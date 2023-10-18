package com.company.appspringbootfirst.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyDTO {
    Integer id;

    String name;

    private Integer universityId;
}
