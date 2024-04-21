package model;

public class FreeRoom extends Room{
	public FreeRoom(String roomNumber, RoomType enumeration) {
		super.price = 0.0;
		this.roomNumber = roomNumber;
		this.enumeration = enumeration;
	}
	@Override
	public String toString() {
		return "Free Room [roomNumber=" + roomNumber + ", price is zero , enumeration=" + enumeration + "]";
	}
}
