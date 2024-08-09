package com.web.services.api.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;


@Setter
@Getter
@Table("users")
public class UserEntity {

    protected UserEntity() {
    }

    @Id
    private Integer id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
}
