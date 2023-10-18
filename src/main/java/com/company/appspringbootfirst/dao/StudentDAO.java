package com.company.appspringbootfirst.dao;

import com.company.appspringbootfirst.dto.StudentDTO;
import com.company.appspringbootfirst.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    List<Student> all();

    Optional<Student> getById(Integer id);

    boolean delete(Integer id);

    Student add(StudentDTO studentDTO);

    Student edit(Integer id, StudentDTO studentDTO);
}
