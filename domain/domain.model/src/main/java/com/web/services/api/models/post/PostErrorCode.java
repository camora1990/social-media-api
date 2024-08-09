package com.web.services.api.models.post;

import lombok.Getter;


@Getter
public enum PostErrorCode {
    USER_NOT_FOUND(1001, "User not found."),
    INVALID_USER_INPUT(1002, "Invalid user input.");

    private final int code;
    private final String message;

    PostErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
