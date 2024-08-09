package com.web.services.api.ReactiveRepositories;

import com.web.services.api.Entities.PostEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IPostReactiveRepository extends ReactiveCrudRepository<PostEntity, Integer> {
    Flux<PostEntity> findAllByUserId(Integer userId);

}
