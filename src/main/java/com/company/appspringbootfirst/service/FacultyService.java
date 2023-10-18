package com.company.appspringbootfirst.service;

import com.company.appspringbootfirst.dto.FacultyDTO;
import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.University;

import java.util.List;
import java.util.Optional;

public interface FacultyService {
    List<Faculty> getAll();
    Optional<Faculty> getById(Integer id);
    Optional<Faculty> add(FacultyDTO facultyDTO);
    Optional<Faculty> edit(Integer id, FacultyDTO facultyDTO);
    boolean deleteById(Integer id);
}
