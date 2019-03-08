package airport;

import java.util.ArrayList;

public class FlightSection
{
	private SeatClass seatClass;
	private Seat[] rows, cols; //A flight section can contain at most 100 rows of seats and at most 10 columns of seats.
	
	FlightSection( SeatClass sC, int r, int c ) {
		seatClass = sC;
		rows = new Seat[r];
		cols = new Seat[c];
	}
	
	//returns true iff the section has some seats that are not booked
	boolean hasAvailableSeats() { return false; }
	
	//book an available seat
	void bookSeat() {}
	
	SeatClass getSeatClass() { return seatClass; }
}
