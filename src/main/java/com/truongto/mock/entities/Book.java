package com.truongto.mock.entities;

import java.math.BigDecimal;
import java.util.Date;

import com.truongto.mock.dtos.BookDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "publication_date")
    private Date publicationDate;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name="price", nullable = true, columnDefinition = "decimal(10, 2) default '0.00'")
    private BigDecimal price;

    @Column(name = "status", nullable = true)
    private String status;

    public Book() {
    }

    public BookDto toDto() {
        BookDto dto = new BookDto();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setDescription(this.description);
        dto.setPublicationDate(this.publicationDate);
        dto.setAuthor(this.author.toDto());
        dto.setPrice(this.price);
        dto.setStatus(this.status);
        return dto;
    }
    
}
