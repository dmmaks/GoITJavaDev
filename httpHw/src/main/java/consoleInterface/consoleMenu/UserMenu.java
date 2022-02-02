package consoleInterface.consoleMenu;

import models.User;
import requestSender.UserRequestSender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class UserMenu {
    private static UserRequestSender userSender = new UserRequestSender();
    private static Scanner in = new Scanner(System.in);

    public static void run() {
        System.out.println("Enter:\n1 to create a user\n2 to login\n3 to logout\n4 to delete a user\n" +
                "5 to get a user by username\n6 to update a user\n7 to add multiple users");
        String input = in.nextLine();

        if (input.equals("1")) {
            User user = getUserObject();
            userSender.create(user);
        }
        else if (input.equals("2")) {
            System.out.print("Enter username: ");
            String username = in.nextLine();
            System.out.print("Enter password: ");
            String password = in.nextLine();
            userSender.login(username, password);
        }
        else if (input.equals("3")) {
            userSender.logout();
        }
        else if (input.equals("4")) {
            System.out.print("Enter username: ");
            String username = in.nextLine();
            userSender.delete(username);
        }
        else if (input.equals("5")) {
            System.out.print("Enter username: ");
            String username = in.nextLine();
            userSender.get(username);
        }
        else if (input.equals("6")) {
            System.out.print("Enter username of user to update: ");
            String prevUsername = in.nextLine();
            User user = getUserObject();
            userSender.update(prevUsername, user);
        }
        else if (input.equals("7")) {
            System.out.print("Enter count of users to add: ");
            int inputNum = 0;
            String inputNumStr = in.nextLine();
            if (inputNumStr.matches("\\d+")) {
                inputNum = Integer.parseInt(inputNumStr);
            }
            Collection<User> users = new ArrayList<>();
            for (int i = 0; i < inputNum; i++) {
                User user = getUserObject();
                users.add(user);
            }
            userSender.createWithArray(users);
        }
        else {
            System.out.println("Invalid command");
        }
    }

    private static User getUserObject() {
        System.out.print("Enter id: ");
        int id = 0;
        String inputNumStr = in.nextLine();
        if (inputNumStr.matches("\\d+")) {
            id = Integer.parseInt(inputNumStr);
        }
        System.out.print("Enter username: ");
        String username = in.nextLine();
        System.out.print("Enter first name: ");
        String firstName = in.nextLine();
        System.out.print("Enter last name: ");
        String lastName = in.nextLine();
        System.out.print("Enter email: ");
        String email = in.nextLine();
        System.out.print("Enter password: ");
        String password = in.nextLine();
        System.out.print("Enter phone: ");
        String phone = in.nextLine();
        System.out.print("Enter status: ");
        int status = 0;
        inputNumStr = in.nextLine();
        if (inputNumStr.matches("\\d+")) {
            status = Integer.parseInt(inputNumStr);
        }
        return new User(id, username, firstName, lastName, email, password, phone, status);
    }
}
