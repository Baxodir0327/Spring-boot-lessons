package com.company.appspringbootfirst.dao.mapper;

import com.company.appspringbootfirst.dao.FacultyDAO;
import com.company.appspringbootfirst.dao.GroupDAO;
import com.company.appspringbootfirst.model.Group;
import com.company.appspringbootfirst.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class StudentMapper implements RowMapper<Student> {
    private final GroupDAO groupDAO;

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Student.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .group(groupDAO.getById(rs.getInt("group_id")).get())
                .build();
    }
}
