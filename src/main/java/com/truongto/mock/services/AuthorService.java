package com.truongto.mock.services;

import com.truongto.mock.entities.Author;

public interface AuthorService {

    Author getAuthorById(Long id);
    Author createAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
    
}
