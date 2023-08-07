package com.truongto.mock.services;

import java.util.List;

import com.truongto.mock.entities.Students;

public interface StudentService {
    
    List<Students> getAllStudents();
    Students getStudentById(long id);
    Students createStudent(Students student);
    Students updateStudent(Long id, Students student);
    void deleteStudent(long id);

}
