package com.company.appspringbootfirst.dao;

import com.company.appspringbootfirst.dto.FacultyDTO;
import com.company.appspringbootfirst.model.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyDAO {
    List<Faculty> getAll();

    Optional<Faculty> getById(Integer id);

    Faculty save(FacultyDTO facultyDTO);

    Optional<Faculty> edit(Integer id, FacultyDTO facultyDTO);

    boolean delete(Integer id);

    boolean existByNameAndUniversityId(FacultyDTO facultyDTO);
}
