package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.application.exception.BEWrapper;
import dev.ykvlv.melo.application.exception.BusinessException;
import dev.ykvlv.melo.domain.entity.User;
import dev.ykvlv.melo.domain.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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


    @NonNull
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BEWrapper(BusinessException.USER_NOT_FOUND, username));
    }
}
