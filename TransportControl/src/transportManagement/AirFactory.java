package transportManagement;

public class AirFactory implements TransportFactory {

	public TransportLine createTransportLine( String name ) {
		return new Airline( name );
	}

	public Transition createTransition( String orig, String dest, int year, int month, int day, String id ) {
		return new Flight( orig, dest, year, month, day, id );
	}

}
