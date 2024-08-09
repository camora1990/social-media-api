package com.web.services.api.post.useCases;

import com.web.services.api.models.post.PostModel;
import com.web.services.api.models.post.Respositories.IPostRepository;
import com.web.services.api.post.useCases.contracts.IGetPostByUserUseCase;
import com.web.services.api.users.useCases.contracts.IGetUserUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetPostByUserUseCase implements IGetPostByUserUseCase {

    private final IPostRepository postRepository;
    private final IGetUserUseCase getUserUseCase;

    public GetPostByUserUseCase(IPostRepository postRepository, IGetUserUseCase getUserUseCase) {
        this.postRepository = postRepository;
        this.getUserUseCase = getUserUseCase;
    }

    @Override
    public Flux<PostModel> apply(Integer userId) {
        return getUserUseCase.apply(userId).thenMany(postRepository.findByUserId(userId));


    }
}
