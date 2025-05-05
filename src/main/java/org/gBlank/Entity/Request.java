package org.gBlank.Entity;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Random;

@Getter @Setter

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
