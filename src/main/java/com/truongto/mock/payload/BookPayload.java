package com.truongto.mock.payload;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class BookPayload {
    
    private String title;
    private String description;
    private String publicationDate;
    private String authorName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private List<String> statuses;


    public BookPayload() {
    }

    public BookPayload(String title, String description, String publicationDate, String authorName) {
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.authorName = authorName;
    }

}
