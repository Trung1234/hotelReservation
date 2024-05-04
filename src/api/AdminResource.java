package api;

import java.util.Collection;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

public class AdminResource {
	private final CustomerService customerService;
	private final ReservationService reservationService;

	public AdminResource(CustomerService customerService, ReservationService reservationService) {
		this.customerService = customerService;
		this.reservationService = reservationService;
	}
	
	public  Customer getCustomer(String email) {
		return customerService.getCustomer(email);
	}
	
	public IRoom getRoom(String roomNumber) {
		return reservationService.getARoom(roomNumber);
	}
	
	public  void addRoom(IRoom room) {
		reservationService.addRoom(room);
	}
	
	public  Collection<IRoom> getAllRooms() {
		return reservationService.getAllRooms();
	}
	
	public  Collection<Customer> getAllCustomers() {
		return customerService.getAllCustomers().values();
	}
	
	public  void displayAllReservations() {
		reservationService.printAllReservation();
	}
}
