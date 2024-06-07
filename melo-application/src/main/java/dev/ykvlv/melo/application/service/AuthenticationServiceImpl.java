package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.application.exception.BEWrapper;
import dev.ykvlv.melo.application.exception.BusinessException;
import dev.ykvlv.melo.application.security.JwtUtil;
import dev.ykvlv.melo.commons.request.auth.AuthRequest;
import dev.ykvlv.melo.commons.response.auth.JwtAuthenticationResponse;
import dev.ykvlv.melo.domain.entity.User;
import dev.ykvlv.melo.domain.repository.CityRepository;
import dev.ykvlv.melo.commons.type.Role;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CityRepository cityRepository;

    @NonNull
    public JwtAuthenticationResponse signUp(@NonNull AuthRequest request) {

        var user = User.builder()
                .username(request.getUsername().toLowerCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .registeredAt(LocalDate.now())
                .city(cityRepository.getReferenceById(1L))
                .role(Role.USER)
                .build();

        user = userService.create(user);

        var jwt = jwtUtil.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @NonNull
    public JwtAuthenticationResponse signIn(@NonNull AuthRequest request) {
        var username = request.getUsername().toLowerCase();

        if (!userService.existsByUsername(username)) {
            throw new BEWrapper(BusinessException.USER_NOT_FOUND, username);
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username,
                request.getPassword()
        ));

        var user = userService.loadUserByUsername(username);

        var jwt = jwtUtil.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
