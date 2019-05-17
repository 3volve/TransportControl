package transportManagement;

import transportManagement.supportClasses.TransportClass;

class AirSection extends TransportSection
{
	private TransportSeating[][] seats; //A flight section can contain at most 100 rows of seats and at most 10 columns of seats.
	private String layout;
	
	AirSection( TransportClass seatClass, int r, String lay, int price ) {
		super(seatClass, price);
		layout = lay;
		int maxCol = layoutColumns(layout);
		
		seats = new Seat[r][maxCol];
		
		for( int row = 0; row < r; row++ ) {
			for( int col = 0; col < maxCol; col++ ) 
				seats[row][col] = new Seat(row + 1, (char)(col + 65), layoutWindowSeat(col, maxCol), layoutAisleSeat(layout, col));
		}
	}
	
	protected void bookSeat( int row, char col) {
		seats[row][col - 65].bookSeat();
		System.out.println("Seat " + row + col + " has now been booked.");
	}
	
	protected boolean isSeatAvailable( int row, char col ) {
		return seats[row][col - 65].isAvailable();
	}
	
	boolean isSeatPrefAvailable( String seatingPref ) {
		for( Seat[] rows : (Seat[][])seats ) 
			for( Seat seat : rows ) 
				if( (seatingPref.equals("window") && seat.isWindowSeat()) ||
						(seatingPref.equals("aisle") && seat.isAisleSeat()) ) 
					if( seat.isAvailable() )
						return true;
				
		return false;
	}

	void bookSeatByPref( String seatingPref ) {
		boolean foundSeat = false;
		
		for( Seat[] rows : (Seat[][])seats ) {
			for( Seat seat : rows ) {
				if( (seatingPref.equals("window") && seat.isWindowSeat()) ||
					 seatingPref.equals("Aisle") && seat.isAisleSeat() ) {
					if( seat.isAvailable() && !foundSeat ) {
						seat.bookSeat();
						System.out.println("Seat " + seat.toString() + " by the " + seatingPref + ", has now been booked.");
						foundSeat = true;
					}
				}
			}
		}
	}
	
	int numberSeatsAvailable() {
		int count = 0;
		
		for( TransportSeating[] rows : (Seat[][])seats ) 
			for( TransportSeating seat : rows ) 
				if( seat != null) 
					if( seat.isAvailable() ) count++;
					
		 return count;
	}		
	
	int layoutColumns( String layout ) {
		int columns = 3;
		
		switch( layout )
		{
			case("M") : columns = 4;
						break;
			case("W") : columns = 10;
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
			case("S") : return col == 0 || col == 1;
			case("M") : return col == 1 || col == 2;
			case("W") : return col == 2 || col == 3 || col == 6 || col == 7;
		}
		
		System.out.println("Error creating Seat: incorrect given layout.");
		return false;
	}
	
	protected void printLayout() {
		int counter = 0;
		boolean aisle = false;
		
		while( counter < layoutColumns(layout) ) {
			if( aisle ) {
				for( int row = 0; row < seats.length; row++ ) { System.out.print("="); }
				System.out.println();
			}
			
			if( layoutAisleSeat(layout, counter) ) aisle = !aisle;
			
			
			for( int row = 0; row < seats.length; row++ ) {
				for( int col = 0; col < seats[row].length; col++ ) {
					if( col == counter ) System.out.print(seats[row][col].bookStr());
				}
			}
			
			System.out.println();
			counter++;
		}
				
	}
	
	protected void printDetailedString() {
		System.out.println("\n    " + seatingClass + " class, costing " + pricing + " per seat,"
				+ " with " + numberSeatsAvailable() + " seats still available\n");
		printLayout();
	}
	
	protected String toViewingString() {
		return "\n		" + seatingClass + " class, costing " + pricing + " per seat,"
				+ " with " + numberSeatsAvailable() + " seats still available";
	}
	
	@Override
	public String toString() {
		return "" + SeatClass.toString(seatingClass) + ":" + pricing + ":" + layout + ":" + seats[0].length;
	}	
}
