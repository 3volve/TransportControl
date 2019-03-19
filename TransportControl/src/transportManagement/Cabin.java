package transportManagement;

public class Cabin extends TransportSeating
{
	Cabin( int cabin, char deck ) { super(cabin, deck); }
	
	@Override
	public String toString() { return "Cabin " + number + letter; }
}
