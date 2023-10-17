package com.company.appspringbootfirst.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    List<T> getAll();

    Optional<T> getById(Integer id);

    T save(T t);

    T edit(Integer id, T t);

    boolean delete(Integer id);


}
