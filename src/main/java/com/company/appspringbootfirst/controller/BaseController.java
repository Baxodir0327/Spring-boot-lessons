package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.model.University;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseController<T> {
    @GetMapping
    HttpEntity<List<T>> list();

    @GetMapping("/{id}")
    HttpEntity<T> one(@PathVariable Integer id);

    @PostMapping
    HttpEntity<?> add(@RequestBody T t);

    @PutMapping("/{id}")
    HttpEntity<T> update(@PathVariable Integer id,
                         @RequestBody T t);

    @DeleteMapping("/{id}")
    HttpEntity<?> delete(@PathVariable Integer id);


}
