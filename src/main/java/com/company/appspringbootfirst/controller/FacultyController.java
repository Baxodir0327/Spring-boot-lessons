package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.dao.FacultyDAO;
import com.company.appspringbootfirst.dto.FacultyDTO;
import com.company.appspringbootfirst.model.Faculty;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/faculty")
public interface FacultyController {
    @GetMapping
    HttpEntity<List<Faculty>> list();

    @GetMapping("/{id}")
    HttpEntity<Faculty> one(@PathVariable Integer id);

    @PostMapping
    HttpEntity<?> add(@RequestBody FacultyDTO facultyDTO);

    @PutMapping("/{id}")
    HttpEntity<Faculty> update(@PathVariable Integer id,
                         @RequestBody FacultyDTO facultyDTO);

    @DeleteMapping("/{id}")
    HttpEntity<?> delete(@PathVariable Integer id);
}
