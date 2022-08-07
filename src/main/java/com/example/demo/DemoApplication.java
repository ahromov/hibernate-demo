package com.example.demo;

import com.example.demo.entity.Note;
import com.example.demo.entity.Person;
import com.example.demo.persistence.Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import static com.example.demo.utils.EntityGenerator.*;


@SpringBootApplication
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        Repository repository = context.getBean(Repository.class);

        log("generatePersonWithNote();");
        Person person = generatePersonWithNote();
        log("repository.persist(person);");
        repository.persist(person);

        log("repository.findById(Person.class, 1);");
        Person persistedPerson = repository.findById(Person.class, 1);
        System.out.println(persistedPerson);

        log("generateNoteForPerson(persistedPerson);");
        Note note = generateNoteForPerson(persistedPerson);
        log("repository.update(Person.class, 1, addNoteToPerson(note));");
        Person updatedPerson = repository.update(Person.class, 1, addNoteToPerson(note));
        System.out.println(updatedPerson);

        log("repository.findAll(Person.class);");
        List<Person> allPersons = repository.findAll(Person.class);
        allPersons.forEach(System.out::println);

        repository.deleteById(Person.class, 1);
    }

    private static Consumer<Person> addNoteToPerson(Note note) {
        return p -> p.getNoteList().add(note);
    }

    private static void log(String s) {
        log.info(s.toUpperCase(Locale.ROOT));
    }
}
