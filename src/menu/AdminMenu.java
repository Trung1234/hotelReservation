package menu;

import java.util.Scanner;

public class AdminMenu {
	public static void createAdminMenu() {
		Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
        	System.out.println("Menu:");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("5. ");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
        }
        while (choice != 5);

        scanner.close();
	}
}
