package com.example.demo.dto;


import com.example.demo.entity.Note;
import com.example.demo.entity.Person;
import lombok.Data;
import java.util.List;

@Data
public class PersonDto {

    private Long id;
    private String name;
    private List<Note> noteList;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.noteList = person.getNoteList();
    }
}
