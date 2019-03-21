package transportManagement;

public abstract class TransportSeating {

	private boolean booked;
	final private int number;
	final private char letter;
	
	TransportSeating( int n, char l ) {
		booked = false;
		number = n;
		letter = l;
	}
	
	void bookSeat() { booked = true; }
	
	boolean isAvailable() { return !booked; }
	
	char getLetter() { return letter; }

	int getRow() { return number; }
	
	String bookStr() { 
		if( booked ) return "X";
		else return "O";
	}
}
