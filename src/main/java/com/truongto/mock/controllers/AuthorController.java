package com.truongto.mock.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truongto.mock.dtos.BaseResponse;
import com.truongto.mock.entities.Author;
import com.truongto.mock.services.AuthorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getAuthor(@PathVariable("id") Long id) {
        BaseResponse response = new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                this.authorService.getAuthorById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createAuthor(@Valid @RequestBody Author author) {
        BaseResponse response = new BaseResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                this.authorService.createAuthor(author));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse> updateAuthor(@PathVariable("id") Long id, @RequestBody Author author) {
        BaseResponse response = new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                this.authorService.updateAuthor(id, author));
        return ResponseEntity.ok(response);
    }
}
