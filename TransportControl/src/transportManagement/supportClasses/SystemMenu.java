package transportManagement.supportClasses;

import java.util.NoSuchElementException;
import java.util.Scanner;

import transportManagement.SystemManager;

public class SystemMenu {
	
	public final static int EXIT_NUM = 10;
	public final static int UI_SWITCH = 9;
	
	public static int menuSwitch( String type, boolean admin, Scanner keyboard ) {
		if( !admin ) {
			if( type.equals("Air") ) return airMenu(keyboard);
			else if( type.equals("Cruise") ) return cruiseMenu(keyboard);
		}
		else if( admin )
			return adminMenu(keyboard);
		
		return -1;
	}
	
	private static int adminMenu(Scanner keyboard) {
		int choice = 0;
		
		do {
			System.out.println("\n____Admin Transport Menu____");
			
			System.out.println("1)   Create airports, airlines, and flights with flight sections and seats.");
			System.out.println("2)   Create cruises, ports, trips, and ships with cabin sections and cabins.");	
			System.out.println("3)   Print the detailed current state of the airline subsystem.");	
			System.out.println("4)   Print the detailed current state of the cruise subsystem.");
			choice = numberPrompt("menu choice here", keyboard);
			
			
			if( choice < 1 || choice > EXIT_NUM )
				System.out.println("That was not a valid choice from the options above, please try again.");
		} while( choice < 1 || choice > EXIT_NUM );
		
		return choice;
	}

	private static int airMenu( Scanner keyboard ) {
		int choice = 0;
		
		do {
			System.out.println("\n____Air Transport Menu____");
			
			System.out.println("1)   Load pre-built Airport System.");
			System.out.println("2)   Change the pricing for seats in given flight section of given flightID for given airline.");	
			System.out.println("3)   List all available flights from airport a to airport b on given date.");	
			System.out.println("4)   Change the seat class pricing for a given airline from airport a to airport b.");
			System.out.println("5)   Book a specific seat if it is available.");
			System.out.println("6)   Book a seat from a flight based on seating preference.");	
			System.out.println("7)   Display all details of Airport system.");
			System.out.println("8)   Save Airport system's details to file.");
			System.out.println("9)   Switch to Administrator UI.");
			System.out.println("10)  Exit.");
			choice = numberPrompt("menu choice here", keyboard);
			
			
			if( choice < 1 || choice > EXIT_NUM )
				System.out.println("That was not a valid choice from the options above, please try again.");
		} while( choice < 1 || choice > EXIT_NUM );
		
		return choice;
	}
	
	private static int cruiseMenu( Scanner keyboard ) {
		int choice = 0;
		
		do {
			System.out.println("\n____Cruise Transport Menu____");
			
			System.out.println("1)   Change the pricing for cabins in given cabin-section of given tripID for given cruiseline.");	
			System.out.println("2)   List all available trips from port a to port b leaving on given date.");	
			System.out.println("3)   Change the cabin-type pricing for a given Cruiseline from port a to port b.");
			System.out.println("4)   Book a specific cabin if it is available.");	
			System.out.println("5)   Display all details of Cruise system.");
			System.out.println("6)   Switch to Administrator UI.");
			System.out.println("7)   Exit.");
			choice = numberPrompt("a menu choice here", keyboard);
			
			
			if( choice < 1 || choice > 6 )
				System.out.println("That was not a valid choice from the options above, please try again.");
			
		} while( choice < 1 || choice > 6 );
		
		if( choice == 5 ) choice = 7;  //to account for the different menus.
		else if( choice == 6 ) choice = UI_SWITCH;
		else if( choice == 7 ) choice = EXIT_NUM;
		else choice++;
		
		return choice;
	}
	
