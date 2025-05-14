package org.gBlank;

import org.gBlank.DataBase.DatabaseInitializer;
import org.gBlank.dao.RequestDAO;
import org.gBlank.dao.UserDAO;
import org.gBlank.entity.Request;
import org.gBlank.entity.Status;
import org.gBlank.entity.User;
import org.gBlank.service.RequestService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        RequestDAO requestDAO = new RequestDAO();
        RequestService service = new RequestService(requestDAO, userDAO);

        while (true) {
            System.out.println("\n1 - Додати заявку");
            System.out.println("2 - Показати всі заявки");
            System.out.println("3 - Змінити статус заявки");
            System.out.println("4 - Показати інформацію про користувача за ID заявки");
            System.out.println("5 - Видалити заявку за її ID");
            System.out.println("6 - Показати заявку за її ID");
            System.out.println("0 - Вийти");
            System.out.print("Ваш вибір: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Ваше ім'я: ");
                    String name = sc.nextLine();
                    System.out.print("Ваше прізвище: ");
                    String surname = sc.nextLine();
                    System.out.print("Ваш Вік: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("Ваш email: ");
                    String email = sc.nextLine();
                    System.out.print("Додайте опис: ");
                    String description = sc.nextLine();

                    User user = new User(name, surname, age, email);
                    Request request = new Request(Status.NEW, user, description);
                    service.addRequest(request);
                    break;

                case "2":
                    service.getRequests();
                    break;

                case "3":
                    System.out.print("Введіть ID заявки: ");
                    String idToUpdate = sc.nextLine();
                    System.out.println("Оберіть новий статус: NEW, IN_PROGRESS, COMPLETED, FAILED");
                    Status newStatus = Status.valueOf(sc.nextLine().toUpperCase());
                    service.changeStatus(idToUpdate, newStatus);
                    break;

                case "4":
                    System.out.print("Введіть ID заявки: ");
                    String idToFind = sc.nextLine();
                    service.getUserInfoByRequestId(idToFind);
                    break;

                case "5":
                    System.out.print("Введіть ID заявки: ");
                    String idToDelete = sc.nextLine();
                    service.removeRequest(idToDelete);
                    break;

                case "6":
                    System.out.print("Введіть ID заявки: ");
                    String idToFindReqById = sc.nextLine();
                        service.getRequestsById(idToFindReqById);
                    break;

                case "0":
                    System.out.println("Завершення програми.");
                    return;

                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
