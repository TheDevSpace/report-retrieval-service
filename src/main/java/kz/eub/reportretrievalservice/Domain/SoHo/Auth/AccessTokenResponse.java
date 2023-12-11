package kz.eub.reportretrievalservice.Domain.SoHo.Auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@RequiredArgsConstructor
public class AccessTokenResponse {
    private Access access;
    private Refresh refresh;
    private boolean pass_change_needed;

    // геттеры и сеттеры

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Access {
        private String hash;
        private String expires_at;
        private int ttl;
        private String issue_date;

        // геттеры и сеттеры
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Refresh {
        private String hash;
        private String expires_at;
        private int ttl;
        private String issue_date;

        // геттеры и сеттеры
    }
}