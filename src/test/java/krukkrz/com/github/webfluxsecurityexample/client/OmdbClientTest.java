package krukkrz.com.github.webfluxsecurityexample.client;

import krukkrz.com.github.webfluxsecurityexample.model.Poster;
import krukkrz.com.github.webfluxsecurityexample.model.SearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OmdbClientTest {

    @Autowired
    private OmdbClient client;

    @Test
    public void fetchPostersForTitle_returnsPostersFromOmdbCLient_integration_test() {
        //GIVEN
        var title = "harry";

        //WHEN
        Mono<SearchResult> posters = client.fetchPostersForTitle(title);

        //THEN
        assertNotNull(posters.block());
    }

}