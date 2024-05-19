package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.commons.request.auth.AuthRequest;
import dev.ykvlv.melo.commons.response.auth.JwtAuthenticationResponse;
import lombok.NonNull;

public interface AuthenticationService {

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    @NonNull
    JwtAuthenticationResponse signUp(@NonNull AuthRequest request);

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    @NonNull
    JwtAuthenticationResponse signIn(@NonNull AuthRequest request);

}
