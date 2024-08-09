package com.web.services.api.controller.v1.users.responses;


import com.web.services.api.controller.v1.post.responses.PostResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserResponseWithPosts {
    private Integer id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private List<PostResponse> posts;
}
