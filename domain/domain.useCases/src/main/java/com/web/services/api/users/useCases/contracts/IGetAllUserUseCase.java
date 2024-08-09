package com.web.services.api.users.useCases.contracts;

import com.web.services.api.models.user.UserModel;
import reactor.core.publisher.Flux;


public interface IGetAllUserUseCase {
    Flux<UserModel> apply();
}
