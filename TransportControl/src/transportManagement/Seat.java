package transportManagement;

class Seat extends TransportSeating
{
	private boolean isWindow;
	private boolean isAisle;
	
	Seat( int row, char col, boolean window, boolean aisle )
	{ super(row, col); isWindow = window; isAisle = aisle; }

	boolean isWindowSeat() { return isWindow; }
	
	boolean isAisleSeat() { return isAisle; }
	
	@Override
	public String toString() { return "Seat " + number + letter; }
}
