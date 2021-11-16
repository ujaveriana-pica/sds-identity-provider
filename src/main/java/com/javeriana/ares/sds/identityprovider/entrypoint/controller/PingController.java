package com.javeriana.ares.sds.identityprovider.entrypoint.controller;

import com.javeriana.ares.sds.identityprovider.crosscutting.constants.ResourceEndpoint;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(ResourceEndpoint.PING_RESOURCE)
@CrossOrigin(ResourceEndpoint.ALL_ORIGIN)
public class PingController {

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<ResponseEntity<String>> ping() {
        return Mono.just("pong").map(ResponseEntity::ok);
    }
}
