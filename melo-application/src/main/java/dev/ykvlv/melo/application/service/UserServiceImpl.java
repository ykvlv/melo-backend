package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.application.exception.BEWrapper;
import dev.ykvlv.melo.application.exception.BusinessException;
import dev.ykvlv.melo.commons.response.UserResponse;
import dev.ykvlv.melo.domain.entity.User;
import dev.ykvlv.melo.domain.mapper.UserMapper;
import dev.ykvlv.melo.domain.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
    public void changeYaMusicCredentials(@NonNull String username, @NonNull String yaMusicCredentials) {
        User user = getByUsername(username);
        user.setYaMusicCredentials(yaMusicCredentials);
        userRepository.save(user);
    }

    @NonNull
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) {
        return getByUsername(username);
    }

    /**
     * Получить пользователя по имени
     *
     * @param username имя пользователя
     * @return пользователь
     */
    @NonNull
    private User getByUsername(@NonNull String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BEWrapper(BusinessException.USER_NOT_FOUND, username));
    }
}
