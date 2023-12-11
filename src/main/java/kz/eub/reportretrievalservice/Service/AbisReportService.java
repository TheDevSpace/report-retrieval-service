package kz.eub.reportretrievalservice.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.eub.reportretrievalservice.Domain.Abis.*;
import kz.eub.reportretrievalservice.Domain.SoHo.Auth.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Service
@RequiredArgsConstructor
public class AbisReportService {
    private final String apiUrl = "https://kyc-fcb-test.1cb.kz/v1/report/type";

    private final AuthAbisService authAbisService;

    public String getAbisData(String bin) throws JsonProcessingException {
        //login
        ResponseEntity<String> authResponse = authAbisService.authenticate();
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
        ObjectMapper objectMapper = new ObjectMapper();
        ReportTypeResponse reportTypeResponse = objectMapper.readValue(responseEntity.getBody(), ReportTypeResponse.class);

        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setProduct_code("reportTypeResponse.getDates().getCode");
        reportRequest.setRequest_id(12345);
        reportRequest.setBin(bin);
        reportRequest.setCallback_url("https://en7ph0hdnbkgc.x.pipedream.net");
        reportRequest.setClient_bin(bin);
        ReportResponse response = registerReport(reportRequest, apiToken);

        String totalResp = getReport2(response.getData().getReport_id(), response.getFcb_request_id(), apiToken);

        return totalResp;
    }

    public AccessTokenResponse parseAuthBody(ResponseEntity<String> response) throws JsonProcessingException {
        String jsonBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        AccessTokenResponse accessTokenResponse = objectMapper.readValue(jsonBody, AccessTokenResponse.class);
        System.out.println("Access Token Hash: " + accessTokenResponse.getAccess().getHash());
        return accessTokenResponse;
    }

    public ReportResponse registerReport(ReportRequest reportRequest, String apiToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Client-type", "B2B");

        HttpEntity<ReportRequest> requestEntity = new HttpEntity<>(reportRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ReportResponse> responseEntity = restTemplate.postForEntity("https://kyc-fcb-test.1cb.kz" + "/v1/report/register", requestEntity, ReportResponse.class);

        return responseEntity.getBody();
    }


    public String getReport2(int reportId, String fcbRequestId, String apiToken) {
        HttpHeaders headers = createHeaders2(apiToken);
        String url = UriComponentsBuilder.fromHttpUrl("https://kyc-fcb-test.1cb.kz")
                .path("/v1/report/get/{reportId}")
                .queryParam("fcb_request_id", fcbRequestId)
                .buildAndExpand(reportId)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        return responseEntity.getBody();
    }

    private HttpHeaders createHeaders2(String apiToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Client-type", "B2B");
        return headers;
    }
}
