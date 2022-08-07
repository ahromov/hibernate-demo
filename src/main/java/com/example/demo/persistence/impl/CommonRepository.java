package com.example.demo.persistence.impl;

import com.example.demo.persistence.Repository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


@Component
public class CommonRepository implements Repository {

    private final EntityManagerFactory sessionFactory;

    public CommonRepository(EntityManagerFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public <T> void persist(T entity) {
        doInsession(entityManager -> {
            entityManager.persist(entity);
            return null;
        });
    }

    @Override
    public <T> T findById(Class<T> clazz, long id) {
        return doInsession(em -> em.find(clazz, id));
    }

    @Override
    public <T> void update(T entity) {
        doInsession(entityManager -> {
            T merged = entityManager.merge(entity);
            entityManager.flush();
            return merged;
        });
    }

    @Override
    public <T> T update(Class<T> clazz, long id, Consumer<T> function) {
        return doInsession(entityManager -> {
            T finded = entityManager.find(clazz, id);
            function.accept(finded);
            return finded;
        });
    }

    @Override
    public <T> void deleteById(Class<T> clazz, long id) {
        doInsession(entityManager -> {
            T entity = entityManager.find(clazz, id);
            entityManager.remove(entity);
            return entity;
        });
    }

    @Override
    public <T> List findAll(Class<T> type) {
        return doInsession(entityManager ->
                entityManager.createQuery("select p from " + type.getSimpleName() + " p")
                        .getResultList());
    }

    private <T> T doInsession(Function<EntityManager, T> function) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        T apply = function.apply(entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
        return apply;
    }
}
