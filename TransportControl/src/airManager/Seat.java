package airManager;

public class Seat
{
	private int row;
	private char col;
	private boolean booked;
	
	Seat( int r, char c ) { row = r; col = c; booked = false; }
	
	void bookSeat() { booked = true; }
	
	boolean isAvailable() { return booked; }
}
