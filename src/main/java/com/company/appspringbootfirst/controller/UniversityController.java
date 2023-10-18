package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.model.University;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/university")
public interface UniversityController{
    @GetMapping
    HttpEntity<List<University>> list();

    @GetMapping("/{id}")
    HttpEntity<University> one(@PathVariable Integer id);

    @PostMapping
    HttpEntity<?> add(@RequestBody University university);

    @PutMapping("/{id}")
    HttpEntity<University> update(@PathVariable Integer id,
                         @RequestBody University university);

    @DeleteMapping("/{id}")
    HttpEntity<?> delete(@PathVariable Integer id);

}
