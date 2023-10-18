package com.company.appspringbootfirst.dao;

import com.company.appspringbootfirst.dao.mapper.StudentMapper;
import com.company.appspringbootfirst.dto.StudentDTO;
import com.company.appspringbootfirst.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class StudentDAOImpl implements StudentDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final StudentMapper studentMapper;
    private final GroupDAO groupDAO;

    @Override
    public List<Student> all() {
        return jdbcTemplate.query("SELECT * from student", studentMapper);
    }

    @Override
    public Optional<Student> getById(Integer id) {
        try {
            Student student = jdbcTemplate.queryForObject("SELECT * from student WHERE id=:id", Map.of("id", id), studentMapper);
            return Optional.ofNullable(student);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM student WHERE id=:id", Map.of("id", id)) > 0;
    }

    @Override
    public Student add(StudentDTO studentDTO) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramSource = new MapSqlParameterSource("name", studentDTO.getName())
                .addValue("group_id", studentDTO.getGroupId());

        jdbcTemplate.update("INSERT INTO student(name, group_id) VALUES(:name,:group_id)",
                paramSource,
                keyHolder, new String[]{"id"});

        Integer id = (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");

        return Student.builder()
                .id(id)
                .name(studentDTO.getName())
                .group(groupDAO.getById(studentDTO.getGroupId()).orElse(null))
                .build();

    }

    @Override
    public Student edit(Integer id, StudentDTO studentDTO) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource("name", studentDTO.getName())
                .addValue("id", id)
                .addValue("group_id", studentDTO.getGroupId());

        jdbcTemplate.update("UPDATE student SET name=:name,group_id=:group_id WHERE id=:id",
                paramSource);

        return Student.builder()
                .id(id)
                .name(studentDTO.getName())
                .group(groupDAO.getById(studentDTO.getGroupId()).orElse(null))
                .build();
    }
}
