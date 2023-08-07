package com.truongto.mock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truongto.mock.dtos.BaseResponse;
import com.truongto.mock.entities.Students;
import com.truongto.mock.services.StudentService;
import com.truongto.mock.thfw.enums.Enums.Status;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<BaseResponse> getStudents() {
        BaseResponse response;
        try {
            response = new BaseResponse(Status.SUCCESS, "Get students successfully", this.studentService.getAllStudents());
            
        } catch (Exception e) {
            response = new BaseResponse(Status.ERROR, "Get students failed", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getStudentById(@PathVariable("id") Long id) {
        BaseResponse response;
        try {
            response = new BaseResponse(Status.SUCCESS, "Get student successfully", this.studentService.getStudentById(id));
            
        } catch (Exception e) {
            response = new BaseResponse(Status.ERROR, "Get student failed", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createStudent(@RequestBody Students student) {
        BaseResponse response;
        try {
            response = new BaseResponse(Status.SUCCESS, "Create student successfully", this.studentService.createStudent(student));
        } catch (DataIntegrityViolationException e) {
            response = new BaseResponse(Status.ERROR, "username hoặc email đã tồn tại");
        } catch (Exception e) {
            response = new BaseResponse(Status.ERROR, "Ngoại lệ chưa xác định", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse> updateStudent(@PathVariable("id") Long id, @RequestBody Students student) {
        BaseResponse response;
        try {
            response = new BaseResponse(Status.SUCCESS, "Update student successfully", this.studentService.updateStudent(id, student));
            
        } catch (Exception e) {
            response = new BaseResponse(Status.ERROR, "Update student failed", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    
    
}
