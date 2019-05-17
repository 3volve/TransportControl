package transportManagement;

public class Cabin extends TransportSeating implements Cloneable
{
	Cabin( int cabin, char deck ) { super(cabin, deck); }
	
	protected Cabin clone() {
		Cabin clone = new Cabin(getRow(), getLetter());
		
		if( !isAvailable() ) clone.bookSeat();
		
		return clone;
	}
}
