package com.murilostore.murilostore;

import org.springframework.beans.factory.annotation.Value;

public class TestUtil {

    @Value("${test.base-url}")
    private String baseUrl;

    public String getUrl(String uri, Integer port) {
        return baseUrl + port + uri;
    }
}
