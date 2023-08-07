package com.truongto.mock.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    Long id;
    String title;
    String description;
    Date publicationDate;
    AuthorDto author;
    BigDecimal price;
    String status;
}


