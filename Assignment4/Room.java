package BookingSystem;

	/*
	 * Suzy Liu 260761416
	 */

public class Room {
	//private attributes: String type, double price and boolean availability
	private String type;
	private double price;
	private boolean availability;
	
	//a public constructor room
	public Room(String type) {
		//initialize attribute type
		this.type = type;
		//check whether the type is double, queen or king
		//initialize attribute price
		try {
			//when the type if neither double, queen or king, throw an exception
			if(!type.equals("double")&&!type.equals("queen")&&!type.equals("king")) {
			throw new IllegalArgumentException("Sorry, we don't have this type of room.");
			}else if(type.equals("double")) {
				//when the type is double
				this.price = 90.0;
			}else if(type.equals("queen")) {
				//when the type id queen
				this.price = 110.0;
			}else if(type.equals("king")) {
				//when the type is king
				this.price = 150.0;
			}
		}
		//catch the exception
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		//initialize attribute availability
		this.availability = true;
	}
	//a method: get type of the room
	public String getType() {
		return this.type;
	}
	//a method: get price of the room
	public double getPrice() {
		return this.price;
	}
	//a method: get availability of the room
	public boolean getAvailability() {
		return this.availability;
	}
	//a method: change the availability of the room
	public void changeAvailability() {
		//when the availability is true, change to false
		if(this.availability) {
			this.availability = false;
		}else {
			//when the availability is false, change to true
			this.availability = true;
		}
	}
	//a method: find an available room
	public static Room findAvailableRoom(Room[] room, String type) {
		//initialize Room a
		Room a = null;
		for(int i=0; i<room.length; i++) {
			//when room type equals to the type the user asks for
			if(room[i].getType().equals(type)) {
				//get the availability of the room
				if(room[i].getAvailability()) {
				a = room[i];
				break;
				}
			}else {
				//when no such room exists
				a = null;
			}
		}
		return a;
	}

}
