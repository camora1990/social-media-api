package com.web.services.api.models.user;

import lombok.Getter;


@Getter
public enum UserErrorCode {
    USER_NOT_FOUND(1001, "User not found."),
    INVALID_USER_INPUT(1002, "Invalid user input.");

    private final int code;
    private final String message;

    UserErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
