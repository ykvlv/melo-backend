package dev.ykvlv.melo.domain.repository;

import dev.ykvlv.melo.domain.entity.User;
import dev.ykvlv.melo.domain.entity.UserFavoriteArtists;
import dev.ykvlv.melo.commons.type.MusicService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoriteArtistsRepository extends JpaRepository<UserFavoriteArtists, Long> {
    List<UserFavoriteArtists> findAllByUserAndMusicService(User user, MusicService musicService);
}
