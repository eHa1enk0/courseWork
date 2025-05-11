package org.gBlank.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Request extends BaseEntity {
    private Status status;
    private User user;
    private String description;

    public Request(Status status, User user, String description) {
        super();
        this.status = status;
        this.user = user;
        this.description = description;
    }
}
