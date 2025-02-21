package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.application.exception.BEWrapper;
import dev.ykvlv.melo.application.exception.BusinessException;
import dev.ykvlv.melo.commons.response.ArtistResponse;
import dev.ykvlv.melo.commons.response.UserResponse;
import dev.ykvlv.melo.commons.response.music.MusicServiceResponse;
import dev.ykvlv.melo.domain.entity.User;
import dev.ykvlv.melo.domain.mapper.ArtistMapper;
import dev.ykvlv.melo.domain.mapper.UserMapper;
import dev.ykvlv.melo.domain.repository.UserRepository;
import dev.ykvlv.melo.commons.type.MusicService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ArtistService artistService;
    private final ArtistMapper artistMapper;

    @NonNull
    @Override
    @Transactional(readOnly = true)
    public UserResponse read(@NonNull String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BEWrapper(BusinessException.USER_NOT_FOUND, username));

        return userMapper.map(user);
    }

    @NonNull
    public User create(@NonNull User user) {
        var username = user.getUsername();
        if (userRepository.existsByUsername(username)) {
            throw new BEWrapper(BusinessException.USER_ALREADY_EXISTS, username);
        }

        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(@NonNull String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User changeYaMusicCredentials(@NonNull String username, @NonNull String yaMusicCredentials) {
        User user = getByUsername(username);
        user.setYaMusicCredentials(yaMusicCredentials);
        return userRepository.save(user);
    }

    @NonNull
    @Override
    @Transactional(readOnly = true)
    public MusicServiceResponse getYaMusicInfo(@NonNull String username) {
        User user = getByUsername(username);

        if (user.getYaMusicCredentials() == null) {
            return MusicServiceResponse.builder().connected(false).build();
        }

        List<ArtistResponse> artists = artistService
                .findAllByUserAndMusicService(user, MusicService.YANDEX_MUSIC)
                .stream().map(artistMapper::map).collect(Collectors.toList());

        return MusicServiceResponse.builder()
                .connected(user.getYaMusicCredentials() != null)
                .login(user.getYaMusicCredentials())
                .artists(artists)
                .build();
    }

    @NonNull
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) {
        return getByUsername(username);
    }

    @NonNull
    @Override
    public User getByUsername(@NonNull String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BEWrapper(BusinessException.USER_NOT_FOUND, username));
    }
}
