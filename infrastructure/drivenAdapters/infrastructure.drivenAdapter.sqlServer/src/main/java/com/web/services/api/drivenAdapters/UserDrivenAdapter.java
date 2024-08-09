package com.web.services.api.drivenAdapters;

import com.web.services.api.Entities.UserEntity;
import com.web.services.api.models.user.UserModel;
import com.web.services.api.ReactiveRepositories.IUserReactiveRepository;
import com.web.services.api.models.user.Respositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserDrivenAdapter implements IUserRepository {
    private final IUserReactiveRepository reactiveRepository;
    private final ModelMapper modelMapper;
    public UserDrivenAdapter(IUserReactiveRepository reactiveRepository, ModelMapper modelMapper) {
        this.reactiveRepository = reactiveRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Flux<UserModel> findAll() {
        return reactiveRepository.findAll()
                .map(u->modelMapper.map(u, UserModel.class));
    }

    @Override
    public Mono<UserModel> findById(Integer id) {
        return reactiveRepository.findById(id).map(u->modelMapper.map(u, UserModel.class));
    }

    @Override
    public Mono<UserModel> save(UserModel entity) {
        return reactiveRepository.save(modelMapper.map(entity, UserEntity.class))
                .map(u->modelMapper.map(u, UserModel.class));
    }

    @Override
    public Mono<UserModel> delete(Integer id) {
        return reactiveRepository.findById(id)
                .map(u->modelMapper.map(u, UserModel.class));
    }
}
