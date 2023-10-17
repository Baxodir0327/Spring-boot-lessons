package com.company.appspringbootfirst.controller;

import com.company.appspringbootfirst.model.Group;
import com.company.appspringbootfirst.model.University;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/group")
public interface GroupController extends BaseController<Group> {

}
