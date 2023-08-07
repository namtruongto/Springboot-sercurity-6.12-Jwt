package com.truongto.mock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truongto.mock.dtos.BaseResponse;
import com.truongto.mock.entities.Author;
import com.truongto.mock.services.AuthorService;
import com.truongto.mock.thfw.enums.Enums.Status;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    
    @GetMapping("/{id}")
    public BaseResponse getAuthor(@PathVariable("id") Long id) {
        BaseResponse response;
        try {
           response = new BaseResponse(Status.SUCCESS, "Success", this.authorService.getAuthorById(id));
        } catch (Exception e) {
            response = new BaseResponse(Status.ERROR, e.getMessage());
        }
        return response;
    }

    @PostMapping("/create")
    public BaseResponse createAuthor(@RequestBody Author author) {

        BaseResponse response;
        try {
            response = new BaseResponse(Status.SUCCESS, "Success", this.authorService.createAuthor(author));
        } catch (Exception e) {
            response = new BaseResponse(Status.ERROR, e.getMessage());
        }
        return response;
    }
}
