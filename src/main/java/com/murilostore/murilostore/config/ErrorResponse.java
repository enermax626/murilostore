package com.murilostore.murilostore.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String timestamp = String.valueOf(System.currentTimeMillis());

    private final String message;

    private final Integer code;

}
