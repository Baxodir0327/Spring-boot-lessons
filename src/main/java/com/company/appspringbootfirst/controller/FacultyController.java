package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.model.Faculty;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/faculty")
public interface FacultyController extends BaseController<Faculty>{
}
