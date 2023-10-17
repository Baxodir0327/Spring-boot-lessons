package com.company.appspringbootfirst.service;

import com.company.appspringbootfirst.model.University;

import java.util.List;
import java.util.Optional;

public interface UniversityService {
    List<University> getAll();
    Optional<University> getById(Integer id);
    Optional<University> add(University university);
    Optional<University> edit(Integer id, University university);
    boolean deleteById(Integer id);
}
