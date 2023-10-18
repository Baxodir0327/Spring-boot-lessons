package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.dto.StudentDTO;
import com.company.appspringbootfirst.model.Student;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student")
public interface StudentController {
    @GetMapping
    HttpEntity<List<Student>> list();
    @GetMapping("/{id}")
    HttpEntity<Student> one(@PathVariable Integer id);
    @DeleteMapping("/{id}")
    HttpEntity<?> delete(@PathVariable Integer id);
    @PostMapping
    HttpEntity<?> add(@RequestBody StudentDTO studentDTO);
    @PutMapping("/{id}")
    HttpEntity<?> update(@PathVariable Integer id,@RequestBody  StudentDTO studentDTO);

}
