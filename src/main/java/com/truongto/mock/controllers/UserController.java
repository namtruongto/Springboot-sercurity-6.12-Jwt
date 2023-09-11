package com.truongto.mock.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truongto.mock.dtos.BaseResponse;
import com.truongto.mock.entities.User;
import com.truongto.mock.payload.UserRolePayload;
import com.truongto.mock.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    ResponseEntity<BaseResponse> findAll() {
        BaseResponse response = new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), userService.findAll());
        return ResponseEntity.ok(response);
    };

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse> getByID(@PathVariable("id") Long id) {
        User person = userService.findById(id);
        BaseResponse response = new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), person.toDto());
        return ResponseEntity.ok(response);
    };

    @PutMapping("/{id}/roles")
    ResponseEntity<BaseResponse> role(@PathVariable Long id, @RequestBody UserRolePayload payload) {
        User person = userService.findById(id);
        person.setRolesInteger(payload.getRoles());
        person = userService.save(person);
        BaseResponse response = new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), person.toDto());
        return ResponseEntity.ok(response);
    };
}
