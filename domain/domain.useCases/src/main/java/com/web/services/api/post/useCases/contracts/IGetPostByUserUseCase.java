package com.web.services.api.post.useCases.contracts;

import com.web.services.api.models.post.PostModel;
import reactor.core.publisher.Flux;

public interface IGetPostByUserUseCase {
    Flux<PostModel> apply(Integer userId);
}
