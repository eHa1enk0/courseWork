package org.gBlank.service;

import org.gBlank.dao.RequestDAO;
import org.gBlank.dao.UserDAO;
import org.gBlank.entity.Request;
import org.gBlank.entity.Status;
import org.gBlank.entity.User;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestService {
    private final RequestDAO requestDAO;
    private final UserDAO userDAO;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public RequestService(RequestDAO requestDAO, UserDAO userDAO) {
        this.requestDAO = requestDAO;
        this.userDAO = userDAO;
    }

    public void addRequest(Request request) {
        boolean validData = false;
        while (!validData) {
            if (request.getUser().getName() == null || request.getUser().getName().trim().isEmpty() || !isAlpha(request.getUser().getName())) {
                System.out.println("Помилка: введено некоректне ім'я.");
                return;
            }

            if (request.getUser().getSurname() == null || request.getUser().getSurname().trim().isEmpty() || !isAlpha(request.getUser().getSurname())) {
                System.out.println("Помилка: введено некоректне прізвище");
                return;
            }

            if (request.getUser().getEmail() == null || request.getUser().getEmail().trim().isEmpty() || !isValidEmail(request.getUser().getEmail())) {
                System.out.println("Помилка: Невалідний email.");
                return;
            }

            if (request.getUser().getAge() < 1 || request.getUser().getAge() > 100) {
                System.out.println("Помилка: введено некоректний вік.");
                return;
            }

            if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
                System.out.println("Помилка: Опис заявки не може бути порожнім.");
                return;
            }

            // Якщо всі перевірки пройдені
            validData = true;
        }
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

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isAlpha(String str) {
        return str != null && str.matches("[a-zA-Z]+");
    }
}
