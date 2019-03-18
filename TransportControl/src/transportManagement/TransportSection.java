package transportManagement;

import transportManagement.supportClasses.TransportClass;

abstract class TransportSection
{
	final TransportClass seatingClass;
	
	TransportSection( TransportClass transClass ) {
		seatingClass = transClass;
	}
	
	abstract void bookSeat( int row, char col);
	
	abstract boolean isSeatAvailable( int row, char col );
	
	TransportClass getTransportClass() { return seatingClass; }
	
	public abstract String toString();
	
	class DataClass
	{
		final String transID, layout;
		final int rows;
		final TransportClass seatClass;
		final TransportLine line;
		
		DataClass(String transid, int r, String lay, TransportClass s, TransportLine l ) {
			line = l; transID = transid;
			rows = r; layout = lay;
			seatClass = s;
		}
	}
}
