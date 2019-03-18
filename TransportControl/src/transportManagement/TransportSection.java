package transportManagement;

import java.util.HashMap;

import transportManagement.supportClasses.TransportClass;

abstract class TransportSection
{
	protected TransportClass seatingClass;
	
	abstract void bookSeat( int row, char col);
	
	abstract boolean isSeatAvailable( int row, char col );
	
	abstract TransportClass getTransportClass();
	
	public abstract String toString();
	
	class DataClass
	{
		String airline, flID;
		int rows, cols;
		TransportClass seatClass;
		HashMap<String, TransportLine> lines;
		
		DataClass(String air, String flid, int r, int c, TransportClass s, HashMap<String, TransportLine> l ) {
			airline = air; flID = flid;
			rows = r; cols = c;
			seatClass = s;
		}
	}
}
