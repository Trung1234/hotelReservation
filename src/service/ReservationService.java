package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import common.Constant;
import model.Customer;
import model.IRoom;
import model.Reservation;

public final class ReservationService {
	private static Collection<IRoom> rooms;
	private static Collection<Reservation> reservations;

	private static ReservationService instance;
	
	private ReservationService() {
		rooms = new ArrayList<>();
		reservations = new ArrayList<>();
	}
	
	public static ReservationService getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ReservationService(); 
		}
		return instance;
	}
	
	public  void addRoom(IRoom room) {
		if(rooms.contains(room)) {
			throw new IllegalArgumentException("This room number is existed.");
		}
		rooms.add(room);
	}
	
	public  Collection<IRoom> getAllRooms() {
		return rooms;
	}

	public  IRoom getARoom(String roomId) {
		Optional<IRoom> foundRoom = rooms.stream().filter(c -> c.getRoomNumber().equals(roomId)).findFirst();
		return foundRoom.orElse(null);
	}

	public  Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
		Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
		reservations.add(reservation);
		return reservation;
	}

	public  void printAllReservation() {
		for (Reservation reservation : reservations) {
			System.out.println(reservation);
		}
	}

	public  Collection<Reservation> getCustomersReservation(Customer customer) {
		return reservations.stream().filter(reservation -> customer.equals(reservation.getCustomer()))
				.collect(Collectors.toList());
	}

	public  Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
		return reservations.stream()
				.filter(reservation -> checkInDate.equals(reservation.getCheckInDate())
						&& !checkOutDate.equals(reservation.getCheckOutDate()))
				.map(Reservation::getRoom).collect(Collectors.toList());
	}

	public  Date  addDefaultPlusDays(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, Constant.RECOMMENDED_ROOMS_DEFAULT_PLUS_DAYS);

		return calendar.getTime();
	}
}
