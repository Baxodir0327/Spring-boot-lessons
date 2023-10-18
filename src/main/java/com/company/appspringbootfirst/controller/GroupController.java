package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.dto.FacultyDTO;
import com.company.appspringbootfirst.dto.GroupDTO;
import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.Group;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/group")
public interface GroupController {
    @GetMapping
    HttpEntity<List<Group>> list();

    @GetMapping("/{id}")
    HttpEntity<Group> one(@PathVariable Integer id);

    @PostMapping
    HttpEntity<?> add(@RequestBody GroupDTO groupDTO);

    @PutMapping("/{id}")
    HttpEntity<Group> update(@PathVariable Integer id,
                         @RequestBody GroupDTO groupDTO);

    @DeleteMapping("/{id}")
    HttpEntity<?> delete(@PathVariable Integer id);
}
