package com.web.services.api.controller.v1.post.responses;

import com.web.services.api.controller.v1.users.responses.UserResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseWithUser {
    private Integer id;
    private String description;
    private UserResponse user;
}
