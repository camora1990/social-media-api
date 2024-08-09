package com.web.services.api.models.post;

import com.web.services.api.shared.BusinessException;

public class PostException extends BusinessException {
    public PostException(PostErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}

