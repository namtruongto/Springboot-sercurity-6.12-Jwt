package com.truongto.mock.services;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;

public interface TestService {

    @Async
    CompletableFuture <Map<String,String>> test();

    @Async
    CompletableFuture<Map<String,String>> test2();
}
