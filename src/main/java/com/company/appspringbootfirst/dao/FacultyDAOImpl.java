package com.company.appspringbootfirst.dao;

import com.company.appspringbootfirst.dao.mapper.FacultyMapper;
import com.company.appspringbootfirst.dto.FacultyDTO;
import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.University;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FacultyDAOImpl implements FacultyDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UniversityDAOImpl universityDAO;
    private final FacultyMapper facultyMapper;

    @Override
    public List<Faculty> getAll() {
        return jdbcTemplate.query("SELECT * FROM faculty", facultyMapper);
    }

    @Override
    public Optional<Faculty> getById(Integer id) {
        try {
            Faculty faculty = jdbcTemplate.queryForObject("SELECT * from faculty WHERE id=:id", new MapSqlParameterSource("id", id), facultyMapper);
            return Optional.ofNullable(faculty);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public Faculty save(FacultyDTO facultyDTO) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramSource = new MapSqlParameterSource("name", facultyDTO.getName())
                .addValue("universityId", facultyDTO.getUniversityId());

        jdbcTemplate.update("INSERT INTO faculty(name, university_id) VALUES(:name,:universityId)",
                paramSource,
                keyHolder, new String[]{"id"});

        Integer id = (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        University university = universityDAO.getById(facultyDTO.getUniversityId()).orElse(null);

        return Faculty.builder()
                .id(id)
                .name(facultyDTO.getName())
                .university(university)
                .build();
    }

    @Override
    public Optional<Faculty> edit(Integer id, FacultyDTO facultyDTO) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("name", facultyDTO.getName())
                .addValue("university_id", facultyDTO.getUniversityId())
                .addValue("id", id);

        jdbcTemplate.update("UPDATE faculty SET name=:name , university_id=:university_id WHERE id=:id ",
                parameterSource);

        University university = universityDAO.getById(facultyDTO.getUniversityId()).get();
        return Optional.of(Faculty.builder()
                .id(id)
                .name(facultyDTO.getName())
                .university(university)
                .build());
    }


    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM faculty WHERE id=:id", Map.of("id", id)) > 0;
    }

    @Override
    public boolean existByNameAndUniversityId(FacultyDTO facultyDTO) {
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource("name", facultyDTO.getName())
                    .addValue("university_id", facultyDTO.getUniversityId());

            Faculty faculty = jdbcTemplate.queryForObject("SELECT * FROM faculty WHERE name = :name AND university_id=:university_id",
                    paramSource,
                    BeanPropertyRowMapper.newInstance(Faculty.class));

            return faculty != null;
        } catch (DataAccessException e) {
            return false;
        }
    }
}

