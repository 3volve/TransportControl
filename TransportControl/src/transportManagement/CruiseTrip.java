package transportManagement;

import java.util.ArrayList;
import java.util.HashMap;

import transportManagement.supportClasses.MyDate;

public class CruiseTrip extends Transition 
{
	private MyDate arriveDate;
	private ArrayList<String> dest;
	
	CruiseTrip( String orig, String id, MyDate depart, MyDate arrive, ArrayList<String> de ) {
		super(orig, id, depart, new HashMap<SeatClass, TransportSection>());
		arriveDate = arrive;
		dest = de;
	}
	
	boolean hasCities(String orig, String... testDest) {
		if( !orig.equals(origin) ) return false;
		
		for( String testCity : testDest )
			if( !dest.contains(testCity) )
				return false;
		
		return true;
	}
	
	MyDate[] getTripDates() { return new MyDate[] {departDate, arriveDate}; }
	
	@Override
	void addSection(TransportSection section) {

	}
}
