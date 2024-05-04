package model;

import java.util.Objects;

public class Room implements IRoom {
	protected String roomNumber;
	protected Double price;
	protected RoomType enumeration;
	

	public Room(String roomNumber, Double price, RoomType enumeration) {
		super();
		this.roomNumber = roomNumber;
		this.price = price;
		this.enumeration = enumeration;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(enumeration, price, roomNumber);
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Room))
			return false;
		Room other = (Room) obj;
		return Objects.equals(roomNumber, other.roomNumber);
	}


	@Override
	public String getRoomNumber() {
		return roomNumber;
	}

	@Override
	public Double getRoomPrice() {
		return price;
	}

	@Override
	public RoomType getRoomType() {
		return enumeration;
	}

	@Override
	public boolean isFree() {
		return false;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", price=" + price + ", room type=" + enumeration + "]";
	}
	
}
