package com.truongto.mock.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDto {
    String name;
    String biography;
    String pathImage;
    String nationality;
    String dateOfBirth;
    String dateOfDeath;
}
