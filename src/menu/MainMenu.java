package menu;

import java.util.Collection;
import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import model.Reservation;

public class MainMenu {
	public static void createNainNenu() {
		Scanner scanner = new Scanner(System.in);
		String selectedMenu = "";
		do {
			displayMainMenu();

			// Check if there's an integer available
			selectedMenu = scanner.nextLine();
			if (selectedMenu.length() == 1) {

				switch (selectedMenu.charAt(0)) {
				case '1':
					AdminResource.displayAllReservations();
					break;
				case '2':
					seeMyReservations();
					break;
				case '3':
					createAnAccount();
					break;
				case '4':
					AdminMenu.createAdminMenu();
					break;
				case '5':
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}

		} while (selectedMenu.charAt(0) != '5' || selectedMenu.length() != 1);
		scanner.close();
	}

	private static void displayMainMenu() {
		System.out.println("Main Menu:");
		System.out.println("1.  Find and reserve a room");
		System.out.println("2.  See my reservations");
		System.out.println("3. Create an account");
		System.out.println("4. Admin");
		System.out.println("5. Exit");
		System.out.print("Enter your choice: ");

	}

	private static void seeMyReservations() {
		Scanner input = new Scanner(System.in);
		System.out.print("Please input your email: ");
		String customerEmail = input.nextLine();
		Collection<Reservation> reservations = HotelResource.getCustomersReservations(customerEmail);
		for (Reservation reservation : reservations) {
			System.out.println(reservation);
		}
		input.close();
	}

	private static void createAnAccount() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please input your email: ");
		String email = input.nextLine();
		System.out.println("Please input your first name: ");
		String firstName = input.nextLine();
		System.out.println("Please input your last name: ");
		String lastName = input.nextLine();
		HotelResource.createACustomer(email, firstName, lastName);
		input.close();
		displayMainMenu();
		//createNainNenu();
	}
}
