package com.teampc.example;

import lombok.Data;

@Data
// Small data class that operates as a row in the example table
public class ExamplePerson {
    private String firstName = "";
    private String lastName = "";
    private String email = "";

    // default constructor is necessary for FX to instantiate a row
    public ExamplePerson() {
        this("", "", "");
    }

    @java.beans.ConstructorProperties({"firstName", "lastName", "email"})
    public ExamplePerson(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
