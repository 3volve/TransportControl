package airManager;

public class FlightSection
{
	private SeatClass seatClass;
	private Seat[][] seats; //A flight section can contain at most 100 rows of seats and at most 10 columns of seats.
	
	FlightSection( SeatClass sC, int r, int c ) {
		seatClass = sC;
		
		for( int row = 0; row < r; row++ ) {
			seats = new Seat[r][c];
			
			for( int col = 0; col < c; col++ ) 
				seats[row][col] = new Seat(r + 1, (char)(c + 65));
		}
	}
	
	//returns true iff the section has some seats that are not booked
	boolean hasAvailableSeats() {
		return findAvailableSeat() != null;
	}
	
	//book an available seat
	void bookSeat() {
		findAvailableSeat().bookSeat();
	}
	
	void bookCertainSeat( int row, char col) {
		seats[row][col - 65].bookSeat();
	}
	
	boolean isSeatAvailable( int row, char col ) {
		return seats[row][col - 65].isAvailable();
	}
	
	SeatClass getSeatClass() { return seatClass; }
	
	Seat findAvailableSeat() {
		for( Seat[] row : seats ) for( Seat col : row )
			if( col.isAvailable() ) return col;
		
		return null;
	}
	
	public String toString() {
		return seatClass + " class section containing " + (seats[0].length * seats.length) + " seats";
	}
}
