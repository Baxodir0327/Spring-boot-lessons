package com.company.appspringbootfirst.service;

import com.company.appspringbootfirst.dao.UniversityDAO;
import com.company.appspringbootfirst.model.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityDAO universityDAO;

    @Override
    public List<University> getAll() {
        return universityDAO.getAll();
    }

    @Override
    public Optional<University> getById(Integer id) {
        return universityDAO.getById(id);
    }

    @Override
    public Optional<University> add(University university) {
        if (universityDAO.existsByName(university.getName()))
            return Optional.empty();

        university = universityDAO.save(university);

        return Optional.of(university);
    }

    @Override
    public Optional<University> edit(Integer id, University university) {
        Optional<University> optionalUniversity = universityDAO.getById(id);
        if (optionalUniversity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(universityDAO.edit(id, university));
    }

    @Override
    public boolean deleteById(Integer id) {
        return universityDAO.delete(id);
    }
}
