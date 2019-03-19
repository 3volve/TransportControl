package transportManagement;

public abstract class TransportSeating {

	private boolean booked;
	final int number;
	final char letter;
	
	TransportSeating( int n, char l ) {
		booked = false;
		number = n;
		letter = l;
	}
	
	void bookSeat() { booked = true; }
	
	boolean isAvailable() { return booked; }
}
