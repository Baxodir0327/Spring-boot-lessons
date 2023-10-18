package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.dto.StudentDTO;
import com.company.appspringbootfirst.model.Student;
import com.company.appspringbootfirst.service.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
@AllArgsConstructor
public class StudentControllerImpl implements StudentController{
    private final StudentServiceImpl studentService;

    @Override
    public HttpEntity<List<Student>> list() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @Override
    public HttpEntity<Student> one(Integer id) {
        return studentService.getById(id);
    }

    @Override
    public HttpEntity<?> delete(Integer id) {
        return studentService.delete(id);
    }

    @Override
    public HttpEntity<?> add(StudentDTO studentDTO) {
        return studentService.save(studentDTO);
    }

    @Override
    public HttpEntity<?> update(Integer id,StudentDTO studentDTO) {
        return studentService.edit(id,studentDTO);
    }

}
