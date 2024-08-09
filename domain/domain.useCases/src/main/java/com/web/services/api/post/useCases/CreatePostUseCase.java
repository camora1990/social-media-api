package com.web.services.api.post.useCases;

import com.web.services.api.models.post.PostModel;
import com.web.services.api.models.post.Respositories.IPostRepository;
import com.web.services.api.post.useCases.contracts.ICreatePostUseCase;
import com.web.services.api.users.useCases.contracts.IGetUserUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreatePostUseCase implements ICreatePostUseCase {

    private final IPostRepository postRepository;
    private final IGetUserUseCase getUserUseCase;

    public CreatePostUseCase(IPostRepository postRepository, IGetUserUseCase getUserUseCase) {
        this.postRepository = postRepository;
        this.getUserUseCase = getUserUseCase;
    }

    @Override
    public Mono<PostModel> apply(PostModel post) {
        return getUserUseCase.apply(post.getUserId())
                .flatMap(user -> postRepository.save(post)
                        .map(p->{
                            p.setUser(user);
                            return p;
                        })
                );
    }
}
