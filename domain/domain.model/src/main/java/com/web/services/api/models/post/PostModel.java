package com.web.services.api.models.post;

import com.web.services.api.models.user.UserModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostModel {
    public PostModel() {
    }

    private Integer id;
    private String description;
    private Integer userId;
    private UserModel user;
}
