package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.domain.entity.User;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    /**
     * Создание пользователя
     *
     * @param user данные пользователя
     * @return созданный пользователь
     */
    @NonNull
    User create(@NonNull User user);

    /**
     * Проверка существования пользователя по имени
     *
     * @param username имя пользователя
     * @return true, если пользователь существует
     */
    boolean existsByUsername(@NonNull String username);
}
