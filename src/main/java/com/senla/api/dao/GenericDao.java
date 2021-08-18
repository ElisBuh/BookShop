package com.senla.api.dao;

import com.senla.model.AEntity;

import java.util.List;

public interface GenericDao<T extends AEntity> {

    T save(T t);
    T get(Integer id);
    List<T> getAll();
    boolean delete(T t);
    T update(T t);
}
