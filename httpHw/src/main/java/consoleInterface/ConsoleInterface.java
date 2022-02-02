package consoleInterface;
import consoleInterface.consoleMenu.PetMenu;
import consoleInterface.consoleMenu.StoreMenu;
import consoleInterface.consoleMenu.UserMenu;

import java.util.Scanner;

public class ConsoleInterface {
    private static Scanner in = new Scanner(System.in);

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.print("Enter pet, store or user to perform corresponding operations or anything else to exit: ");
            String input = in.nextLine();
            if (input.isEmpty()) {
                input = in.nextLine();
            }
            if (input.equalsIgnoreCase("pet")) {
                PetMenu.run();
            }
            else if (input.equalsIgnoreCase("store")) {
                StoreMenu.run();
            }
            else if (input.equalsIgnoreCase("user")) {
                UserMenu.run();
            }
            else {
                exit = true;
            }
        }
    }
}
