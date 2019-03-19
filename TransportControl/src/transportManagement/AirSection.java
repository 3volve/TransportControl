package transportManagement;

import transportManagement.supportClasses.TransportClass;

class AirSection extends TransportSection
{
	private TransportSeating[][] seats; //A flight section can contain at most 100 rows of seats and at most 10 columns of seats.
	
	AirSection( TransportClass seatClass, int r, String layout ) {
		super(seatClass);
		int maxCol = layoutColumns(layout);
		
		for( int row = 0; row < r; row++ ) {
			seats = new Seat[r][maxCol];
			
			for( int col = 0; col < maxCol; col++ ) 
				seats[row][col] = new Seat(row, (char)(col + 61), layoutWindowSeat(col, maxCol), layoutAisleSeat(layout, col));
		}
	}
	
	protected void bookSeat( int row, char col) {
		seats[row][col - 65].bookSeat();
	}
	
	protected boolean isSeatAvailable( int row, char col ) {
		return seats[row][col - 65].isAvailable();
	}
	
	@Override
	public String toString() {
		return seatingClass + " class section containing " + (seats[0].length * seats.length) + " seats";
	}
	
	
	int layoutColumns( String layout ) {
		int columns = 3;
		
		switch( layout )
		{
			case("medium") : columns = 4;
			case("wide") : columns = 10;
		}
		
		return columns;
	}
	
	boolean layoutWindowSeat( int col, int maxCol ) {
		if( col == 0 || col == maxCol - 1 ) return true;
		else return false;
	}
	
	boolean layoutAisleSeat( String layout, int col) {
		switch( layout )
		{
			case("small")  : return col == 0 || col == 1;
			case("medium") : return col == 1 || col == 2;
			case("wide")   : return col == 2 || col == 3 || col == 6 || col == 7;
		}
		
		System.out.println("Error creating Seat: incorrect given layout.");
		return false;
	}
}
