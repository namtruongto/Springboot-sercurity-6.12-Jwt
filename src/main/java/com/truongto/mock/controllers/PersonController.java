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
import com.truongto.mock.entities.Person;
import com.truongto.mock.payload.PersonRolePayload;
import com.truongto.mock.services.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    ResponseEntity<BaseResponse> findAll() {
        BaseResponse response = new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), personService.findAll());
        return ResponseEntity.ok(response);
    };

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse> getByID(@PathVariable("id") Long id) {
        Person person = personService.findById(id);
        BaseResponse response = new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), person.toDto());
        return ResponseEntity.ok(response);
    };

    @PutMapping("/{id}/roles")
    ResponseEntity<BaseResponse> role(@PathVariable Long id, @RequestBody PersonRolePayload payload) {
        Person person = personService.findById(id);
        person.setRolesInteger(payload.getRoles());
        person = personService.save(person);
        BaseResponse response = new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), person.toDto());
        return ResponseEntity.ok(response);
    };
}
