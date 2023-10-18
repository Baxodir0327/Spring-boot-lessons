package com.company.appspringbootfirst.dao.mapper;

import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.University;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FacultyMapper implements RowMapper<Faculty> {
    @Override
    public Faculty mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Faculty.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .university(University
                        .builder()
                        .name(rs.getString("u_id"))
                        .id(rs.getInt("u_id")).build())
                .build();
    }
}
