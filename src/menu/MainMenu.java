package menu;

import java.util.Scanner;

import api.AdminResource;

public class MainMenu {
	public static void createNainNenu() {
		Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
        	System.out.println("Menu:");
            System.out.println("1.  Find and reserve a room");
            System.out.println("2.  See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
            case 1:
                System.out.println("You selected Option 1");
                AdminResource.displayAllReservations();
                break;
            case 2:
                System.out.println("You selected Option 2");
                // Add your code for Option 2 here
                break;
            case 3:
                System.out.println("You selected Option 3");
                // Add your code for Option 3 here
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        }
        while (choice != 5);

        scanner.close();
	}
}
