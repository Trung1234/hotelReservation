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
import main.inteface.IMenu;
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
		if (Objects.nonNull(checkIn) && Objects.nonNull(checkOut)) {
			Collection<IRoom> availableRooms = hotelResource.findRooms(checkIn, checkOut);

			if (availableRooms.isEmpty()) {
				System.out.println("No rooms available");
			} else {
//				final Date alternativeCheckIn = HotelResource.addDefaultPlusDays(checkIn);
//				final Date alternativeCheckOut = HotelResource.addDefaultPlusDays(checkOut);
//				System.out.println("We've only found rooms on alternative dates:" + "\nCheck-In Date:"
//						+ alternativeCheckIn + "\nCheck-Out Date:" + alternativeCheckOut);

				printRooms(availableRooms);
			}
		}
	}

	private void printRooms(final Collection<IRoom> rooms) {
		if (rooms.isEmpty()) {
			System.out.println("No rooms found.");
		} else {
			rooms.forEach(System.out::println);
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
