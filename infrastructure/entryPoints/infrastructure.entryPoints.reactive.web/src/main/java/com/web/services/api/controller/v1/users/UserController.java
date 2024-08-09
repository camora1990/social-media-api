package com.web.services.api.controller.v1.users;

import com.web.services.api.controller.BaseController;
import com.web.services.api.controller.CustomResponse;
import com.web.services.api.controller.v1.users.requests.CreateUserRequest;
import com.web.services.api.controller.v1.users.responses.UserResponse;
import com.web.services.api.controller.v1.users.responses.UserResponseWithPosts;
import com.web.services.api.models.user.UserModel;
import com.web.services.api.users.useCases.contracts.ICreateUserUseCase;
import com.web.services.api.users.useCases.contracts.IDeleteUseCase;
import com.web.services.api.users.useCases.contracts.IGetAllUserUseCase;
import com.web.services.api.users.useCases.contracts.IGetUserUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "users")
public class UserController extends BaseController {
    private final IGetAllUserUseCase getAllUser;
    private final ICreateUserUseCase createUserUseCase;
    private final IGetUserUseCase getUserUseCase;
    private final IDeleteUseCase deleteUserUseCase;
    private final ModelMapper modelMapper;

    public UserController(IGetAllUserUseCase getAllUser, ICreateUserUseCase createUserUseCase, IGetUserUseCase getUserUseCase, IDeleteUseCase deleteUserUseCase, ModelMapper modelMapper) {
        this.getAllUser = getAllUser;
        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "")
    public Mono<ResponseEntity<CustomResponse<List<UserResponse>>>> getAllUser() {
        return handleRequest(() ->
                getAllUser.apply().
                        collectList().map(user -> user.stream().map(u -> modelMapper.map(u, UserResponse.class)).toList())
        );
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomResponse<UserResponse>>> getUser(@PathVariable Integer id) {
        return handleRequest(() -> getUserUseCase.apply(id)
                .map(user -> modelMapper.map(user, UserResponse.class)));
    }

    @GetMapping("/{id}/post")
    public Mono<ResponseEntity<CustomResponse<UserResponseWithPosts>>> getUserWithPosts(@PathVariable Integer id) {
        return handleRequest(() -> getUserUseCase.userWhitPosts(id)
                .map(user -> modelMapper.map(user, UserResponseWithPosts.class)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<CustomResponse<UserResponse>>> deleteUser(@PathVariable Integer id) {
        return handleRequest(() -> deleteUserUseCase.apply(id).map(user -> modelMapper.map(user, UserResponse.class)));
    }

    @PostMapping("")
    public Mono<ResponseEntity<CustomResponse<UserResponse>>> createUser(@Valid @RequestBody Mono<CreateUserRequest> user) {
        return handleRequest(() ->
                user.flatMap(u -> createUserUseCase
                        .apply(modelMapper.map(u, UserModel.class))
                        .map(userResponse -> modelMapper.map(userResponse, UserResponse.class)
                        )
                ));
    }
}
