package com.truongto.mock.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.truongto.mock.entities.Book;
import com.truongto.mock.payload.BookPayload;

public interface BookService {

    Page<Book> pagingBook(Pageable pageable, BookPayload payload);
    Book getBookById(Long id);
    Book createBook(Book payload);
    Book updateBook(Long id, BookPayload payload);
    void deleteBook(Long id);
}
