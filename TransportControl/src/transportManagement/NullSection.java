package transportManagement;

import java.util.HashMap;

class NullSection extends TransportSection
{
	void bookSeat( int row, char col) {}
	
	boolean isSeatAvailable( int row, char col ) { return false; }
	
	SeatClass getSeatClass() { return null; }
	
	public String toString() { return "This is a Null Section"; }
}
