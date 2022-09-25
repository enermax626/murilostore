package com.murilostore.murilostore.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    CUSTOMER_NOT_FOUND(404L),
    CUSTOMER_ALREADY_EXISTS(400L);

    private final Long code;

    ExceptionCode(Long code) {
        this.code = code;
    }

}
