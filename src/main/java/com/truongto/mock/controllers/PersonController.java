package com.truongto.mock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

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
import com.truongto.mock.thfw.enums.Enums;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    ResponseEntity<BaseResponse> findAll() {
        BaseResponse response;
        try {
            response = new BaseResponse(Enums.Status.SUCCESS, "OK", personService.findAll());
        } catch (Exception e) {
            response = new BaseResponse(Enums.Status.ERROR, e.getMessage());
        }
        return ResponseEntity.ok(response);
    };

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{id}")
    ResponseEntity<BaseResponse> getByID(@PathVariable("id") Long id) {
        BaseResponse response;
        try {
            Person person = personService.findById(id);
            response = new BaseResponse(Enums.Status.SUCCESS, "OK", person.toDto());
        } catch (Exception e) {
            response = new BaseResponse(Enums.Status.ERROR, e.getMessage());
        }
        return ResponseEntity.ok(response);
    };

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/roles")
    ResponseEntity<BaseResponse> role(@PathVariable Long id, @RequestBody PersonRolePayload payload) {
        BaseResponse response;
        try {
            Person person = personService.findById(id);
            person.setRolesInteger(payload.getRoles());
            person = personService.save(person);
            response = new BaseResponse(Enums.Status.SUCCESS, "OK", person.toDto());
        } catch (Exception e) {
            response = new BaseResponse(Enums.Status.ERROR, e.getMessage());
        }
        return ResponseEntity.ok(response);
    };
}
