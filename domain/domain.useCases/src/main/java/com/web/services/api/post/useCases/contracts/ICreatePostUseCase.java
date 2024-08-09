package com.web.services.api.post.useCases.contracts;

import com.web.services.api.models.post.PostModel;
import reactor.core.publisher.Mono;

public interface ICreatePostUseCase {
    Mono<PostModel> apply(PostModel post);
}
