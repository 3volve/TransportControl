package transportManagement;

class Seat
{
	private boolean isWindow;
	private boolean isAisle;
	private boolean booked;
	
	Seat( boolean window, boolean aisle )
	{ isWindow = window; isAisle = aisle; booked = false; }
	
	void bookSeat() { booked = true; }

	boolean isWindowSeat() { return isWindow; }
	
	boolean isAisleSeat() { return isAisle; }

	boolean isAvailable() { return booked; }
}
