package transportManagement;

import java.util.ArrayList;

import transportManagement.supportClasses.MyDate;

public class CruiseTrip extends Transition 
{
	private MyDate arriveDate;
	private ArrayList<String> dest;
	
	CruiseTrip( String orig, String id, MyDate depart, MyDate arrive, CruiseShip ship, ArrayList<String> de ) {
		super(orig, id, depart, ship.getLayout());
		arriveDate = arrive;
		dest = de;
	}
	
	protected boolean hasCities( String orig, String... testDest ) {
		if( !orig.equals(origin) ) return false;
		
		for( String testCity : testDest )
			if( !dest.contains(testCity) )
				return false;
		
		return true;
	}
	
	MyDate[] getTripDates() { return new MyDate[] {departDate, arriveDate}; }

	protected void printSimpleString() {
		String str = "\n  " + ID + ", departing: " + departDate.toString() + ", traveling from " + origin + " to " + dest.toString() + " and arriving: " + arriveDate.toString() + ", with sections:";
		System.out.println(str);
	}
	
	MyDate getArriveDate() { return arriveDate; }
	
	protected String toViewingString() {
		String temp = "";
		if( dest.size() == 1 ) temp = dest.get(0).toString();
		else { 
			for( int index = 0; index < dest.size(); index++ ) {
				temp += dest.get(index).toString();
				if( index < dest.size() - 2 )
					temp += ", ";
				else if( index == dest.size() - 2 )
					temp += " and ";
			}
		}
		
		String str = "\n	" + ID + ", departing at: " + departDate.toString() + ", traveling from " + origin + " to " + temp + "; arriving at: " + arriveDate.toString();
		
		if( !super.getSections().isEmpty() ) {
			 str += ", with sections:";
			 
			for(int index = 0; index < super.getSections().size(); index++ ) {
				if( index != 0 ) str += ", ";
				
				str += ( (TransportSection)(super.getSections().values().toArray()[index]) ).toViewingString();
			}
		}
		else
			str += ", without any sections";
		
		return str + ".\n";
	}
}
