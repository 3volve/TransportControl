package transportManagement;

import transportManagement.supportClasses.TransportClass;

public abstract class TransportSection
{
	final TransportClass seatingClass;
	
	protected TransportSection( TransportClass transClass ) {
		seatingClass = transClass;
	}
	
	protected abstract void bookSeat( int row, char col);
	
	protected abstract boolean isSeatAvailable( int row, char col );
	
	TransportClass getTransportClass() { return seatingClass; }
	
	public abstract String toString();
	
	class DataClass
	{
		final String transID, layout;
		final int number;
		final TransportClass seatClass;
		final TransportLine line;
		
		DataClass(String transid, int n, String lay, TransportClass s, TransportLine l ) {
			line = l; transID = transid;
			number = n; layout = lay;
			seatClass = s;
		}
	}
}
