package menu;

import java.util.Collection;
import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

public class AdminMenu {
	public static void createAdminMenu() {
		Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
        	System.out.println("Admin Menu:");
            System.out.println("1. See all Customers");
            System.out.println("2.  See all Rooms");
            System.out.println("3.  See all Reservations");
            System.out.println("4.  Add a Room");
            System.out.println("5.  Back to Main Menus");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
            case 1:
            	seeAllCustomers();
                break;
            case 2:
            	seeAllRooms();
                break;
            case 3:
            	AdminResource.displayAllReservations();            
                break;
            case 4:
            	addARoom();               
                break;
            case 5:
            	MainMenu.createNainNenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        }
        while (choice != 5);
        scanner.close();
	}

	public static void addARoom() {
		// TODO Auto-generated method stub
		
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
