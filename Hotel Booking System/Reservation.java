package BookingSystem;

	/*
	 * Suzy Liu 260761416
	 */

public class Reservation {
	//private attributes: String name and Room roomReserved
	private String name;
	private Room roomReserved;
	//a public constructor Reservation
	public Reservation(Room roomReserved, String name) {
		//initialize attributes roomReserved and name
		this.roomReserved = roomReserved;
		this.name = name;
	}
	//a method: get name of the user who makes the reservation
	public String getName() {
		return this.name;
	}
	//a method: get the room which is reserved by the user
	public Room getRoom() {
		return this.roomReserved;
	}

}
