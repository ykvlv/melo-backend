package dev.ykvlv.melo.application.service.music;


import dev.ykvlv.melo.application.MeloProperties;
import dev.ykvlv.melo.application.config.Constants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class YaMusicServiceImpl implements YaMusicService {

    private final MeloProperties meloProperties;
    private final RestTemplate restTemplate;

    @NonNull
    @Override
    public String fetchToken(@NonNull String code) {
        // Подготовка данных для запроса
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String auth = meloProperties.getYaMusicClientId() + ":" + meloProperties.getYaMusicClientSecret();
        headers.add(HttpHeaders.AUTHORIZATION, Constants.BASIC + Base64.getEncoder().encodeToString(auth.getBytes()));

        // Формирование тела запроса
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(Constants.GRANT_TYPE, Constants.AUTHORIZATION_CODE);
        map.add(Constants.CODE, code);

        // Создание запроса
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        // Отправка запроса
        ResponseEntity<String> response = restTemplate.exchange(
                "https://oauth.yandex.ru/token", HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // Парсинг JSON ответа
            JSONObject jsonObject = new JSONObject(response.getBody());
            return jsonObject.getString(Constants.ACCESS_TOKEN);  // Возвращаем только токен доступа
        } else {
            throw new RuntimeException("Ошибка подключения: " + response.getBody());
        }
    }

    @NonNull
    @Override
    public String fetchLogin(@NonNull String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, Constants.O_AUTH + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(Constants.PARAMETERS, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://login.yandex.ru/info",
                HttpMethod.GET,
                entity,
                String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());
        return jsonObject.getString(Constants.LOGIN);
    }
}
