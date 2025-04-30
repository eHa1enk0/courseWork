package org.gBlank.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private String name;
    private String surname;
    private int age;
    private String email;

    public User(String name, String surname, int age, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }
}
