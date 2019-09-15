package BookingSystem;

	/*
 	* Suzy Liu 
 	*/

import java.util.Scanner;
import java.util.Random;

public class BookingSystem {
    
    private static String[] typeOfRooms = {"double","queen","king"};
    private static Random r = new Random(123);
    
    //returns a random String from the above array. 
    private static String getRandomType(){
        int index = r.nextInt(typeOfRooms.length);
        return typeOfRooms[index];
    }
    //returns a random number of rooms between 5 and 50.
    private static int getRandomNumberOfRooms(){
        return r.nextInt(50)+1;
    }
    //End of provided code. 
    
    public static void main(String[] args){
        //Student Name: Suzy Liu
        //Student Number: 260761416
        //Your code goes here.    
    	System.out.println("Welcome to the COMP 202 booking system");
    	System.out.println("Please enter the name of the hotel you'd like to book");
    	//let user input the hotel name
    	Scanner scanner = new Scanner(System.in);
    	String hotelName = scanner.nextLine();
    	//create new Room array of rooms at random
    	Room[] hRoom = new Room[getRandomNumberOfRooms()];
    	//create new Reservation array, which has the same length as new Room array
    	Reservation[] hReservation = new Reservation[hRoom.length];
    	for(int i=0;i<hRoom.length;i++) {
    		//get the random type for each rooms
    		Room t = new Room(getRandomType());
    		hRoom[i] = t;
    	}
    	//create a new Hotel, takes the input of hotelRoom and new Room array hRoom
    	Hotel newHotel = new Hotel(hotelName,hRoom);
    	int book = 0;
    	//make the main menu replay
    	while(book<10) {
    	System.out.println("*********************************************************************");
    	System.out.println("Welcome to "+hotelName+". "+"Choose one of the following options:");
    	System.out.println("1) Make a reservation");
    	System.out.println("2) Cancel a reservation");
    	System.out.println("3) See an invoice");
    	System.out.println("4) See hotel info");
    	System.out.println("5) Exit the Booking System");
    	//let user input the option
    	int option = scanner.nextInt();
    	//when the user wants to make a reservation
    	if(option==1) {
    		Scanner name = new Scanner(System.in);
    		Scanner type = new Scanner(System.in);
    		System.out.println("Please enter your name: ");
    		String n = name.nextLine();
    		System.out.println("What type of room would you like to reserve?");
    		String t = type.nextLine();
    		Room r = new Room(t);
    		//when the input type is one of double,queen or king
    		if(type.equals("double")||type.equals("queen")||type.equals("king")) {
    			//call method createReservation
    			newHotel.createReservation(n,r);
    			System.out.print("You have successfully reserved a "+t+" room under the name of "+n+".");
        		System.out.println("We look forward having you at Grand Budapest Hotel!");
    		}else{
    			//call method createReservation
    			newHotel.createReservation(n,r);
    		}
    	}
    	//when the user wants to cancel a reservation
    	else if(option==2) {
    		Scanner name = new Scanner(System.in);
    		Scanner type = new Scanner(System.in);
    		System.out.println("Please enter the name you used to make the reservation");
    		String n = name.nextLine();
    		System.out.println("What type of room did you reserve?");
    		String t = type.nextLine();
    		Room r = new Room(t);
    		//call method cancel Reservation
    		newHotel.cancelReservation(n,r);
    	}
    	//when the user wants to see his/her invoice
    	else if(option==3) {
    		Scanner name = new Scanner(System.in);
    		System.out.println("Please enter your name: ");
    		String n = name.nextLine();
    		//call method printInvoice
    		newHotel.printInvoice(n);
    	}
    	//when the user wants to see the hotel info
    	else if(option==4) {
    		System.out.println(newHotel.toString());
    	}
    	//when the user wants to exit the booking system
    	else if(option==5) {
    		System.out.println("It was a pleasure doing business with you!");
    		break;
    	}
    	}
    	
    }
}
