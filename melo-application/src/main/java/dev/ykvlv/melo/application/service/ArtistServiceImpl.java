package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.domain.entity.Artist;
import dev.ykvlv.melo.domain.entity.User;
import dev.ykvlv.melo.domain.entity.UserFavoriteArtists;
import dev.ykvlv.melo.domain.repository.UserFavoriteArtistsRepository;
import dev.ykvlv.melo.commons.type.MusicService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final UserFavoriteArtistsRepository userFavoriteArtistsRepository;

    @NonNull
    @Override
    @Transactional(readOnly = true)
    public List<Artist> findAllByUserAndMusicService(@NonNull User user, @NonNull MusicService musicService) {
        return userFavoriteArtistsRepository
                .findAllByUserAndMusicService(user, musicService)
                .stream()
                .map(UserFavoriteArtists::getArtist)
                .sorted((a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()))
                .collect(Collectors.toList());
    }
}
