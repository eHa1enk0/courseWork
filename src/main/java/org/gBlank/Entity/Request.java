package org.gBlank.Entity;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Random;

@Getter @Setter

public class Request {
    private String id;
    private Status status;
    private User user;
    private String description;
    private LocalDateTime createdAt;

    public Request(Status status, User user, String description) {
        this.id = generateId();
        this.status = status;
        this.user = user;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    private String generateId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
