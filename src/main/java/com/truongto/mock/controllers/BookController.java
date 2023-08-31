package com.truongto.mock.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truongto.mock.dtos.BaseResponse;
import com.truongto.mock.entities.Book;
import com.truongto.mock.payload.BookPayload;
import com.truongto.mock.services.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<BaseResponse> getBook(Pageable pageable, BookPayload payload) {
        HttpHeaders headers = new HttpHeaders();
        BaseResponse response = new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                this.bookService.pagingBook(pageable, payload));
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createBook(@Valid @RequestBody Book book) {
        BaseResponse response = new BaseResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                this.bookService.createBook(book));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
