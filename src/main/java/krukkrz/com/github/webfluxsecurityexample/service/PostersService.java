package krukkrz.com.github.webfluxsecurityexample.service;

import krukkrz.com.github.webfluxsecurityexample.client.OmdbClient;
import krukkrz.com.github.webfluxsecurityexample.model.Poster;
import krukkrz.com.github.webfluxsecurityexample.model.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
@Service
public class PostersService {

    private final OmdbClient omdb;

    public PostersService(OmdbClient omdb) {
        this.omdb = omdb;
    }

    public Flux<Poster> fetchPostersForTitle(String title) {
        return omdb.fetchPostersForTitle(title)
                .flatMapIterable(SearchResult::getSearch)
                .delayElements(Duration.ofMillis(1000));
    }
}
