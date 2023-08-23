package com.truongto.mock.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truongto.mock.entities.Author;
import com.truongto.mock.repositories.AuthorRepository;
import com.truongto.mock.services.AuthorService;
import com.truongto.mock.thfw.exceptions.NotFoundException;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author getAuthorById(Long id) {
        return this.authorRepository.findById(id).map(author -> {
            author.getBooks().forEach(book -> {
                book.setAuthor(null);
            });
            return author;
        }).orElseThrow(() -> new NotFoundException("Không tìm thấy tác giả với id: " + id));
    }

    @Override
    public Author createAuthor(Author author) {
        Author authorCreated = this.authorRepository.save(author);
        authorCreated.getBooks().forEach(book -> {
            book.setAuthor(null);
        });
        return authorCreated;
    }

    @Override
    public Author updateAuthor(Author author) {
        Optional<Author> authorInDB = this.authorRepository.findById(author.getId());
        if (authorInDB.isPresent()) {
            Author authorUpdate = authorInDB.get();
            if (author.getName() != null) {
                authorUpdate.setName(author.getName());
            }
            if (author.getDateOfBirth() != null) {
                authorUpdate.setDateOfBirth(author.getDateOfBirth());
            }
            if (author.getDateOfDeath() != null) {
                authorUpdate.setDateOfDeath(author.getDateOfDeath());
            }
            if (author.getBiography() != null) {
                authorUpdate.setBiography(author.getBiography());
            }
            if (author.getNationality() != null) {
                authorUpdate.setNationality(author.getNationality());
            }
            Author authorUpdated = this.authorRepository.save(authorUpdate);
            authorUpdated.getBooks().forEach(book -> {
                book.setAuthor(null);
            });
            return authorUpdated;

        } else {
            throw new NotFoundException("Không tìm thấy tác giả với id: " + author.getId());
        }

        
    }

    @Override
    public void deleteAuthor(Long id) {
        this.authorRepository.deleteById(id);
    }

}
