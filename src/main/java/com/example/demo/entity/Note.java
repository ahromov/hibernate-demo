package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = "person")
public class Note {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    private Person person;
}
