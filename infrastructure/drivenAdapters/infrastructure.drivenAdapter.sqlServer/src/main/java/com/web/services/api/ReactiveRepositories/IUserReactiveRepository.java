package com.web.services.api.ReactiveRepositories;

import com.web.services.api.Entities.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IUserReactiveRepository extends ReactiveCrudRepository<UserEntity, Integer> {
}
