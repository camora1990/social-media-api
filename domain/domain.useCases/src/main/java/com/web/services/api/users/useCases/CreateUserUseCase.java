package com.web.services.api.users.useCases;

import com.web.services.api.models.user.UserModel;
import com.web.services.api.models.user.Respositories.IUserRepository;
import com.web.services.api.users.useCases.contracts.ICreateUserUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateUserUseCase implements ICreateUserUseCase {
    private final IUserRepository userRespository;

    public CreateUserUseCase(IUserRepository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public Mono<UserModel> apply(UserModel entity) {
        return userRespository.save(entity);
    }
}
