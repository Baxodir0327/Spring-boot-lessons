package com.company.appspringbootfirst.service;

import com.company.appspringbootfirst.dao.FacultyDAO;
import com.company.appspringbootfirst.dao.UniversityDAO;
import com.company.appspringbootfirst.dao.UniversityDAOImpl;
import com.company.appspringbootfirst.dto.FacultyDTO;
import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final FacultyDAO facultyDAO;
    private final UniversityDAOImpl universityDAO;


    @Override
    public List<Faculty> getAll() {
        return facultyDAO.getAll();
    }

    @Override
    public Optional<Faculty> getById(Integer id) {
        return facultyDAO.getById(id);
    }

    @Override
    public Optional<Faculty> add(FacultyDTO facultyDTO) {
        Optional<University> optionalUniversity = universityDAO.getById(facultyDTO.getUniversityId());

        if (optionalUniversity.isEmpty())
            return Optional.empty();

        if (facultyDAO.existByNameAndUniversityId(facultyDTO))
            return Optional.empty();

        Faculty faculty = facultyDAO.save(facultyDTO);
        return Optional.of(faculty);
    }

    @Override
    public Optional<Faculty> edit(Integer id, FacultyDTO facultyDTO) {
        if (universityDAO.getById(facultyDTO.getUniversityId()).isEmpty() || facultyDAO.getById(id).isEmpty()) {
            return Optional.empty();
        }
        return facultyDAO.edit(id, facultyDTO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return facultyDAO.delete(id);
    }
}
