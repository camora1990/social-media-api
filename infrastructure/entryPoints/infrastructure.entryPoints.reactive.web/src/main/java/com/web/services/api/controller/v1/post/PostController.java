package com.web.services.api.controller.v1.post;

import com.web.services.api.controller.BaseController;
import com.web.services.api.controller.CustomResponse;
import com.web.services.api.controller.v1.post.requests.PostRequest;
import com.web.services.api.controller.v1.post.responses.PostResponse;
import com.web.services.api.controller.v1.post.responses.PostResponseWithUser;
import com.web.services.api.models.post.PostModel;
import com.web.services.api.post.useCases.contracts.ICreatePostUseCase;
import com.web.services.api.post.useCases.contracts.IGetPostByUserUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "posts")
public class PostController extends BaseController {

    private final ICreatePostUseCase createPostUseCase;
    private final IGetPostByUserUseCase getPostByUserUseCase;
    private final ModelMapper modelMapper;

    public PostController(ICreatePostUseCase createPostUseCase, IGetPostByUserUseCase getPostByUserUseCase, ModelMapper modelMapper) {
        this.createPostUseCase = createPostUseCase;
        this.getPostByUserUseCase = getPostByUserUseCase;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public Mono<ResponseEntity<CustomResponse<PostResponseWithUser>>> createPost(@Valid @RequestBody Mono<PostRequest> monPost) {
        return handleRequest(()->
             monPost.flatMap(post-> createPostUseCase.apply(modelMapper.map(post, PostModel.class)))
                    .map(post -> modelMapper.map(post, PostResponseWithUser.class))
        );
    }

    @GetMapping("/user-id/{userId}")
    public Mono<ResponseEntity<CustomResponse<List<PostResponse>>>> getPostByUser(@PathVariable Integer userId) {
        return handleRequest(()-> getPostByUserUseCase.apply(userId)
                .collectList()
                .map(postModel ->
                        postModel.stream().map(p-> modelMapper.map(p,PostResponse.class)).toList()
                        )

        );
    }
}
