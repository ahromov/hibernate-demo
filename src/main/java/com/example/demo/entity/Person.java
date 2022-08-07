package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString
public class Person {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String name;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Note> noteList = new LinkedHashSet<>();

    public void addNote(Note note) {
        note.setPerson(this);
        this.noteList.add(note);
    }
}
