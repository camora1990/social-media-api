package com.web.services.api.users.useCases;

import com.web.services.api.models.user.UserModel;
import com.web.services.api.models.user.UserErrorCode;
import com.web.services.api.models.user.UserException;
import com.web.services.api.models.user.Respositories.IUserRepository;
import com.web.services.api.users.useCases.contracts.IDeleteUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteUserUseCase implements IDeleteUseCase {

    private final IUserRepository userRespository;

    public DeleteUserUseCase(IUserRepository userRespository) {
        this.userRespository = userRespository;
    }


    @Override
    public Mono<UserModel> apply(Integer id) {
        return userRespository.delete(id)
                .switchIfEmpty(Mono.error(new UserException(UserErrorCode.USER_NOT_FOUND)));
    }
}
