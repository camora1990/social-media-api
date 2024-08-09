package com.web.services.api.controller.v1.users.responses;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserResponse {
    private Integer id;

    private String name;

    private String lastName;

    private LocalDate birthDate;
}
