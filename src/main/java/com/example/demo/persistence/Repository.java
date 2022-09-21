package com.example.demo.persistence;

import java.util.List;

public interface Repository {

    <T> void persist(T entity);
    <T> T findById(Class<T> clazz, long id);
    <T> T update(T type);
    <T> List<T> findAll(Class<T> type);
    <T> void deleteById(Class<T> clazz, long l);
    <T, I> List<I> findAllDtos(Class<T> clazz, Class<I> dtoClazz);
}
