package com.company.appspringbootfirst.dao;

import com.company.appspringbootfirst.model.University;

import java.util.List;
import java.util.Optional;

public interface UniversityDAO{
    List<University> getAll();

    Optional<University> getById(Integer id);

    University save(University t);

    University edit(Integer id, University t);

    boolean delete(Integer id);
    boolean existsByName(String name);

}
