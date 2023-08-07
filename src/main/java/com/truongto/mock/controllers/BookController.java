package com.truongto.mock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truongto.mock.dtos.BaseResponse;
import com.truongto.mock.payload.BookPayload;
import com.truongto.mock.services.BookService;
import com.truongto.mock.thfw.enums.Enums.Status;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @GetMapping
    public ResponseEntity<BaseResponse> getBook(Pageable pageable, BookPayload payload) {
        BaseResponse response;
        HttpHeaders headers = new HttpHeaders();
        try {
            System.err.println("statuses: " + payload.getStatuses());
            response = new BaseResponse(Status.SUCCESS, "Success", this.bookService.pagingBook(pageable, payload));
            headers.add("TRUONG", "DEPTRAI");

            // Đặt header Content-Type là JSON
            headers.setContentType(MediaType.APPLICATION_JSON);
        } catch (Exception e) {
            response = new BaseResponse(Status.ERROR, e.getMessage());
        }
        return ResponseEntity.ok().headers(headers).body(response);
    }
}
