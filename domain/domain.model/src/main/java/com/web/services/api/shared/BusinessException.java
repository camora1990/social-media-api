package com.web.services.api.shared;

import lombok.Getter;

@Getter
public abstract class BusinessException extends Exception {

    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}