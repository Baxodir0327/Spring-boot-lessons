package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.dto.FacultyDTO;
import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.service.FacultyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class FacultyControllerImpl implements FacultyController {
    private final FacultyServiceImpl facultyService;

    @Override
    public HttpEntity<List<Faculty>> list() {
        return new ResponseEntity<>(facultyService.getAll(), HttpStatus.OK);
    }

    @Override
    public HttpEntity<Faculty> one(Integer id) {
        Optional<Faculty> optionalFaculty = facultyService.getById(id);
        return optionalFaculty.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @Override
    public HttpEntity<?> add(FacultyDTO facultyDTO) {
        Optional<Faculty> optionalFaculty = facultyService.add(facultyDTO);
        if (optionalFaculty.isEmpty()) {
            return new ResponseEntity<>("OKA namdur chota xato ", HttpStatus.CONFLICT);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(optionalFaculty.get());
    }

    @Override
    public HttpEntity<Faculty> update(Integer id, FacultyDTO facultyDTO) {
        Optional<Faculty> optionalFaculty = facultyService.edit(id, facultyDTO);
        return optionalFaculty
                .map(faculty -> ResponseEntity.accepted().body(faculty))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Override
    public HttpEntity<?> delete(Integer id) {
        if (!facultyService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
