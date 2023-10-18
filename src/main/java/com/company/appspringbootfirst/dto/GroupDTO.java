package com.company.appspringbootfirst.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDTO {
    Integer id;

    String name;

    private Integer facultyId;
}
