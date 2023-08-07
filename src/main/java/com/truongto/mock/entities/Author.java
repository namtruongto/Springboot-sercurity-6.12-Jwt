package com.truongto.mock.entities;

import java.util.List;

import com.truongto.mock.dtos.AuthorDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    public Author() {
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

    public AuthorDto toDto(){

        return new AuthorDto(
        this.name, 
        this.biography,
        this.pathImage,
        this.nationality,
        this.dateOfBirth,
        this.dateOfDeath);
    }

}
