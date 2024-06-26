package model;

public class FreeRoom extends Room{
	public FreeRoom(String roomNumber, RoomType enumeration) {
		super(roomNumber,  0.0,  enumeration);
	}
	
	@Override
	public boolean isFree() {
		return true;
	}

	@Override
	public String toString() {
		return "Free Room [roomNumber=" + roomNumber + ", price is zero , price=" + enumeration + "]";
	}
}
