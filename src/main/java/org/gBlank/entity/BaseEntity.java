package org.gBlank.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter

public class BaseEntity {
    private LocalDateTime createdAt;
    private String id;

    public BaseEntity() {
        this.createdAt = LocalDateTime.now();
        this.id = generateId();
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
