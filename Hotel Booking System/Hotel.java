package BookingSystem;
import java.util.NoSuchElementException;

	/*
	 * Suzy Liu 260761416
	 */

public class Hotel {
	//private attributes: String name, Room[] room and Reservation[] reservation
	private String name;
	private Room[] room;
	private Reservation[] reservation;
	//a public constructor Hotel
	public Hotel(String name, Room[] room) {
		//initialize attributes name, room and reservation
		this.name = name;
		this.room = new Room[room.length];
		this.reservation = new Reservation[room.length];
		//copy the references from the input Room array to the new array
		for(int i=0;i<room.length;i++) {
			this.room[i] = room[i];
		}
		this.reservation = null;
	}
	//a method: addReservation
	private void addReservation(Reservation r) {
		//create a new reservation array
		Reservation[] addRe;
		//when no reservation has been made
		if(this.reservation==null) {
			addRe = new Reservation[1];
			addRe[0] = r;
		}else {
			//when some reservations have already been made
			//add the length of the new array
			addRe = new Reservation[reservation.length+1];
			//copy references from the previous reservation array
			for(int i=0;i<reservation.length;i++) {
				addRe[i] = this.reservation[i];
			}
			//add the new reservation to the array
			addRe[reservation.length] = r;
		}
		//the new reservation array
		this.reservation = addRe;
	}
	//a method: removeReservation
	private void removeReservation(String name, Room type) {
		//check whether there is reservation or not
		if(this.reservation==null) {
			System.out.println("imhere");
			throw new NoSuchElementException("No reservation under the name has been made.");
		}
		//create a new reservation array
		Reservation[] removeRe = new Reservation[this.reservation.length-1];
			for(int i=0;i<this.reservation.length;i++) {
				//name used to make reservation
				String nName = this.reservation[i].getName();
				//type of the room reserved under the given name
				String nType = this.reservation[i].getRoom().getType();
				//type of the room reserved the user inputs
				String rType = type.getType();
			
			//when there is only one reservation in the system
			if(this.reservation.length==1&&nType.equals(rType)&&nName.equals(name)) {
				removeRe = null;
				reservation[i].getRoom().changeAvailability();
				break;
			}
			//when there is more than one reservation in the system
			if(nType.equals(rType)&&nName.equals(name)) {
				reservation[i].getRoom().changeAvailability();
				for(int j=i+1;j<reservation.length;j++) {
					//jump the canceled reservation
					removeRe[j-1] = reservation[j];
					}
				break;
			}
			//copy other reservations from the previous reservation array
			removeRe[i] = this.reservation[i];
		}
				this.reservation = removeRe;
		}
	//a method: createReservation
	public void createReservation(String name, Room type) {
		//verify whether a room of the given type is available
		Room ava = Room.findAvailableRoom(room,type.getType());
		//when the room is not available
		if(ava==null) {
			System.out.println("Sorry, the hotel has no available rooms of the requested type.");
		}else {
			//when the room is available
			//create a new reservation r, and takes ava and name as input
			Reservation r = new Reservation(ava,name);
			//change the availability of the rOOM AVA
			ava.changeAvailability();
			//call method addReservatoin and input r
			addReservation(r);
			System.out.println("The reservation is successfully completed!");
		}
	}
	//a method: cancel Reservation
	public void cancelReservation(String name, Room type) {
		//get the type of the room reserved
		String cType = type.getType();
		//use try catch
		try {
			//call method removeReservation, and input name and type
			removeReservation(name,type);
			System.out.println(name+", your reservation for a "+cType+" has successfully canceled");
		}
		//when there is no such reservation
		catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}
	//a method: printInvoice
	public void printInvoice(String name) {
		//int d,q,k indicate the number of each type of room reserved
		int d = 0;
		int q = 0;
		int k = 0;
		//sum of the total reservation
		double sum = 0;
		//when no reservation has been made
		if(this.reservation==null) {
			System.out.println("Sorry, no reservatoin has been made under this name.");
		}else {
		//when there are reservations
		for(int i = 0;i<reservation.length;i++) {
			//get the name and room type of the reservation
			String rRType = reservation[i].getRoom().getType();
			String rRName = reservation[i].getName();
			//when there is such a reservation for same name and type
			//the number of each type of room reserved plus one respectively
			if(rRName.equals(name)&&rRType.equals("double")) {
				d++;
			}else if(rRName.equals(name)&&rRType.equals("queen")) {
				q++;
			}else if(rRName.equals(name)&&rRType.equals("king")) {
				k++;
			}
			//get the total price of the reserved room
		}
		sum = 90.0*d+110.0*q+150.0*k;
		System.out.println(name+"'s invoice is of $"+sum);
		}
	}
	//a method: toString
	public String toString() {
		//int d,q,k indicate the number of room type double, queen and king respectively
		int d = 0;
		int q = 0;
		int k = 0;
		for(int i=0;i<room.length;i++) {
			//when the room is available
			if(room[i].getAvailability()) {
			String type = room[i].getType();
			//number of int d,q,k plus one respectively
			if(type.equals("double")) {
				d++;
			}else if(type.equals("queen")) {
				q++;
			}else if(type.equals("king")) {
				k++;
			}
			}
		}
		String a = "Here is the hotel info";
		String b = "Hotel name: "+this.name;
		String c = "Available Rooms: "+d+" double, "+q+" queen, "+k+" king, ";
		String info = a+"\n"+b+"\n"+c;
		//return String info, which returns the hotel information
		return info;
	}
	
}
