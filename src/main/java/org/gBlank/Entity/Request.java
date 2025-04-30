package org.gBlank.Entity;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter

public class Request {
    private UUID id;
    private Status status;
    private User user;
    private String description;
    private LocalDateTime createdAt;

    public Request(Status status, User user, String description) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.user = user;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }
}
