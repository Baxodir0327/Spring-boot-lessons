package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.model.University;
import com.company.appspringbootfirst.service.UniversityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UniversityControllerImpl implements UniversityController {
    private final UniversityServiceImpl universityService;

    @Override
    public HttpEntity<List<University>> list() {
        return new ResponseEntity<>(universityService.getAll(), HttpStatus.OK);
    }

    @Override
    public HttpEntity<University> one(Integer id) {
        Optional<University> optionalUniversity = universityService.getById(id);

        return optionalUniversity
                .map(university -> new ResponseEntity<>(university, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @Override
    public HttpEntity<?> add(University university) {
        Optional<University> optionalUniversity = universityService.add(university);
        if (optionalUniversity.isEmpty())
            return new ResponseEntity<>("OKa university already exists", HttpStatus.CONFLICT);

        return ResponseEntity.status(HttpStatus.CREATED).body(optionalUniversity.get());

    }

    @Override
    public HttpEntity<University> update(Integer id, University university) {
        Optional<University> optionalUniversity = universityService.edit(id, university);

        return optionalUniversity
                .map(university1 -> ResponseEntity.accepted().body(university1))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Override
    public HttpEntity<?> delete(Integer id) {
        boolean deleted = universityService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();

    }
}
