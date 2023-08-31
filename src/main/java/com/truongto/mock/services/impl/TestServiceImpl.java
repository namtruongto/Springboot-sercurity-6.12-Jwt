package com.truongto.mock.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.truongto.mock.services.TestService;

@Service
public class TestServiceImpl implements TestService {

    
    

    public String stepOne() {
        // Deley 10 seconds
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Step 1 completed");
        return "Step 1 completed";
    }

    public String stepTwo() {
        // Deley 7 seconds
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Step 2 completed");
        return "Step 2 completed";
    }

    public String stepThree() {
        // Deley 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Step 3 completed");
        return "Step 3 completed";
    }

    public String stepFour() {
        // Deley 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Step 4 completed");
        return "Step 4 completed";
    }

    @Async
    public String stepFive() {
        // Deley 1 seconds
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Step 5 completed");
        return "Step 5 completed";
    }

    @Override
    public CompletableFuture<Map<String, String>> test() {
        Map<String, String> map = new HashMap<>();
        map.put("stepOne", stepOne());
        map.put("stepTwo", stepTwo());
        map.put("stepThree", stepThree());
        map.put("stepFour", stepFour());
        map.put("stepFive", stepFive());
        return CompletableFuture.completedFuture(map);
    }

    @Override
    public CompletableFuture<Map<String, String>> test2() {
        Map<String, String> map = new HashMap<>();
        // Thuc hien tat ca cac step cung luc
        CompletableFuture<String> stepOne = CompletableFuture.supplyAsync(this::stepOne);
        CompletableFuture<String> stepTwo = CompletableFuture.supplyAsync(this::stepTwo);
        CompletableFuture<String> stepThree = CompletableFuture.supplyAsync(this::stepThree);
        CompletableFuture<String> stepFour = CompletableFuture.supplyAsync(this::stepFour);
        CompletableFuture<String> stepFive = CompletableFuture.supplyAsync(this::stepFive);
        
        // Neu step nao hoan thanh thi push vao map
        if(!stepOne.isCompletedExceptionally()) {
            map.put("stepOne", stepOne.join());
        }
        if(!stepTwo.isCompletedExceptionally()) {
            map.put("stepTwo", stepTwo.join());
        }
        if(!stepThree.isCompletedExceptionally()) {
            map.put("stepThree", stepThree.join());
        }
        if(!stepFour.isCompletedExceptionally()) {
            map.put("stepFour", stepFour.join());
        }
        if(!stepFive.isCompletedExceptionally()) {
            map.put("stepFive", stepFive.join());
        }
        
        return CompletableFuture.completedFuture(map);
    }


}
