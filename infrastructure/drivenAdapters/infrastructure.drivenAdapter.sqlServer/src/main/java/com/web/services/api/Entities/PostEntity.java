package com.web.services.api.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("posts")
@Getter
@Setter
public class PostEntity {
    public PostEntity() {
    }

    @Id
    private Integer id;
    private String description;
    private Integer userId;
}
