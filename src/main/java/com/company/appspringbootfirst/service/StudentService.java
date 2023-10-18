package com.company.appspringbootfirst.service;

import com.company.appspringbootfirst.dto.StudentDTO;
import com.company.appspringbootfirst.model.Student;
import org.springframework.http.HttpEntity;

import java.util.List;

public interface StudentService {
    List<Student> getAll();

    HttpEntity<Student> getById(Integer id);

    HttpEntity<?> delete(Integer id);

    HttpEntity<?> save(StudentDTO studentDTO);

    HttpEntity<?> edit(Integer id, StudentDTO studentDTO);
}
