package com.web.services.api.users.useCases;

import com.web.services.api.models.user.UserModel;
import com.web.services.api.models.user.Respositories.IUserRepository;
import com.web.services.api.users.useCases.contracts.IGetAllUserUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllUserUseCase implements IGetAllUserUseCase {
    private final IUserRepository userRespository;

    public GetAllUserUseCase(IUserRepository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public Flux<UserModel> apply() {
        return userRespository.findAll();
    }
}
