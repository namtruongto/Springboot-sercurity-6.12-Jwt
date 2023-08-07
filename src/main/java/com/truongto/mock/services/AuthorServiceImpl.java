package com.truongto.mock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truongto.mock.entities.Author;
import com.truongto.mock.repositories.AuthorRepository;
import com.truongto.mock.thfw.exceptions.NotFoundException;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author getAuthorById(Long id) {
        return this.authorRepository.findById(id).map(author ->{
            author.getBooks().forEach(book -> {
                book.setAuthor(null);
            });
            return author;
        }).orElseThrow(() -> new NotFoundException("Không tìm thấy tác giả với id: " + id));
    }

    @Override
    public Author createAuthor(Author author) {
        return this.authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAuthor'");
    }

    @Override
    public void deleteAuthor(Long id) {
        this.authorRepository.deleteById(id);
    }
    
}
