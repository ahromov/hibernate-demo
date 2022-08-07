package com.example.demo.utils;

import com.example.demo.entity.Note;
import com.example.demo.entity.Person;

public class EntityGenerator {

    private EntityGenerator(){}

    public static Person generatePersonWithNote() {
        Person person = generatePerson("Andrii");
        Note note = generateNote("Some text");
        person.addNote(note);
        return person;
    }

    public static Note generateNoteForPerson(Person person) {
        Note note = generateNote("Some test 2");
        note.setPerson(person);
        return note;
    }

    public static Person generatePerson(String name) {
        var person = new Person();
        person.setName(name);
        return person;
    }

    public static Note generateNote(String text) {
        var note = new Note();
        note.setText(text);
        return note;
    }
}
