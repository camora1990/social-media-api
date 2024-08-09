package com.web.services.api.models.user;

import com.web.services.api.shared.BusinessException;

public class UserException extends BusinessException {
    public UserException(UserErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}

