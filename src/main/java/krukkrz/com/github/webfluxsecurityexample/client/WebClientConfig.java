package krukkrz.com.github.webfluxsecurityexample.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${omdb.base-url}")
    private String baseUrl;

    @Value("${omdb.api-key}")
    private String apiKey;

    @Bean
    public WebClient webClient() {
        String authorizedPath = baseUrl + "?apiKey=" + apiKey + "&";
        return WebClient.builder()
                .baseUrl(authorizedPath)
                .build();
    }
}
