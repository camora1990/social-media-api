package com.web.services.api.users.useCases.contracts;

import com.web.services.api.models.user.UserModel;
import reactor.core.publisher.Mono;


public interface IDeleteUseCase {
    Mono<UserModel> apply(Integer id);
}
