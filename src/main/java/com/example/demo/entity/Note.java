package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString
public class Note {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "notes_persons_fk"))
    @ToString.Exclude
    private Person person;
}
