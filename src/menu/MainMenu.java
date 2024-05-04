package menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import common.Constant;
import menu.interdace.IMenu;
import model.Customer;
import model.IRoom;
import model.Reservation;

public final class MainMenu implements IMenu {

	private final HotelResource hotelResource;

	private final Scanner scanner;

	private final IMenu adminMenu;

	public MainMenu(HotelResource hotelResource, Scanner scanner, IMenu adminMenu) {
		super();
		this.hotelResource = hotelResource;
		this.scanner = scanner;
		this.adminMenu = adminMenu;
	}

	@Override
	public void createMenu() {
		String selectedMenu = "";
		do {
			try {
				displayMainMenu();
				if (scanner.hasNextLine()) {
					selectedMenu = scanner.nextLine();
					if (selectedMenu.length() == 1) {

						switch (selectedMenu.charAt(0)) {
						case '1':
							findAndReserveRoom(scanner);
							break;
						case '2':
							seeMyReservations(scanner);
							break;
						case '3':
							createAnAccount(scanner);
							break;
						case '4':
							adminMenu.createMenu();
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

	private void findAndReserveRoom(Scanner scanner) {
		System.out.println("Please input checkIn Date with format mm/dd/yyyy ");
		Date checkIn = getDateFormat(scanner);
		System.out.println("Please input Check-Out Date with format mm/dd/yyyy ");
		Date checkOut = getDateFormat(scanner);
		// Check Check-Out Date is after checkIn Date
		while (checkOut.before(checkIn) || checkOut.equals(checkIn)) {
			System.out.println("Check-Out Date has  to  be after checkIn Date");
			System.out.println("Please input Check-Out Date with format mm/dd/yyyy ");
			checkOut = getDateFormat(scanner);
		}
		// hotelResource.
		if (Objects.nonNull(checkIn) && Objects.nonNull(checkOut)) {
			AdminMenu adMenu = (AdminMenu) adminMenu;
			adMenu.seeAllRooms();

			System.out.println("Please input a room number");
			String roomNumber = scanner.nextLine();
			IRoom room = hotelResource.getRoom(roomNumber);
			while (Objects.isNull(room)) {
				System.out.println("This room number is not exist...");
				System.out.println("Please input a room number");
				roomNumber = scanner.nextLine();
				room = hotelResource.getRoom(roomNumber);
			}
			Reservation existReservation = hotelResource.findReservation(room, checkIn, checkOut);
			if (Objects.isNull(existReservation)) {
				reserveRoom(room, scanner, checkIn, checkOut);
			} else {
				final Date alternativeCheckIn = hotelResource.addDefaultPlusDays(checkIn);
				final Date alternativeCheckOut = hotelResource.addDefaultPlusDays(checkOut);
				System.out.println("We've only found this room on alternative dates:" + "\nCheck-In Date:"
						+ alternativeCheckIn + "\nCheck-Out Date:" + alternativeCheckOut);
				reserveRoom(room, scanner, alternativeCheckIn, alternativeCheckOut);
			}
		}
	}

	private void reserveRoom(IRoom room, Scanner scanner, Date checkIn, Date checkOut) {
		while (true) {
			System.out.println("Do you want to book this room ? (y for yes/ no for no)");
			// Convert input to lowercase for case-insensitive comparison
			String input = scanner.nextLine().toLowerCase();

			if (input.equals(Constant.OPTION.YES.getOption())) {
				System.out.println("Please input a customer's email");
				String email = scanner.nextLine();
				Customer customer = hotelResource.getCustomer(email);
				if (Objects.isNull(customer)) {
					System.out.println("This email is not exist...");
					continue;
				}
				Reservation reservation = hotelResource.bookARoom(email, room, checkIn, checkOut);
				System.out.println(reservation);
				break;
			} else if (input.equals(Constant.OPTION.NO.getOption())) {
				System.out.println("Exiting...");
				break; // Exit the loop if the user answers 'no'
			} else {
				continue;
			}
		}

	}

	private Date getDateFormat(Scanner scanner) {
		try {
			return new SimpleDateFormat(Constant.DATE_FORMAT).parse(scanner.nextLine());
		} catch (ParseException ex) {
			System.out.println("Invalid date format!");
			findAndReserveRoom(scanner);
		}

		return null;
	}

	private void displayMainMenu() {
		System.out.println("Main Menu:");
		System.out.println("1.  Find and reserve a room");
		System.out.println("2.  See my reservations");
		System.out.println("3. Create an account");
		System.out.println("4. Admin");
		System.out.println("5. Exit");
		System.out.print("Enter your choice: ");

	}

	private void seeMyReservations(Scanner scanner) {
		System.out.print("Please input your email: ");
		String customerEmail = scanner.nextLine();
		Collection<Reservation> reservations = hotelResource.getCustomersReservations(customerEmail);
		for (Reservation reservation : reservations) {
			System.out.println(reservation);
		}
	}

	private void createAnAccount(Scanner scanner) {
		System.out.println("Please input your email: ");
		String email = scanner.nextLine();
		System.out.println("Please input your first name: ");
		String firstName = scanner.nextLine();
		System.out.println("Please input your last name: ");
		String lastName = scanner.nextLine();
		try {
			hotelResource.createACustomer(email, firstName, lastName);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getLocalizedMessage());
			createAnAccount(scanner);
		}
	}

}
