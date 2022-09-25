package com.murilostore.murilostore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HeartBeatController {

    @GetMapping
    public String heartBeat() {
        return "OK";
    }
}
