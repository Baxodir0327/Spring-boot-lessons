package com.company.appspringbootfirst.dao.mapper;

import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.Group;
import com.company.appspringbootfirst.model.University;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Group.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .faculty(Faculty.builder()
                        .id(rs.getInt("f_id"))
                        .name(rs.getString("f_name"))
                        .university(University.builder()
                                .id(rs.getInt("u_id"))
                                .name(rs.getString("u_name"))
                                .build()

                        ).build())
                .build();

    }
}
