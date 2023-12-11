package kz.eub.reportretrievalservice.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.username}")
    private String username;

    @Value("${api.password}")
    private String password;

}