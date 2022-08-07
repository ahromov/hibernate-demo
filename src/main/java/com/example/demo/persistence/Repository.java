package com.example.demo.persistence;

import java.util.List;
import java.util.function.Consumer;

public interface Repository {

    <T> void persist(T entity);
    <T> T findById(Class<T> clazz, long id);
    <T> void update(T type);
    <T> T update(Class<T> clazz, long id, Consumer<T> function);
    <T> List<T> findAll(Class<T> type);
    <T> void deleteById(Class<T> clazz, long l);
}
