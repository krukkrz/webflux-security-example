package krukkrz.com.github.webfluxsecurityexample.service;

import krukkrz.com.github.webfluxsecurityexample.client.OmdbClient;
import krukkrz.com.github.webfluxsecurityexample.model.Poster;
import krukkrz.com.github.webfluxsecurityexample.model.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
public class PostersService {

    private final OmdbClient omdb;

    public PostersService(OmdbClient omdb) {
        this.omdb = omdb;
    }

    public Flux<Poster> fetchPostersForTitle(String title) {
        Mono<String> nameMono = ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName);

        Mono<Authentication> authenticationMono = ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication);

        return omdb.fetchPostersForTitle(title)
                .zipWith(nameMono)
                .map(tuple -> {
                    log.info("Username: {}", tuple.getT2());
                    return tuple.getT1();
                })
                .flatMapIterable(SearchResult::getSearch)
                .delayElements(Duration.ofMillis(1000));
    }
}
