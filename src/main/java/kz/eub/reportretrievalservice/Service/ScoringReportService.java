package kz.eub.reportretrievalservice.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.eub.reportretrievalservice.Domain.SoHo.Auth.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Service
@RequiredArgsConstructor
public class ScoringReportService {
    private final String apiUrl = "https://test2.1cb.kz/soho-v2/v1/scoring/get?IIN_BIN=900411300518";

    private final AuthSoHoService authSoHoService;

    public String getScoringData(String bin) throws JsonProcessingException {
        ResponseEntity<String> authResponse = authSoHoService.authenticate();
        AccessTokenResponse accessTokenResponse = parseAuthBody(authResponse);
        String apiToken = accessTokenResponse.getAccess().getHash();

        if (authResponse.getStatusCode().value() != 200) {
            System.out.println("error");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiUrl));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        return responseEntity.getBody();
    }

    public AccessTokenResponse parseAuthBody(ResponseEntity<String> response) throws JsonProcessingException {
        String jsonBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        AccessTokenResponse accessTokenResponse = objectMapper.readValue(jsonBody, AccessTokenResponse.class);
        System.out.println("Access Token Hash: " + accessTokenResponse.getAccess().getHash());
        return accessTokenResponse;
    }
}
