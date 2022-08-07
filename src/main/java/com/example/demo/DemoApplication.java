package com.example.demo;

import com.example.demo.entity.Note;
import com.example.demo.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class DemoApplication {

    private static EntityManagerFactory sessionFactory;

    static {
        sessionFactory = Persistence.createEntityManagerFactory("com.example.demo");
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        Person person = createPersonWithNote();

        persist(person);

        findAll().forEach(o -> System.out.println(o));

        deleteById(Person.class, 1);

        findAll().forEach(o -> System.out.println(o));
    }

    private static Person createPersonWithNote() {
        Person person = generatePerson("Andrii");
        Note note = generateNote("noteText");
        person.addNote(note);
        return person;
    }

    private static Person generatePerson(String name) {
        var person = new Person();
        person.setName(name);
        return person;
    }

    private static Note generateNote(String text){
        var note = new Note();
        note.setText(text);
        return note;
    }

    private static <T> void persist(T entity) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static <T> void deleteById(Class<T> clazz, long l) {
        var em = sessionFactory.createEntityManager();
        em.getTransaction().begin();
        T entity = em.find(clazz, l);
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
        System.out.printf("Entity %s with id %S removed\n".toUpperCase(Locale.ROOT), entity.getClass().getSimpleName(), l);
    }

    private static List findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List resultList = entityManager.createQuery("select p from Person p").getResultList();
        return resultList;
    }
}
