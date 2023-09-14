package com.truongto.mock.services.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.truongto.mock.entities.Author;
import com.truongto.mock.entities.Book;
import com.truongto.mock.payload.BookPayload;
import com.truongto.mock.repositories.BookRepository;
import com.truongto.mock.services.BookService;
import com.truongto.mock.thfw.exceptions.NotFoundException;

import jakarta.persistence.criteria.Predicate;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Page<Book> pagingBook(Pageable pageable, BookPayload payload) {

        Specification<Book> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Xử lý các điều kiện tìm kiếm trong payload
            if (payload.getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + payload.getTitle() + "%"));
            }
            if (payload.getDescription() != null) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + payload.getDescription() + "%"));
            }
            if (payload.getPublicationDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("publicationDate"), payload.getPublicationDate()));
            }
            if (payload.getAuthorName() != null) {
                predicates
                        .add(criteriaBuilder.like(root.get("author").get("name"), "%" + payload.getAuthorName() + "%"));
            }
            if (payload.getMinPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), payload.getMinPrice()));
            }
            if (payload.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), payload.getMaxPrice()));
            }
            if (payload.getStatuses() != null && payload.getStatuses().size() > 0) {
                predicates.add(root.get("status").in(payload.getStatuses()));
            }
            // if (payload.getTitle() != null) {
            // Predicate orPredicate = criteriaBuilder.or(
            // criteriaBuilder.like(root.get("title"), "%" + payload.getTitle() + "%"),
            // criteriaBuilder.like(root.get("description"), "%" + payload.getTitle() + "%")
            // );
            // predicates.add(orPredicate);
            // }
            // Kết hợp các điều kiện với AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        // Thực hiện truy vấn với Specification và Pageable
        Page<Book> page = bookRepository.findAll(spec, pageable).map(book -> {
            if (book.getAuthor() != null)
                book.setAuthor(new Author(book.getAuthor().getId(), book.getAuthor().getName(),
                        book.getAuthor().getBiography(), book.getAuthor().getPathImage(),
                        book.getAuthor().getNationality(), book.getAuthor().getDateOfBirth(),
                        book.getAuthor().getDateOfDeath()));

            return book;
        });

        return page;
    }

    @Override
    public Book getBookById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sách với id: " + id));
    }

    @Override
    public Book createBook(Book payload) {
        return bookRepository.save(payload);
    }

    @Override
    public Book updateBook(Book payload) {
        return bookRepository.save(payload);
    }

}
