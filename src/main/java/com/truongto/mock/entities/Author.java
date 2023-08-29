package com.truongto.mock.entities;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "author")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "biography", length = 1000, nullable = true)
    private String biography;

    @Column(name = "pathImage", length = 1000, nullable = true)
    private String pathImage;

    @Column(name = "nationality", length = 100, nullable = false)
    private String nationality;

    @Column(name = "dateOfBirth", length = 100, nullable = true)
    private String dateOfBirth;

    @Column(name = "dateOfDeath", length = 100, nullable = true)
    private String dateOfDeath;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;

    @CreationTimestamp
    @Column(name = "created_at", nullable = true, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @CreatedBy
    @Column(name = "created_by", nullable = true, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by", nullable = true)
    private String updatedBy;

    public Author() {
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        for (Book book : books) {
            book.setAuthor(this);
        }
    }

    public Author(Long id, String name, String biography, String pathImage, String nationality, String dateOfBirth,
            String dateOfDeath) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.pathImage = pathImage;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

}
