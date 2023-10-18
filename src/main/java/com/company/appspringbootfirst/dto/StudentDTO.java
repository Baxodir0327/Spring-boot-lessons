package com.company.appspringbootfirst.dto;

import com.company.appspringbootfirst.dao.StudentDAOImpl;
import com.company.appspringbootfirst.model.Faculty;
import com.company.appspringbootfirst.model.Group;
import com.company.appspringbootfirst.model.Student;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDTO {
    private Integer id;
    private String name;
    private Integer groupId;


}