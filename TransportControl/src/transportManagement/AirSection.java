package transportManagement;

import transportManagement.supportClasses.SeatClass;
import transportManagement.supportClasses.TransportClass;

class AirSection extends TransportSection
{
	private Seat[][] seats; //A flight section can contain at most 100 rows of seats and at most 10 columns of seats.
	
	AirSection( TransportClass seatClass2, int r, int c ) {
		seatingClass = seatClass2;
		
		for( int row = 0; row < r; row++ ) {
			seats = new Seat[r][c];
			
			for( int col = 0; col < c; col++ ) 
				seats[row][col] = new Seat(r + 1, (char)(c + 65));
		}
	}
	
	void bookSeat( int row, char col) {
		seats[row][col - 65].bookSeat();
	}
	
	boolean isSeatAvailable( int row, char col ) {
		return seats[row][col - 65].isAvailable();
	}
	
	TransportClass getTransportClass() { return seatingClass; }
	
	public String toString() {
		return seatingClass + " class section containing " + (seats[0].length * seats.length) + " seats";
	}
}
