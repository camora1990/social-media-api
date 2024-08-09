package com.web.services.api.shared;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IGenericRespository<T,TId> {
    public Flux<T> findAll();
    public Mono<T> findById(TId id);
    public Mono<T> save(T entity);
    public Mono<T> delete(TId id);
}
