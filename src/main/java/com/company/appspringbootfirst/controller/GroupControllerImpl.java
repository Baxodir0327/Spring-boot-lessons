package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.dto.GroupDTO;
import com.company.appspringbootfirst.model.Group;
import com.company.appspringbootfirst.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GroupControllerImpl implements GroupController {
    private final GroupService groupService;

    @Override
    public HttpEntity<List<Group>> list() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @Override
    public HttpEntity<Group> one(Integer id) {
        Optional<Group> optionalFaculty = groupService.getById(id);
        return optionalFaculty.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @Override
    public HttpEntity<?> add(GroupDTO groupDTO) {
        Optional<Group> groupOptional = groupService.add(groupDTO);
        if (groupOptional.isEmpty()) {
            return new ResponseEntity<>("OKA namdur chota xato ", HttpStatus.CONFLICT);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(groupOptional.get());
    }

    @Override
    public HttpEntity<Group> update(Integer id, GroupDTO groupDTO) {
        Optional<Group> groupOptional = groupService.edit(id, groupDTO);
        return groupOptional
                .map(faculty -> ResponseEntity.accepted().body(faculty))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Override
    public HttpEntity<?> delete(Integer id) {
        if (!groupService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
