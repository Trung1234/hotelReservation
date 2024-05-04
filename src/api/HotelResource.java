package api;

import java.util.Collection;
import java.util.Date;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

public final class HotelResource {

	private final CustomerService customerService;
	private final ReservationService reservationService;

	public HotelResource(CustomerService customerService, ReservationService reservationService) {
		this.customerService = customerService;
		this.reservationService = reservationService;
	}

	/**
	 * get Customer by email
	 * 
	 * @param customerEmail
	 * @return
	 */
	public Customer getCustomer(String email) {
		return customerService.getCustomer(email);
	}

	public Date addDefaultPlusDays(final Date date) {
		return reservationService.addDefaultPlusDays(date);
	}

	public Reservation findReservation(IRoom room ,Date checkInDate, Date checkOutDate) {
		return reservationService.findReservation(room, checkInDate, checkOutDate);
	}

	public void createACustomer(String email, String firstName, String lastName) {
		customerService.addCustomer(email, firstName, lastName);
	}

	public IRoom getRoom(String roomNumber) {
		return reservationService.getARoom(roomNumber);
	}

	public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date CheckOutDate) {
		return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, CheckOutDate);
	}

	public Collection<Reservation> getCustomersReservations(String customerEmail) {
		return reservationService.getCustomersReservation(getCustomer(customerEmail));
	}
}
