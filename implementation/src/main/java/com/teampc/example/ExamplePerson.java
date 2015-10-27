package com.teampc.example;

import lombok.Data;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
// Small data class that operates as a row in the example table
@Entity
@Table(name = "EXAMPLE_PERSON")
public class ExamplePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // default constructor is necessary for FX to instantiate a row
    public ExamplePerson() {
    }
}
