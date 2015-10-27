package com.teampc.example;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
// Small data class that operates as a row in the example table
public class ExamplePerson {
    private String firstName = "";
    private String lastName = "";
    private String email = "";

    // default constructor is necessary for FX to instantiate a row
    public ExamplePerson() {
        this("", "", "");
    }
}
