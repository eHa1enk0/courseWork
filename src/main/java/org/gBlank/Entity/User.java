package org.gBlank.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class User extends BaseEntity {
    private String name;
    private String surname;
    private int age;
    private String email;

    public User(String name, String surname, int age, String email) {
        super();
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }
}
