package com.web.services.api.users.useCases;

import com.web.services.api.models.post.Respositories.IPostRepository;
import com.web.services.api.models.user.UserModel;
import com.web.services.api.models.user.UserErrorCode;
import com.web.services.api.models.user.UserException;
import com.web.services.api.models.user.Respositories.IUserRepository;
import com.web.services.api.users.useCases.contracts.IGetUserUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetUserUseCase implements IGetUserUseCase {

    private final IUserRepository userRepository;
    private final IPostRepository postRepository;

    public GetUserUseCase(IUserRepository userRespository, IPostRepository postRepository) {
        this.userRepository = userRespository;
        this.postRepository = postRepository;
    }


    @Override
    public Mono<UserModel> apply(Integer id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserException(UserErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public Mono<UserModel> userWhitPosts(Integer id) {
        return apply(id)
                .zipWith(postRepository.findByUserId(id).collectList())
                .map(tuple -> {
                    UserModel user = tuple.getT1();
                    var posts = tuple.getT2();
                    user.setPosts(posts);
                    return user;
                });
    }
}
