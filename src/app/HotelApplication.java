package app;

import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import main.inteface.IMenu;
import menu.AdminMenu;
import menu.MainMenu;
import service.CustomerService;
import service.ReservationService;

public class HotelApplication {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CustomerService customerService = CustomerService.getInstance();
		ReservationService reservationService = ReservationService.getInstance();
		HotelResource hotelResource = new HotelResource(customerService, reservationService);
		AdminResource adminResource = new AdminResource(customerService, reservationService);
		IMenu adminMenu = new AdminMenu(adminResource, scanner);
		IMenu mainMenu = new MainMenu(hotelResource, scanner, adminMenu);
		mainMenu.createMenu();
	}
}
