package krukkrz.com.github.webfluxsecurityexample.client;

import krukkrz.com.github.webfluxsecurityexample.model.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OmdbClient {

    private final WebClient webClient;

    public OmdbClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<SearchResult> fetchPostersForTitle(String title) {
        log.info("searching posters for title {}", title);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("")
                        .queryParam("s", title)
                        .build())
                .retrieve()
                .bodyToMono(SearchResult.class);
    }
}
