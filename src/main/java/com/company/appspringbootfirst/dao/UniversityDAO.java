package com.company.appspringbootfirst.dao;

import com.company.appspringbootfirst.model.University;

public interface UniversityDAO extends BaseDAO<University> {
    boolean existsByName(String name);

}
