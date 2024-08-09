package com.web.services.api.drivenAdapters;

import com.web.services.api.Entities.PostEntity;
import com.web.services.api.models.post.PostModel;
import com.web.services.api.models.post.Respositories.IPostRepository;
import com.web.services.api.ReactiveRepositories.IPostReactiveRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PostDrivenAdapter implements IPostRepository {
    private final IPostReactiveRepository postReactiveRepository;
    private final ModelMapper mapper;
    public PostDrivenAdapter(IPostReactiveRepository postReactiveRepository, ModelMapper mapper) {
        this.postReactiveRepository = postReactiveRepository;

        this.mapper = mapper;
    }


    @Override
    public Mono<PostModel> save(PostModel post) {
        PostEntity postEntity = mapper.map(post, PostEntity.class);
        return postReactiveRepository.save(postEntity)
                .map(newPost -> mapper.map(newPost, PostModel.class));
    }

    @Override
    public Flux<PostModel> findByUserId(Integer userId) {
        return postReactiveRepository.findAllByUserId(userId)
                .map(p ->
                      mapper.map(p, PostModel.class)
                );
    }
}
