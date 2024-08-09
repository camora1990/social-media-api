package com.web.services.api.controller.v1.post.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
public class PostRequest {

    @NotNull
    @Length(min = 1, max = 255)
    private String description;
    @Min(1)
    private Integer userId;
}
