package com.company.appspringbootfirst.dao;

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
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UniversityDAOImpl implements UniversityDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<University> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM university",
                BeanPropertyRowMapper.newInstance(University.class));
    }

    @Override
    public Optional<University> getById(Integer id) {
        try {
            University university = jdbcTemplate.queryForObject("SELECT * FROM university WHERE id = :id",
                    new MapSqlParameterSource("id", id),
                    BeanPropertyRowMapper.newInstance(University.class));
            return Optional.ofNullable(university);
        } catch (DataAccessException e) {
            return Optional.empty();
        }

    }

    public boolean existsByName(String name) {
        try {
            University university = jdbcTemplate.queryForObject("SELECT * FROM university WHERE name = :name",
                    new MapSqlParameterSource("name", name),
                    BeanPropertyRowMapper.newInstance(University.class));
            return university != null;
        } catch (DataAccessException e) {
            return false;
        }
    }


    @Override
    public University save(University university) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update("INSERT INTO university(name) VALUES(:name)",
                new MapSqlParameterSource("name", university.getName()),
                keyHolder, new String[]{"id", "name"});

        Integer id = (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        String name = (String) Objects.requireNonNull(keyHolder.getKeys()).get("name");

        return University.builder()
                .id(id)
                .name(name)
                .build();

    }

    @Override
    public University edit(Integer id, University university) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("UPDATE university SET name=:name WHERE id=:id",
                new MapSqlParameterSource("name", university.getName())
                        .addValue("id",id),
                keyHolder, new String[]{"id"});

        Integer id1 = (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");

        return University.builder()
                .id(id1)
                .name(university.getName())
                .build();
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("delete from university where id = :id", new MapSqlParameterSource("id", id)) > 0;
    }
}
