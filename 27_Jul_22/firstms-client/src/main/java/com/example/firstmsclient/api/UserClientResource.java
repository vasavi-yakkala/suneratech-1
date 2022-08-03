package com.example.firstmsclient.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserClientResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserClientResource.class);
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello-client")
    public String getClientHello() {
        LOGGER.info("about to call firstms");
        return restTemplate.getForObject("http://firstms/hello", String.class);
    }
}
