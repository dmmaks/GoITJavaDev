import consoleInterface.ConsoleInterface;
import models.Order;
import models.User;
import models.enums.OrderStatus;
import requestSender.PetRequestSender;
import requestSender.StoreRequestSender;
import requestSender.UserRequestSender;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface();
        consoleInterface.run();
    }
}
