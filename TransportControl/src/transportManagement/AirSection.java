package transportManagement;

import java.util.HashMap;

class Section
{
	private SeatClass seatClass;
	private Seat[][] seats; //A flight section can contain at most 100 rows of seats and at most 10 columns of seats.
	
	Section( SeatClass sC, int r, int c ) {
		seatClass = sC;
		
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
	
	SeatClass getSeatClass() { return seatClass; }
	
	public String toString() {
		return seatClass + " class section containing " + (seats[0].length * seats.length) + " seats";
	}
	
	class DataClass
	{
		String airline, flID;
		int rows, cols;
		SeatClass seatClass;
		HashMap<String, TransportLine> lines;
		
		DataClass(String air, String flid, int r, int c, SeatClass s, HashMap<String, TransportLine> l ) {
			airline = air; flID = flid;
			rows = r; cols = c;
			seatClass = s;
		}
	}
}
