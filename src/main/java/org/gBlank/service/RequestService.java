package org.gBlank.service;

import org.gBlank.dao.RequestDAO;
import org.gBlank.dao.UserDAO;
import org.gBlank.entity.Request;
import org.gBlank.entity.Status;
import org.gBlank.entity.User;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestService {
    private RequestDAO requestDAO;
    private UserDAO userDAO;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public RequestService(RequestDAO requestDAO, UserDAO userDAO) {
        this.requestDAO = requestDAO;
        this.userDAO = userDAO;
    }

    public void addRequest(Request request) {
        userDAO.saveUser(request.getUser());
        requestDAO.saveRequest(request);
        System.out.println("\nЗаявку додано, її id: " + request.getId());
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

    public void getRequests() {
        List<Request> requests = requestDAO.getAllRequests();
        if (requests.isEmpty()) {
            System.out.println("\nСписок заявок пустий.");
        } else {
            for (Request request : requests) {
                printRequest(request);
            }
        }
    }

    public void getRequestsById(String id) {
        Request request = requestDAO.getRequestById(id);
        if (request != null) {
            printRequest(request);
        } else {
            System.out.println("Заявка з таким ID не знайдено.");
            System.out.println("----------------------------------------");
        }
    }

    public void getUserInfoByRequestId(String id) {
        Request request = requestDAO.getRequestById(id);
        if (request != null) {
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

    public void changeStatus(String id, Status status) {
        Request request = requestDAO.getRequestById(id);
        if (request != null) {
            request.setStatus(status);
            requestDAO.updateStatus(id, status);
            System.out.println("\nСтатус оновлено.");
        } else {
            System.out.println("\nЗаявка з таким ID не знайдено.");
        }
        System.out.println("----------------------------------------");
    }

    public void removeRequest(String id) {
        boolean removed = requestDAO.deleteRequest(id);
        if (removed) {
            System.out.println("\nЗаявку видалено");
        } else {
            System.out.println("\nЗаявка з таким ID не знайдено.");
        }
        System.out.println("----------------------------------------");
    }
}
