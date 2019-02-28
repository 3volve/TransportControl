package airport;

import java.util.ArrayList;

public class Flight
{
	private String orig, dest, ID;
	private int year, month, day;
	private ArrayList<FlightSection> flightSections;
	
	Flight( String or, String de, int ye, int mo, int da, String id ) {
		orig = or; dest = de; ID = id;
		year = ye; month = mo; day = da; 
		flightSections = new ArrayList<FlightSection>();
	}
	
	void addFlightSection( FlightSection section ) { flightSections.add(section); }
}
