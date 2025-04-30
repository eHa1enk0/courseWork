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
        System.out.println("Заявку додано, її ID:  " + request.getId());
    }

    public void getRequests() {
        if (requests.isEmpty()) {
            System.out.println("Список заявок пустий.");
        } else {
            for (Request request : requests) {
                System.out.println("ID: " + request.getId());
                System.out.println("Ім'я: " + request.getUser().getName());
                System.out.println("Прізвище: " + request.getUser().getSurname());
                System.out.println("Вік: " + request.getUser().getAge());
                System.out.println("Статус заявки: " + request.getStatus());
                System.out.println("Опис: " + request.getDescription());
                System.out.println("Дата створення: " + request.getCreatedAt());
            }
        }
    }

    public void getUserInfoByRequestId(UUID id) {
        Request request = findById(id);
        if (request == null) {
            User user = request.getUser();
            System.out.println("Інформація про користувача: ");
            System.out.println("Ім'я: " + user.getName());
            System.out.println("Прізвище: " + user.getSurname());
            System.out.println("Вік: " + user.getAge());
            System.out.println("Електронна пошта: " + user.getEmail());
        } else {
            System.out.println("Заявка з таким ID не знайдено.");
        }
    }

    public void changeStatus(UUID id, Status status) {
        for (Request request : requests) {
            if (request.getId().equals(id)) {
                request.setStatus(status);
                System.out.println("Статус оновлено.");
                return;
            }
        }
        System.out.println("Заявка з таким ID не знайдено.");
    }

    public Request findById(UUID id) {
        for (Request request : requests) {
            if (request.getId().equals(id)) {
                return request;
            }
        }
        return null;
    }
}
