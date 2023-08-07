package com.truongto.mock.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students extends Person{

    @Column(name = "studentCode", length = 10)
    private String studentCode;

    @Column(name = "class", length = 10)
    private String classStudent;

    @Override
    public String toString() {
        return "Students [classStudent=" + classStudent + ", studentCode=" + studentCode + "" + super.toString() +"]";
    }

}
