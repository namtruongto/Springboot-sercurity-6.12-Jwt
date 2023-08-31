package com.truongto.mock.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truongto.mock.services.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    
    private final TestService testService;

    @GetMapping
    public Map<String, String> test(){
    
        Map<String, String> map = new HashMap<>();

        this.testService.test2().thenAccept(result -> {
           map.putAll(result);
        });

        return map;
    }
}
