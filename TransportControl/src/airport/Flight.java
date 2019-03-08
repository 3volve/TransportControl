package airport;

import java.util.HashMap;

public class Flight
{
	private String orig, dest, ID;
	private int year, month, day;
	private HashMap<SeatClass, FlightSection> flightSections;
	
	Flight( String or, String de, int ye, int mo, int da, String id ) {
		orig = or; dest = de; ID = id;
		year = ye; month = mo; day = da; 
		flightSections = new HashMap<SeatClass, FlightSection>();
	}
	
	void addFlightSection( FlightSection section ) { flightSections.put(section.getSeatClass(), section); }
	
	String getID() { return ID; }
}
