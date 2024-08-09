package com.web.services.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

public abstract class BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected <TResult> Mono<ResponseEntity<CustomResponse<TResult>>> handleRequest(Supplier<Mono<TResult>> request) {
        return request.get()
                .doOnSubscribe(subscription -> logger.info("Handling request"))
                .map(response -> ResponseEntity.ok(CustomResponse.buildSuccess(response)))
                .onErrorResume(Mono::error);
    }

}
