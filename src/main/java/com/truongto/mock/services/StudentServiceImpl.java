package com.truongto.mock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.truongto.mock.entities.Students;
import com.truongto.mock.repositories.StudentRepository;
import com.truongto.mock.thfw.exceptions.NotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Students getStudentById(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy sinh viên với id = " + id));
    }

    @Override
    public Students createStudent(Students student) {
        String password = student.getPassword();
        password = passwordEncoder.encode(password);
        student.setPassword(password);
        return studentRepository.save(student);
    }

    @Override
    public Students updateStudent(Long id, Students student) {
        Students studentInDb = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy sinh viên với id = " + id));
        student.setId(studentInDb.getId());
        student.setPassword(studentInDb.getPassword());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

}
