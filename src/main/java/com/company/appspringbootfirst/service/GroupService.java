package com.company.appspringbootfirst.service;

import com.company.appspringbootfirst.dto.FacultyDTO;
import com.company.appspringbootfirst.dto.GroupDTO;
import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    List<Group> getAll();
    Optional<Group> getById(Integer id);
    Optional<Group> add(GroupDTO groupDTO);
    Optional<Group> edit(Integer id, GroupDTO groupDTO);
    boolean deleteById(Integer id);
}
