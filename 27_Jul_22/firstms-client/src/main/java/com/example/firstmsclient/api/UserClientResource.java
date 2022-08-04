package com.example.firstmsclient.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class UserClientResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserClientResource.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient webClient;

    @GetMapping("/hello-client-web")
    public Mono<String> getClientHelloWeb() {
        LOGGER.info("WebClient: about to call firstms");
        Mono<String> response = webClient.get().uri("/hello")
                .retrieve().bodyToMono(String.class);
        LOGGER.info("WebClient: call completed");
        return response;
    }

    @GetMapping("/hello-client")
    @HystrixCommand(fallbackMethod = "getClientHelloFromFallback")
    public String getClientHello() {
        LOGGER.info("about to call firstms");
        return restTemplate.getForObject("http://firstms/hello", String.class);
    }

    private  String getClientHelloFromFallback() {
        LOGGER.info("Falling Back");
        return "Coming from the fallback";
    }

}
