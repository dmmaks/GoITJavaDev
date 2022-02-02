package consoleInterface.consoleMenu;

import models.Category;
import models.Pet;
import models.Tag;
import models.enums.Method;
import models.enums.PetStatus;
import requestSender.PetRequestSender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class PetMenu {
    private static PetRequestSender petSender = new PetRequestSender();
    private static Scanner in = new Scanner(System.in);

    public static void run() {
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
            long id = 0;
            String inputNumStr = in.nextLine();
            if (inputNumStr.matches("\\d+")) {
                id = Long.parseLong(inputNumStr);
            }
            petSender.uploadImage(id);
        }
        else if (input.equals("4")) {
            getByStatus();
        }
        else if (input.equals("5")) {
            getByTags();
        }
        else if (input.equals("6")) {
            System.out.print("Enter id: ");
            long inputNum = 0;
            String inputNumStr = in.nextLine();
            if (inputNumStr.matches("\\d+")) {
                inputNum = Long.parseLong(inputNumStr);
            }
            petSender.getById(inputNum);
        }
        else if (input.equals("7")) {
            System.out.print("Enter id: ");
            long inputNum = 0;
            String inputNumStr = in.nextLine();
            if (inputNumStr.matches("\\d+")) {
                inputNum = Long.parseLong(inputNumStr);
            }
            petSender.delete(inputNum);
        }
        else {
            System.out.println("Invalid command");
        }
    }

    private static void updatePet(Method method) {
        System.out.print("Enter id: ");
        long id = 0;
        String inputNumStr = in.nextLine();
        if (inputNumStr.matches("\\d+")) {
            id = Long.parseLong(inputNumStr);
        }
        System.out.print("Enter name: ");
        String name = in.nextLine();
        System.out.print("Enter category id: ");
        long cat_id = 0;
        inputNumStr = in.nextLine();
        if (inputNumStr.matches("\\d+")) {
            cat_id = Long.parseLong(inputNumStr);
        }
        System.out.print("Enter category name: ");
        String cat_name = in.nextLine();
        int photoCount = -1;
        while (photoCount < 0) {
            System.out.print("Enter count of photo urls: ");
            inputNumStr = in.nextLine();
            if (inputNumStr.matches("\\d+")) {
                photoCount = Integer.parseInt(inputNumStr);
            }
        }
        Collection<String> photoUrls = new ArrayList<>();
        for (int i = 0; i < photoCount; i++) {
            System.out.print("Enter photo url: ");
            photoUrls.add(in.nextLine());
        }
        int tagCount = -1;
        while (tagCount < 0) {
            System.out.print("Enter count of tags: ");
            inputNumStr = in.nextLine();
            if (inputNumStr.matches("\\d+")) {
                tagCount = Integer.parseInt(inputNumStr);
            }
        }
        Collection<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tagCount; i++) {
            System.out.print("Enter tag id: ");
            long tag_id = 0;
            inputNumStr = in.nextLine();
            if (inputNumStr.matches("\\d+")) {
                tag_id = Long.parseLong(inputNumStr);
            }
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
        petSender.update(pet, method);
    }

    private static void getByStatus() {
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

    private static void getByTags() {
        int tagCount = -1;
        while (tagCount < 0) {
            System.out.print("Enter count of tags: ");
            String inputNumStr = in.nextLine();
            if (inputNumStr.matches("\\d+")) {
                tagCount = Integer.parseInt(inputNumStr);
            }
        }
        Collection<String> tags = new ArrayList<>();
        for (int i = 0; i < tagCount; i++) {
            System.out.print("Enter tag name: ");
            String tag = in.nextLine();
            tags.add(tag);
        }
        petSender.getByTags(tags);
    }
}
