package com.example.demo.dto;

import com.example.demo.entity.Note;
import com.example.demo.entity.Person;
import lombok.Data;

@Data
public class NoteDto {

    private Long id;
    private String text;
    private Person person;

    public NoteDto(Note note) {
        this.id = note.getId();
        this.text = note.getText();
        this.person = note.getPerson();
    }
}
