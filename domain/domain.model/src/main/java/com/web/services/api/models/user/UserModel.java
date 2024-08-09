package com.web.services.api.models.user;

import com.web.services.api.models.post.PostModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
public class UserModel {

    public UserModel() {
    }

    private Integer id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private List<PostModel> posts;
}
