package transportManagement;

import transportManagement.supportClasses.TransportClass;

public abstract class TransportSection
{
	final TransportClass seatingClass;
	int pricing;
	
	protected TransportSection( TransportClass transClass, int price ) {
		seatingClass = transClass; pricing = price;
	}
	
	protected abstract void bookSeat( int row, char col);
	
	protected abstract boolean isSeatAvailable( int row, char col );
	
	void setPricing( int price ) { pricing = price; }
	
	TransportClass getTransportClass() { return seatingClass; }
	
	protected abstract void printLayout();

	protected abstract void printDetailedString();
	
	protected abstract String toViewingString();
	
	class DataClass
	{
		final String transID, layout;
		final int number, pricing;
		final TransportClass seatClass;
		final TransportLine line;
		
		DataClass(String transid, int n, int price, String lay, TransportClass s, TransportLine l ) {
			line = l; transID = transid;
			number = n; layout = lay;
			seatClass = s; pricing = price;
		}
	}
}
