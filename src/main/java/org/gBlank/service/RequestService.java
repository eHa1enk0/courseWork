package org.gBlank.service;

import lombok.Getter;
import lombok.Setter;
import org.gBlank.entity.Request;
import org.gBlank.entity.User;
import org.gBlank.entity.Status;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Getter
@Setter

public class RequestService {
    private Map<String, Request> requestsMap;
    private List<Request> requestsList;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public RequestService(Map<String, Request> requestsMap, List<Request> requestsList) {
        this.requestsMap = requestsMap;
        this.requestsList = requestsList;
    }

    public void addRequest(Request request) {
        requestsMap.put(request.getId(), request);
        requestsList.add(request);
        System.out.println("\nЗаявку додано, її ID:  " + request.getId());
    }

    private void printRequest(Request request) {
        System.out.println("\nID: " + request.getId());
        System.out.println("Ім'я: " + request.getUser().getName());
        System.out.println("Прізвище: " + request.getUser().getSurname());
        System.out.println("Вік: " + request.getUser().getAge());
        System.out.println("Статус заявки: " + request.getStatus());
        System.out.println("Опис: " + request.getDescription());
        System.out.println("Дата створення: " + request.getCreatedAt().format(formatter));
        System.out.println("----------------------------------------");
    }

    public void getRequest() {
        if (requestsList.isEmpty()) {
            System.out.println("\nСписок заявок пустий.");
        } else {
            for (Request request : requestsList) {
                printRequest(request);
            }
        }
    }

    public void getRequestsById(String id) {
        Request request = requestsMap.get(id);
        if (request != null) {
            printRequest(request);
        } else {
            System.out.println("Заявка з таким ID не знайдено.");
            System.out.println("----------------------------------------");
        }
    }

    public void getUserInfoByRequestId(String id) {
        for (Request request : requestsList) {
            if (request.getId().equals(id)) {
                User user = request.getUser();
                System.out.println("\nІнформація про користувача: ");
                System.out.println("Ім'я: " + user.getName());
                System.out.println("Прізвище: " + user.getSurname());
                System.out.println("Вік: " + user.getAge());
                System.out.println("Електронна пошта: " + user.getEmail());
                System.out.println("Дата створення: " + user.getCreatedAt().format(formatter));
                System.out.println("----------------------------------------");
            } else {
                System.out.println("Заявка з таким ID не знайдено.");
                System.out.println("----------------------------------------");
            }
        }
    }

    public void changeStatus(String id, Status status) {
        for (Request request : requestsList) {
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
        for (Request request : requestsList) {
            if (request.getId().equals(id)) {
                requestsList.remove(request);
                System.out.println("\nЗаявку видалено");
                System.out.println("----------------------------------------");
                return;
            }
        }
        System.out.println("\nЗаявка з таким ID не знайдено.");
        System.out.println("----------------------------------------");
    }
}