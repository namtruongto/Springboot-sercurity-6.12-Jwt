package com.truongto.mock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<BaseResponse> getBook(Pageable pageable, BookPayload payload) {
        HttpHeaders headers = new HttpHeaders();
        BaseResponse response = new BaseResponse(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(),
                this.bookService.pagingBook(pageable, payload));
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createBook(@RequestBody Book book) {
        BaseResponse response = new BaseResponse(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(),
                this.bookService.createBook(book));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
