package com.company.appspringbootfirst.dao;

import com.company.appspringbootfirst.dao.mapper.GroupMapper;
import com.company.appspringbootfirst.dto.GroupDTO;
import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.Group;
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
public class GroupDAOImpl implements GroupDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final FacultyDAO facultyDAO;
    private final GroupMapper groupMapper;

    @Override
    public List<Group> getAll() {
        return jdbcTemplate.query("""
                        SELECT g.id as id, g.name as name, f.id as f_id, f.name as f_name, u.id as u_id, u.name as u_name
                        from groups g
                                 inner join faculty f on g.faculty_id = f.id
                                 inner join university u on u.id = f.university_id"""
                , groupMapper);
    }

    @Override
    public Optional<Group> getById(Integer id) {
        try {
            Group group = jdbcTemplate.queryForObject("""
                    SELECT g.id as id, g.name as name, f.id as f_id, f.name as f_name, u.id as u_id, u.name as u_name
                    from groups g
                             inner join faculty f on g.faculty_id = f.id
                             inner join university u on u.id = f.university_id
                    where f.id = :id;
                    """, new MapSqlParameterSource("id", id), groupMapper);
            return Optional.ofNullable(group);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public Group save(GroupDTO groupDTO) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramSource = new MapSqlParameterSource("name", groupDTO.getName())
                .addValue("faculty_id", groupDTO.getFacultyId());

        jdbcTemplate.update("INSERT INTO groups(name, faculty_id) VALUES(:name,:faculty_id)",
                paramSource,
                keyHolder, new String[]{"id"});

        Integer id = (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        Faculty faculty = facultyDAO.getById(groupDTO.getFacultyId()).orElse(null);

        return Group.builder()
                .id(id)
                .name(groupDTO.getName())
                .faculty(faculty)
                .build();
    }

    @Override
    public Optional<Group> edit(Integer id, GroupDTO groupDTO) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("name", groupDTO.getName())
                .addValue("faculty_id", groupDTO.getFacultyId())
                .addValue("id", id);

        jdbcTemplate.update("UPDATE groups SET name=:name , faculty_id=:faculty_id WHERE id=:id ",
                parameterSource);

        Faculty faculty = facultyDAO.getById(groupDTO.getFacultyId()).get();
        return Optional.of(Group.builder()
                .id(id)
                .name(groupDTO.getName())
                .faculty(faculty)
                .build());
    }


    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM groups WHERE id=:id", Map.of("id", id)) > 0;
    }

    @Override
    public boolean existByNameAndFacultyId(GroupDTO groupDTO) {
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource("name", groupDTO.getName())
                    .addValue("faculty_id", groupDTO.getFacultyId());

            Group group = jdbcTemplate.queryForObject("SELECT * FROM groups WHERE name = :name and faculty_id=:faculty_id",
                    paramSource,
                    BeanPropertyRowMapper.newInstance(Group.class));

            return group != null;
        } catch (DataAccessException e) {
            return false;
        }
    }
}

