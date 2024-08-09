package com.web.services.api.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponse<T>{
    private final int errorCode;
    private final String message;
    private final T data;

    private CustomResponse(int errorCode, String message, T data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public static <T> CustomResponse<T> buildSuccess(T data) {
        return new CustomResponse<>(0, "", data);
    }


    public static <T> CustomResponse<T> buildError(int errorCode, String message, T data) {
        return new CustomResponse<>(errorCode, message, data);
    }
}