	public static void optionNumber( int choice, String type, SystemManager systems, SystemBuilder systemsBuilder, Scanner keyboard ) {
		
		switch( choice )
		{
			case(1) :	systemsBuilder = new SystemBuilder(strPrompt("file name", keyboard), keyboard);
						systems = systemsBuilder.getSystemManager();
						break;
						
			case(2) : 	systems.changePricing(type, strPrompt(type + "line" + systems.allLinesToString(type), keyboard), 
							strPrompt("transit ID#", keyboard), seatClassPrompt(type, keyboard), numberPrompt("price", keyboard));
						break;
			
			case(3) :	systems.findAvailableTransits(type, strPrompt("starting port" + systems.allPortsToString(type), keyboard),
							strPrompt("ending port" + systems.allPortsToString(type), keyboard),
							new MyDate(numberPrompt("month", keyboard), numberPrompt("day", keyboard), numberPrompt("year", keyboard)));
						break;
			
			case(4) :	systems.changePricing(type, strPrompt(type + "line" + systems.allLinesToString(type), keyboard),
							strPrompt("starting port" + systems.allPortsToString(type), keyboard),
							strPrompt("ending port" + systems.allPortsToString(type), keyboard), seatClassPrompt(type, keyboard), numberPrompt("price", keyboard));
						break;
			
			case(5) :	String seatType = "";
						if( type.equals("Air") ) seatType = "seat";
						else if( type.equals("Cruise") ) seatType = "cabin";
						
						systems.bookSeat(type, strPrompt(type + "line" + systems.allLinesToString(type), keyboard),
							strPrompt("transit ID#", keyboard), seatClassPrompt(type, keyboard), numberPrompt(seatType + "number", keyboard),
							Character.toUpperCase(characterPrompt(seatType + " letter", keyboard)));
						break;
			
			case(6) :	systems.bookAirplaneSeatByPreference(type, strPrompt(type + "line" + systems.allLinesToString(type), keyboard),
							strPrompt("transit ID#", keyboard), (SeatClass) seatClassPrompt(type, keyboard), strPrompt("seating preference[window, aisle]", keyboard));
						break;
			
			case(7) :	systems.displaySystemDetails(type);
						break;
			
			case(8) :	systems.printAirsystemDetailsToFile(strPrompt("File name", keyboard), keyboard);
		}
	}
	
	private static char characterPrompt( String type, Scanner keyboard ) {
		char input = strPrompt(type, keyboard).charAt(0);
		return input;
	}

	private static int numberPrompt( String type, Scanner keyboard ) {
		int input = 1;
		
		System.out.print("Please enter a " + type + ": ");
		
		try { input = keyboard.nextInt(); }
		catch( NoSuchElementException e ) { System.out.println("Incorrect input: " + e); }
		keyboard.nextLine();
		
		return input;
	}

	public static String strPrompt( String type, Scanner keyboard ) {
		String input = "";
		
		System.out.print("Please enter a " + type + ": ");
		input = keyboard.nextLine();
		
		return input;
	}
	
	private static TransportClass seatClassPrompt(String type, Scanner keyboard ) {
		if( type.equals("Air") ) return seatPrompt(keyboard);
		else if( type.equals("Cruise") ) return cabinPrompt(keyboard);
		
		else return null;
	}
	
	private static TransportClass cabinPrompt( Scanner keyboard ) {
		CabinClass cabin = null;
		String input;
		
		do {
			System.out.print("Please enter a CabinClass[family, deluxe family, couples, deluxe couples]: ");
			input = keyboard.nextLine();
			
			if( input.equals("family") ) cabin = CabinClass.family;
			else if( input.equals("deluxe family") ) cabin = CabinClass.deluxeFamily;
			else if( input.equals("couples") ) cabin = CabinClass.couples;
			else if( input.equals("deluxe couples") ) cabin = CabinClass.deluxeCouples;
			
			if( cabin == null) System.out.print("That was not a valid CabinClass, ");
			
		} while( cabin == null );
		
		return cabin;
	}
	
	private static TransportClass seatPrompt( Scanner keyboard ) {
		SeatClass seat = null;
		String input;
		
		do {
			System.out.print("Please enter a SeatClass[first, business, economy]: ");
			input = keyboard.nextLine();
			
			if( input.equals("first") ) seat = SeatClass.first;
			else if( input.equals("business") ) seat = SeatClass.business;
			else if( input.equals("economy") ) seat = SeatClass.economy;
			
			if( seat == null) System.out.print("That was not a valid SeatClass, ");
		} while( seat == null );
		return seat;
	}

	public static String UISwitch(String UI) {
		if( UI.equals("customer") ) return "admin";
		else return "customer";
	}
}
