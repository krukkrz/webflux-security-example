package krukkrz.com.github.webfluxsecurityexample.controller;

import krukkrz.com.github.webfluxsecurityexample.common.Constants;
import krukkrz.com.github.webfluxsecurityexample.model.Poster;
import krukkrz.com.github.webfluxsecurityexample.service.PostersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static krukkrz.com.github.webfluxsecurityexample.controller.PostersController.PATH;

@Slf4j
@RestController
@RequestMapping({PATH})
public class PostersController {

    static final String PATH = Constants.API_V1_PATH + "posters";

    private final PostersService service;

    public PostersController(PostersService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Poster> fetchPosters(@RequestParam String title) {
        return service.fetchPostersForTitle(title)
                .doOnNext(poster -> log.info("POSTER: {}", poster));
    }

    @GetMapping("/user")
    public Mono<String> userDetails(Mono<Principal> principalMono) {
        return principalMono.map(Principal::getName);
    }
}
