package consoleInterface;

import jdk.jshell.Snippet;
import models.*;
import models.enums.Method;
import models.enums.OrderStatus;
import models.enums.PetStatus;
import requestSender.PetRequestSender;
import requestSender.StoreRequestSender;
import requestSender.UserRequestSender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    private PetRequestSender petSender = new PetRequestSender();
    private StoreRequestSender storeSender = new StoreRequestSender();
    private UserRequestSender userSender = new UserRequestSender();
    private static Scanner in = new Scanner(System.in);

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.print("Enter pet, store or user to perform corresponding operations or anything else to exit: ");
            String input = in.nextLine();
            if (input.isEmpty()) {
                input = in.nextLine();
            }
            if (input.toLowerCase().equals("pet")) {
                processPets();
            }
            else if (input.toLowerCase().equals("store")) {
                processStore();
            }
            else if (input.toLowerCase().equals("user")) {
                processUsers();
            }
            else {
                exit = true;
            }
        }
    }

    private void processPets() {
        System.out.println("Enter:\n1 to create a pet\n2 to update a pet\n3 to upload an image of a pet\n4 to get pets by status\n" +
                "5 to get pets by tags\n6 to get a pet by id\n7 to delete a pet");
        String input = in.nextLine();

        if (input.equals("1")) {
            updatePet(Method.POST);
        }
        else if (input.equals("2")) {
            updatePet(Method.PUT);
        }
        else if (input.equals("3")) {
            System.out.print("Enter id: ");
            int id = in.nextInt();
            in.nextLine();
            petSender.uploadImage(id);
        }
        else if (input.equals("4")) {
            String statusInput = "";
            while (!statusInput.contains("available") && !statusInput.contains("pending") && !statusInput.contains("sold")) {
                System.out.print("Enter statuses (available, pending or sold): ");
                statusInput = in.nextLine();
            }
            Collection<PetStatus> statuses = new ArrayList<>();
            if (statusInput.contains("available")) {
                statuses.add(PetStatus.available);
            }
            if (statusInput.contains("sold")) {
                statuses.add(PetStatus.sold);
            }
            if (statusInput.contains("pending")) {
                statuses.add(PetStatus.pending);
            }
            petSender.getByStatus(statuses);
        }
        else if (input.equals("5")) {
            int tagCount = -1;
            while (tagCount < 0) {
                System.out.print("Enter count of tags: ");
                tagCount = in.nextInt();
            }
            in.nextLine();
            Collection<String> tags = new ArrayList<>();
            for (int i = 0; i < tagCount; i++) {
                System.out.print("Enter tag name: ");
                String tag = in.nextLine();
                tags.add(tag);
            }
            petSender.getByTags(tags);
        }
        else if (input.equals("6")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            petSender.getById(inputNum);
        }
        else if (input.equals("7")) {
            System.out.print("Enter id: ");
            long inputNum = in.nextLong();
            petSender.delete(inputNum);
        }
        else {
            System.out.println("Invalid command");
        }
    }

    private void processStore() {
        System.out.println("Enter:\n1 to create an order\n2 to get an order by id\n3 to delete an order\n4 to get inventory");
        String input = in.nextLine();

        if (input.equals("1")) {
            System.out.print("Enter id: ");
            int id = in.nextInt();
            in.nextLine();
            System.out.print("Enter pet id: ");
            int petId = in.nextInt();
            in.nextLine();
            System.out.print("Enter quantity: ");
            int quantity = in.nextInt();
            in.nextLine();
            System.out.print("Enter date: ");
            String dateStr = in.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = formatter.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Date parsing error occurred");
                return;
            }
            String statusInput = "";
            while (!statusInput.equals("placed") && !statusInput.equals("approved") && !statusInput.equals("delivered")) {
                System.out.print("Enter status (placed, approved or delivered): ");
                statusInput = in.nextLine();
            }
            OrderStatus status = OrderStatus.valueOf(statusInput);
            String isCompleteInput = "";
            while (!isCompleteInput.equals("true") && !isCompleteInput.equals("false")) {
                System.out.print("Enter whether order is complete (true or false): ");
                isCompleteInput = in.nextLine();
            }
            boolean isComplete = Boolean.parseBoolean(isCompleteInput);

            Order order = new Order(id, petId, quantity, date, status, isComplete);
            this.storeSender.create(order);
        }
        else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            storeSender.getById(inputNum);
        }
        else if (input.equals("3")) {
            System.out.print("Enter id: ");
            long inputNum = in.nextLong();
            storeSender.delete(inputNum);
        }
        else if (input.equals("4")) {
            storeSender.getInventory();
        }
        else {
            System.out.println("Invalid command");
        }
    }

    private void processUsers() {
        System.out.println("Enter:\n1 to create a user\n2 to login\n3 to logout\n4 to delete a user\n" +
                "5 to get a user by username\n6 to update a user\n7 to add multiple users");
        String input = in.nextLine();

        if (input.equals("1")) {
            User user = getUserObject();
            this.userSender.create(user);
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
        else if (input.equals("4")) {
            String statusInput = "";
            while (!statusInput.contains("available") && !statusInput.contains("pending") && !statusInput.contains("sold")) {
                System.out.print("Enter statuses (available, pending or sold): ");
                statusInput = in.nextLine();
            }
            Collection<PetStatus> statuses = new ArrayList<>();
            if (statusInput.contains("available")) {
                statuses.add(PetStatus.available);
            }
            if (statusInput.contains("sold")) {
                statuses.add(PetStatus.sold);
            }
            if (statusInput.contains("pending")) {
                statuses.add(PetStatus.pending);
            }
            petSender.getByStatus(statuses);
        }
        else if (input.equals("5")) {
            int tagCount = -1;
            while (tagCount < 0) {
                System.out.print("Enter count of tags: ");
                tagCount = in.nextInt();
            }
            in.nextLine();
            Collection<String> tags = new ArrayList<>();
            for (int i = 0; i < tagCount; i++) {
                System.out.print("Enter tag name: ");
                String tag = in.nextLine();
                tags.add(tag);
            }
            petSender.getByTags(tags);
        }
        else if (input.equals("6")) {
            System.out.print("Enter username of user to update: ");
            String prevUsername = in.nextLine();
            User user = getUserObject();
            this.userSender.update(prevUsername, user);
        }
        else if (input.equals("7")) {
            System.out.print("Enter count of users to add: ");
            int inputNum = in.nextInt();
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

    private void updatePet(Method method) {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        System.out.print("Enter name: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.print("Enter category id: ");
        int cat_id = in.nextInt();
        System.out.print("Enter category name: ");
        in.nextLine();
        String cat_name = in.nextLine();
        int photoCount = -1;
        while (photoCount < 0) {
            System.out.print("Enter count of photo urls: ");
            photoCount = in.nextInt();
        }
        in.nextLine();
        Collection<String> photoUrls = new ArrayList<>();
        for (int i = 0; i < photoCount; i++) {
            System.out.print("Enter photo url: ");
            photoUrls.add(in.nextLine());
        }
        int tagCount = -1;
        while (tagCount < 0) {
            System.out.print("Enter count of tags: ");
            tagCount = in.nextInt();
        }
        in.nextLine();
        Collection<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tagCount; i++) {
            System.out.print("Enter tag id: ");
            int tag_id = in.nextInt();
            in.nextLine();
            System.out.print("Enter tag name: ");
            String tag_name = in.nextLine();
            Tag tag = new Tag(tag_id, tag_name);
            tags.add(tag);
        }
        String statusInput = "";
        while (!statusInput.equals("available") && !statusInput.equals("pending") && !statusInput.equals("sold")) {
            System.out.print("Enter status (available, pending or sold): ");
            statusInput = in.nextLine();
        }
        PetStatus status = PetStatus.valueOf(statusInput);

        Pet pet = new Pet(id, new Category(cat_id, cat_name), name, photoUrls, tags, status);
        this.petSender.update(pet, method);
    }

    private User getUserObject() {
        System.out.print("Enter id: ");
        int id = in.nextInt();
        in.nextLine();
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
        int status = in.nextInt();
        return new User(id, username, firstName, lastName, email, password, phone, status);
    }
}
