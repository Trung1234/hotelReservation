package menu;

import java.util.Collection;
import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;

public class AdminMenu {
	public static void createAdminMenu() {
		Scanner scanner = new Scanner(System.in);
		String selectedMenu = "";
        do {
        	
        	selectedMenu = scanner.nextLine();
        	 if (selectedMenu.length() == 1) {
        		 switch (selectedMenu.charAt(0)) {
                 case '1':
                 	seeAllCustomers();
                     break;
                 case '2':
                 	seeAllRooms();
                     break;
                 case '3':
                 	AdminResource.displayAllReservations();            
                     break;
                 case '4':
                 	addARoom();               
                     break;
                 case '5':
                 	MainMenu.createNainNenu();
                     break;
                 default:
                     System.out.println("Invalid choice. Please try again.");
                 }
        	 }
            
            
        
        }
        while (selectedMenu.charAt(0) != '5' || selectedMenu.length() != 1);
        scanner.close();
	}

	private void  displayMainMenu() {
		System.out.println("Admin Menu:");
        System.out.println("1. See all Customers");
        System.out.println("2.  See all Rooms");
        System.out.println("3.  See all Reservations");
        System.out.println("4.  Add a Room");
        System.out.println("5.  Back to Main Menus");
        System.out.print("Enter your choice: ");
	}
	public static void addARoom() {
		Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Enter 1 or 2 to make a choice of Room type.");
        while (true) {
            System.out.print("Enter your choice (1 or 2): 1 is SINGLE, 2 is DOUBLE");
            if (scanner.hasNextInt()) { // Check if input is an integer
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) { // Check if input is 1 or 2
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); 
            }
        }
        RoomType roomType = RoomType.getValueByLabel(choice);
        System.out.println("Please input your roomNumber: ");
	    String roomNumber = scanner.nextLine();
	    System.out.println("Please input your roomNumber's price: ");
	    Double price = scanner.nextDouble();
        scanner.close();
        IRoom room = new Room(roomNumber,  price, roomType);
		AdminResource.addRoom(room);
	}

	public static void seeAllCustomers() {
		Collection<Customer> customers = AdminResource.getAllCustomers();
		for(Customer customer : customers) {
	    	System.out.println(customer);
	    }
	}
	
	public static void seeAllRooms() {
		Collection<IRoom> rooms = AdminResource.getAllRooms();
		for(IRoom room : rooms) {
	    	System.out.println(room);
	    }
	}
}
