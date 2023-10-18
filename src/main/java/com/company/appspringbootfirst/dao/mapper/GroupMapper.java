package com.company.appspringbootfirst.dao.mapper;

import com.company.appspringbootfirst.dao.FacultyDAO;
import com.company.appspringbootfirst.dao.UniversityDAO;
import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class GroupMapper implements RowMapper<Group> {
    private final FacultyDAO facultyDAO;

    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Group.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .faculty(facultyDAO.getById(rs.getInt("faculty_id")).get())
                .build();
    }
}
