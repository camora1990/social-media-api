package com.web.services.api.models.post.Respositories;

import com.web.services.api.models.post.PostModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPostRepository {
    Mono<PostModel> save(PostModel post);
    Flux<PostModel> findByUserId(Integer userId);


}
