package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.model.Student;
import com.company.appspringbootfirst.model.University;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/student")
public interface StudentController extends BaseController<Student> {

}
