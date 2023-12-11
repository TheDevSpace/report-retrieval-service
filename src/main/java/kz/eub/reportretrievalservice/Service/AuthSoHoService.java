package kz.eub.reportretrievalservice.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthSoHoService {

    private final String AUTH_URL = "https://test2.1cb.kz/soho-v2/v1/login";
    //    private final String AUTH_URL = "https://soho-v2-staging.1cb.kz/v1/login";

    private final String REFRESH_URL = "https://test2.1cb.kz/soho-v2/v1/refresh";
    //    private final String REFRESH_URL = "https://soho-v2-staging.1cb.kz/v1/refresh";
    private static final String USERNAME = "7003339004";
    private static final String PASSWORD = "900411300518";

    public ResponseEntity<String> authenticate() {
        // Кодирование логина и пароля в формат BASE64
        String credentials = Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes());

        // Создание заголовка Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + credentials);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Создание запроса с заголовком
        HttpEntity<String> request = new HttpEntity<>(headers);

        // Выполнение POST-запроса для аутентификации
        ResponseEntity<String> response = new RestTemplate().exchange(AUTH_URL, HttpMethod.POST, request, String.class);

        return response;
    }


    public ResponseEntity<String> refreshTokens(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Создание тела запроса с refresh token
        String requestBody = "{\"token_hash\": \"" + refreshToken + "\"}";

        // Создание запроса с заголовками и телом
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        // Выполнение POST-запроса для обновления токенов
        ResponseEntity<String> response = new RestTemplate().postForEntity(REFRESH_URL, request, String.class);

        return response;
    }

}
