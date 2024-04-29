package menu;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import model.Reservation;

public class MainMenu {
	public static void createNainNenu() {
		Scanner scanner = new Scanner(System.in);
		String selectedMenu = "";
		do {
			//scanner = new Scanner(System.in);
			try {
				displayMainMenu();
				if(scanner.hasNextLine()) {
					selectedMenu = scanner.nextLine();
					if (selectedMenu.length() == 1) {

						switch (selectedMenu.charAt(0)) {
						case '1':
							AdminResource.displayAllReservations();
							break;
						case '2':
							seeMyReservations(scanner);
							break;
						case '3':
							createAnAccount(scanner);
							break;
						case '4':
							AdminMenu.createAdminMenu();
							break;
						case '5':
							System.out.println("Exiting the app.");
							scanner.close();
							break;
						default:
							System.out.println("Invalid choice. Please try again.");
						}
					}
				}
				
			} catch (NoSuchElementException e) {
				System.err.println("No input provided.");
			}

		} while (selectedMenu.charAt(0) != '5' || selectedMenu.length() != 1);
		
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

	private static void seeMyReservations(Scanner scanner) {
		System.out.print("Please input your email: ");
		String customerEmail = scanner.nextLine();
		Collection<Reservation> reservations = HotelResource.getCustomersReservations(customerEmail);
		for (Reservation reservation : reservations) {
			System.out.println(reservation);
		}
	}

	private static void createAnAccount(Scanner scanner) {
		System.out.println("Please input your email: ");
		String email = scanner.nextLine();
		System.out.println("Please input your first name: ");
		String firstName = scanner.nextLine();
		System.out.println("Please input your last name: ");
		String lastName = scanner.nextLine();
		try {
			HotelResource.createACustomer(email, firstName, lastName);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getLocalizedMessage());
			createAnAccount(scanner);
		}



		// createNainNenu();
	}
}
