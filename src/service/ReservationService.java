package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import common.Constant;
import model.Customer;
import model.IRoom;
import model.Reservation;

public class ReservationService {
	private static Collection<IRoom> rooms = new ArrayList<>();
	private static Collection<Reservation> reservations = new ArrayList<>();

	public static void addRoom(IRoom room) {
		rooms.add(room);
	}

	public static Collection<IRoom> getAllRooms() {
		return rooms;
	}

	public static IRoom getARoom(String roomId) {
		Optional<IRoom> foundRoom = rooms.stream().filter(c -> c.getRoomNumber().equals(roomId)).findFirst();
		return foundRoom.orElse(null);
	}

	public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
		Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
		reservations.add(reservation);
		return reservation;
	}

	public static void printAllReservation() {
		for (Reservation reservation : reservations) {
			System.out.println(reservation);
		}
	}

	public static Collection<Reservation> getCustomersReservation(Customer customer) {
		return reservations.stream().filter(reservation -> customer.equals(reservation.getCustomer()))
				.collect(Collectors.toList());
	}

	public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
		return reservations.stream()
				.filter(reservation -> checkInDate.equals(reservation.getCheckInDate())
						&& !checkOutDate.equals(reservation.getCheckOutDate()))
				.map(Reservation::getRoom).collect(Collectors.toList());
	}

	public static Date  addDefaultPlusDays(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, Constant.RECOMMENDED_ROOMS_DEFAULT_PLUS_DAYS);

		return calendar.getTime();
	}
}
