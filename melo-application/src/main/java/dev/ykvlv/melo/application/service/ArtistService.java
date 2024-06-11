package dev.ykvlv.melo.application.service;


import dev.ykvlv.melo.domain.entity.Artist;
import dev.ykvlv.melo.domain.entity.User;
import dev.ykvlv.melo.commons.type.MusicService;
import lombok.NonNull;

import java.util.List;

public interface ArtistService {

    /**
     * Получение списка избранных артистов пользователя для конкретного сервиса
     * @param user пользователь
     * @param musicService сервис
     * @return список артистов
     */
    @NonNull
    List<Artist> findAllByUserAndMusicService(@NonNull User user, @NonNull MusicService musicService);

}
