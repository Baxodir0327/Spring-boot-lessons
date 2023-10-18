package com.company.appspringbootfirst.dao.mapper;

import com.company.appspringbootfirst.dao.UniversityDAO;
import com.company.appspringbootfirst.dao.UniversityDAOImpl;
import com.company.appspringbootfirst.model.Faculty;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
@RequiredArgsConstructor
public class FacultyMapper implements RowMapper<Faculty> {
    private final UniversityDAO universityDAO;

    @Override
    public Faculty mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Faculty.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .university(universityDAO.getById(rs.getInt("university_id")).get())
                .build();
    }
}
