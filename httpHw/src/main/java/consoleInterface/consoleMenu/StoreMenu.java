package consoleInterface.consoleMenu;

import models.Order;
import models.enums.OrderStatus;
import requestSender.StoreRequestSender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StoreMenu {
    private static StoreRequestSender storeSender = new StoreRequestSender();
    private static Scanner in = new Scanner(System.in);

    public static void run() {
        System.out.println("Enter:\n1 to create an order\n2 to get an order by id\n3 to delete an order\n4 to get inventory");
        String input = in.nextLine();

        if (input.equals("1")) {
            createOrder();
        }
        else if (input.equals("2")) {
            System.out.print("Enter id: ");
            long inputNum = 0;
            String inputNumStr = in.nextLine();
            if (inputNumStr.matches("\\d+")) {
                inputNum = Long.parseLong(inputNumStr);
            }
            storeSender.getById(inputNum);
        }
        else if (input.equals("3")) {
            System.out.print("Enter id: ");
            long inputNum = 0;
            String inputNumStr = in.nextLine();
            if (inputNumStr.matches("\\d+")) {
                inputNum = Long.parseLong(inputNumStr);
            }
            storeSender.delete(inputNum);
        }
        else if (input.equals("4")) {
            storeSender.getInventory();
        }
        else {
            System.out.println("Invalid command");
        }
    }

    private static void createOrder() {
        System.out.print("Enter id: ");
        long id = 0;
        String inputNumStr = in.nextLine();
        if (inputNumStr.matches("\\d+")) {
            id = Long.parseLong(inputNumStr);
        }
        System.out.print("Enter pet id: ");
        long petId = 0;
        inputNumStr = in.nextLine();
        if (inputNumStr.matches("\\d+")) {
            petId = Long.parseLong(inputNumStr);
        }
        System.out.print("Enter quantity: ");
        int quantity = 0;
        inputNumStr = in.nextLine();
        if (inputNumStr.matches("\\d+")) {
            quantity = Integer.parseInt(inputNumStr);
        }
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
        storeSender.create(order);
    }
}
