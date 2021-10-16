package krukkrz.com.github.webfluxsecurityexample.controller;

import krukkrz.com.github.webfluxsecurityexample.client.OmdbClient;
import krukkrz.com.github.webfluxsecurityexample.client.WebClientConfig;
import krukkrz.com.github.webfluxsecurityexample.model.Poster;
import krukkrz.com.github.webfluxsecurityexample.service.PostersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import static krukkrz.com.github.webfluxsecurityexample.common.Constants.API_V1_PATH;

@WebFluxTest
@Import({PostersService.class, OmdbClient.class, WebClientConfig.class})
class PostersControllerTest {

    @Autowired
    private WebTestClient client;

    @Test
    @WithMockUser
    public void fetchPostersForTitle_returnsFluxOfPostersForAGivenTitle() {
        //GIVEN
        var title = "rings";

        //WHEN
        client.get()
                .uri(builder -> builder.path(API_V1_PATH + "posters")
                        .queryParam("title", title)
                        .build())
                .exchange()
                //THEN
                .expectStatus().isOk()
                .returnResult(Poster.class)
                .getResponseBody()
                .doOnNext(Assertions::assertNotNull);
    }

}