package com.company.appspringbootfirst.service;

import com.company.appspringbootfirst.dao.GroupDAOImpl;
import com.company.appspringbootfirst.dao.StudentDAOImpl;
import com.company.appspringbootfirst.dto.StudentDTO;
import com.company.appspringbootfirst.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentDAOImpl studentDAO;
    private final GroupDAOImpl groupDAO;

    @Override
    public List<Student> getAll() {
        return studentDAO.all();
    }

    @Override
    public HttpEntity<Student> getById(Integer id) {
        Optional<Student> studentOptional = studentDAO.getById(id);

        return studentOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public HttpEntity<?> delete(Integer id) {
        if (!studentDAO.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public HttpEntity<?> save(StudentDTO studentDTO) {
        if (groupDAO.getById(studentDTO.getGroupId()).isEmpty()) {
            return new ResponseEntity<>("Oka bunday group mavjud emas", HttpStatus.CONFLICT);
        }
        Student student = studentDAO.add(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @Override
    public HttpEntity<?> edit(Integer id, StudentDTO studentDTO) {
        if (groupDAO.getById(studentDTO.getGroupId()).isEmpty()) {
            return new ResponseEntity<>("Oka bunday group mavjud emas", HttpStatus.CONFLICT);
        }
        if (studentDAO.getById(id).isEmpty()){
            return new ResponseEntity<>("Oka bunday tabalab yoqku qattan oldiz", HttpStatus.CONFLICT);
        }
        Student student = studentDAO.edit(id,studentDTO);
        return ResponseEntity.accepted().body(student);
    }
}
