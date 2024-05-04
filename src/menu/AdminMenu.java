package menu;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import menu.interdace.IMenu;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

public class AdminMenu implements IMenu {

	
	private final AdminResource adminResource;
	
	private final Scanner scanner;

	public AdminMenu(AdminResource adminResource, Scanner scanner) {
		super();
		this.adminResource = adminResource;
		this.scanner = scanner;
	}
	
	@Override
	public void createMenu() {

		String selectedMenu = "";
		try {
			boolean exitLoop = false;
			do {
	        	displayAdminMenu();
	        	if (scanner.hasNextLine())  {
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
	                    	adminResource.displayAllReservations();            
	                        break;
	                    case '4':
	                    	addARoom(scanner);               
	                        break;
	                    case '5':
	                    	System.out.println("Back to the main menu.");
	                    	exitLoop = true;;
	                        break;
	                    default:
	                        System.out.println("Invalid choice. Please try again.");
	                    }
	           	 }
	        	}      
	        }
	        while (!exitLoop && (selectedMenu.charAt(0) != '5' || selectedMenu.length() != 1));
		}catch (Exception e) {
			createMenu();
		}
        
	}

	private  void  displayAdminMenu() {
		System.out.println("Admin Menu:");
        System.out.println("1. See all Customers");
        System.out.println("2.  See all Rooms");
        System.out.println("3.  See all Reservations");
        System.out.println("4.  Add a Room");
        System.out.println("5.  Back to Main Menus");
        System.out.print("Enter your choice: ");
	}
	
	public  void addARoom(Scanner scanner) {
		
        String choice = "";
        
        System.out.println("Please input your roomNumber: ");
	    String roomNumber = scanner.nextLine();
	    System.out.println("Please input your roomNumber's price: ");
	    Double price = addRoomPrice(scanner);
        
	    System.out.println("Enter 1 or 2 to make a choice of Room type.");
        while (true) {
            System.out.println("Enter your choice (1 or 2): 1 for SINGLE bed, 2 is DOUBLE bed");
            if (scanner.hasNextLine()) { // Check if input is an integer
                choice = scanner.nextLine();
                if (choice.equals(RoomType.DOUBLE.label.toString()) || choice.equals(RoomType.SINGLE.label.toString())) { 
                	// Check if input is 1 or 2
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); 
            }
        }
        RoomType roomType = RoomType.getValueByLabel(Integer.valueOf(choice));
	    
        IRoom room = new Room(roomNumber,  price, roomType);
        adminResource.addRoom(room);
	}
	
	 private static double addRoomPrice(Scanner scanner) {
	        try {
	            return Double.parseDouble(scanner.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid price format! Please, enter a valid double number.");
	            return addRoomPrice(scanner);
	        }
	    }

	public  void seeAllCustomers() {
		Collection<Customer> customers;
		try {
			customers = adminResource.getAllCustomers();
			for(Customer customer : customers) {
		    	System.out.println(customer);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public  void seeAllRooms() {
		System.out.println("These are all rooms");
		Collection<IRoom> rooms =  adminResource.getAllRooms();
		for(IRoom room : rooms) {
	    	System.out.println(room);
	    }
	}
}
