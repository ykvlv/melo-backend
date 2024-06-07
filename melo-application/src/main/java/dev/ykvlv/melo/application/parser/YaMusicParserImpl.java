package dev.ykvlv.melo.application.parser;

import com.google.common.collect.Lists;
import dev.ykvlv.melo.application.MeloParserProperties;
import dev.ykvlv.melo.commons.parser.ArtistData;
import dev.ykvlv.melo.commons.parser.YaMusicLibrary;
import dev.ykvlv.melo.commons.parser.YaMusicTrack;
import dev.ykvlv.melo.commons.type.MusicService;
import dev.ykvlv.melo.domain.entity.User;
import lombok.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class YaMusicParserImpl implements ParserStrategy {
    private static final int BATCH_SIZE = 49;

    private final FilterService filterService;
    private final MeloParserProperties.MusicParserProperties parserProperties;
    private final WebClient webClient;

    public YaMusicParserImpl(FilterService filterService,
                             MeloParserProperties meloParserProperties) {
        this.filterService = filterService;
        this.parserProperties = meloParserProperties.getYaMusic();
        this.webClient = WebClient.builder()
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(parserProperties.getHeaders()))
                .build();
    }

    @Override
    public void parse() {
        // TODO
    }

    public void parseUser(@NonNull User user) {
        String libraryUrl = parserProperties.getLibraryUrl().formatted(user.getYaMusicCredentials());

        // Получаем библиотеку пользователя (его треки)
        YaMusicLibrary yaMusicLibrary = webClient.post()
                .uri(libraryUrl)
                .retrieve()
                .bodyToMono(YaMusicLibrary.class)
                .block();

        // Делим все треки пользователя на части
        List<List<String>> batches = Lists.partition(yaMusicLibrary.getTrackIds(), BATCH_SIZE);

        for (List<String> batch : batches) {
            // Этот запрос надо проводить пачками по 49 треков максимум, а не сразу все
            List<YaMusicTrack> yaMusicTracks = webClient.post()
                    .uri(parserProperties.getTracksUrl())
                    .body(BodyInserters.fromFormData("MIME Type", "application/x-www-form-urlencoded; charset=UTF-8")
                            .with("entries", String.join(",", batch))
                            .with("strict", "true")
                            .with("removeDuplicates", "true")
                            .with("lang", "ru")
                            .with("overembed", "false"))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<YaMusicTrack>>() {})
                    .block();

            // Добавление каждого артиста в избранное
            for (YaMusicTrack yaMusicTrack : yaMusicTracks) {
                // Получение всех артистов из трека
                for (YaMusicTrack.Artist artist : yaMusicTrack.getArtists()) {
                    var coverUri = transformCoverUri(artist.getCover());

                    var artistData = ArtistData.builder()
                            .artistName(artist.getName())
                            .photoUrl(coverUri)
                            .musicService(MusicService.YANDEX_MUSIC)
                            .build();
                    filterService.addFavoriteArtist(user, artistData);
                }
            }
        }
    }

    private String transformCoverUri(YaMusicTrack.Artist.Cover cover) {
        if (cover == null) {
            return null;
        }
        var coverUri = cover.getUri();
        if (coverUri != null && coverUri.endsWith("%%")) {
            // Удаляем "%%" с конца строки и добавляем "/300x300"
            return coverUri.substring(0, coverUri.length() - 2) + "300x300";
        }
        return coverUri; // Возвращаем оригинальный URL, если нет "%%"
    }

    @Override
    public void handleError(@NonNull Exception e) {
        e.printStackTrace();
    }

}
