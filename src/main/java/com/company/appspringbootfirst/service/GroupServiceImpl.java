package com.company.appspringbootfirst.service;

import com.company.appspringbootfirst.dao.FacultyDAO;
import com.company.appspringbootfirst.dao.GroupDAO;
import com.company.appspringbootfirst.dto.GroupDTO;
import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupDAO groupDAO;
    private final FacultyDAO facultyDAO;


    @Override
    public List<Group> getAll() {
        return groupDAO.getAll();
    }

    @Override
    public Optional<Group> getById(Integer id) {
        return groupDAO.getById(id);
    }

    @Override
    public Optional<Group> add(GroupDTO groupDTO) {
        Optional<Faculty> optionalFaculty = facultyDAO.getById(groupDTO.getFacultyId());

        if (optionalFaculty.isEmpty())
            return Optional.empty();

        if (groupDAO.existByNameAndFacultyId(groupDTO))
            return Optional.empty();

        Group group = groupDAO.save(groupDTO);
        return Optional.of(group);
    }

    @Override
    public Optional<Group> edit(Integer id, GroupDTO groupDTO) {
        if (facultyDAO.getById(groupDTO.getFacultyId()).isEmpty() || groupDAO.getById(id).isEmpty()) {
            return Optional.empty();
        }
        return groupDAO.edit(id, groupDTO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return groupDAO.delete(id);
    }
}
