package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.model.University;
import com.company.appspringbootfirst.service.UniversityService;
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
    private final UniversityService universityService;

    @Override
    public HttpEntity<List<University>> list() {
        return new ResponseEntity<>(universityService.getAll(), HttpStatus.OK);
    }

    @Override
    public HttpEntity<University> one(Integer id) {
        Optional<University> optionalUniversity = universityService.getById(id);

        return optionalUniversity
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Override
    public HttpEntity<?> add(University university) {
        Optional<University> optionalUniversity = universityService.add(university);
        if (optionalUniversity.isEmpty()) {
            return new ResponseEntity<>("OKA university already exist", HttpStatus.CONFLICT);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(optionalUniversity.get());
    }

    @Override
    public HttpEntity<University> update(Integer id, University university) {
        Optional<University> optionalUniversity = universityService.edit(id, university);

        return optionalUniversity
                .map(un -> ResponseEntity.accepted().body(un))
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
