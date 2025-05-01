package org.gBlank.Service;

import lombok.Getter;
import lombok.Setter;
import org.gBlank.Entity.Request;
import org.gBlank.Entity.User;
import org.gBlank.Entity.Status;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class RequestService {
    private List<Request> requests;

    public RequestService(List<Request> requests) {
        this.requests = requests;
    }

    public void addRequest(Request request) {
        requests.add(request);
        System.out.println("\nЗаявку додано, її ID:  " + request.getId());
    }

    public void getRequests() {
        if (requests.isEmpty()) {
            System.out.println("\nСписок заявок пустий.");
        } else {
            for (Request request : requests) {
                System.out.println("\nID: " + request.getId());
                System.out.println("Ім'я: " + request.getUser().getName());
                System.out.println("Прізвище: " + request.getUser().getSurname());
                System.out.println("Вік: " + request.getUser().getAge());
                System.out.println("Статус заявки: " + request.getStatus());
                System.out.println("Опис: " + request.getDescription());
                System.out.println("Дата створення: " + request.getCreatedAt());
                System.out.println("----------------------------------------");
            }
        }
    }

    public void getRequestsById(String id) {
        for (Request request : requests) {
            if (request.getId().equals(id)) {
                System.out.println("\nID: " + request.getId());
                System.out.println("Ім'я: " + request.getUser().getName());
                System.out.println("Прізвище: " + request.getUser().getSurname());
                System.out.println("Вік: " + request.getUser().getAge());
                System.out.println("Статус заявки: " + request.getStatus());
                System.out.println("Опис: " + request.getDescription());
                System.out.println("Дата створення: " + request.getCreatedAt());
                System.out.println("----------------------------------------");
            } else {
                System.out.println("Заявка з таким ID не знайдено.");
                System.out.println("----------------------------------------");
            }
        }
    }

    public void getUserInfoByRequestId(String id) {
        for (Request request : requests) {
            if (request.getId().equals(id)) {
                User user = request.getUser();
                System.out.println("\nІнформація про користувача: ");
                System.out.println("Ім'я: " + user.getName());
                System.out.println("Прізвище: " + user.getSurname());
                System.out.println("Вік: " + user.getAge());
                System.out.println("Електронна пошта: " + user.getEmail());
                System.out.println("----------------------------------------");
            } else {
                System.out.println("Заявка з таким ID не знайдено.");
                System.out.println("----------------------------------------");
            }
        }
    }

    public void changeStatus(String id, Status status) {
        for (Request request : requests) {
            if (request.getId().equals(id)) {
                request.setStatus(status);
                System.out.println("\nСтатус оновлено.");
                System.out.println("----------------------------------------");
                return;
            }
        }
        System.out.println("\nЗаявка з таким ID не знайдено.");
        System.out.println("----------------------------------------");
    }

    public void removeRequest(String id) {
        for (Request request : requests) {
            if (request.getId().equals(id)) {
                requests.remove(request);
                System.out.println("\nЗаявку видалено");
                System.out.println("----------------------------------------");
                return;
            }
        }
        System.out.println("\nЗаявка з таким ID не знайдено.");
        System.out.println("----------------------------------------");
    }
}