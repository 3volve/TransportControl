package transportManagement;

import transportManagement.supportClasses.TransportClass;

class NullSection extends TransportSection
{
	NullSection() {
		super(null);
	}

	void bookSeat( int row, char col) {}
	
	boolean isSeatAvailable( int row, char col ) { return false; }
	
	TransportClass getTransportClass() { return null; }
	
	public String toString() { return "This is a Null Section"; }
}
