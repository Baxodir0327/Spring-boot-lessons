package com.company.appspringbootfirst.dao;

import com.company.appspringbootfirst.dto.GroupDTO;
import com.company.appspringbootfirst.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupDAO {
    List<Group> getAll();

    Optional<Group> getById(Integer id);

    Group save(GroupDTO groupDTO);

    Optional<Group> edit(Integer id, GroupDTO groupDTO);

    boolean delete(Integer id);

    boolean existByNameAndFacultyId(GroupDTO groupDTO);
}
