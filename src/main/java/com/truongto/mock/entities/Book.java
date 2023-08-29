package com.truongto.mock.entities;

import java.math.BigDecimal;
import java.util.Date;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "price", columnDefinition = "decimal(10, 2)")
    private BigDecimal price;

    @Column(name = "status", nullable = true)
    private String status;

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




    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", description=" + description + ", publicationDate="
                + publicationDate + ", author=" + author + ", price=" + price + ", status=" + status + "]";
    }

}
