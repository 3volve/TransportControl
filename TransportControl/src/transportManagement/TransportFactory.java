package transportManagement;

interface TransportFactory {

	TransportLine createTransportLine( String name );
	
	Transition createTransition( String orig, String dest, int year, int month, int day, String id );
	
	static TransportFactory createSpecificFactory( String type ) {
		switch( type )
		{
			case( "Air" ) : return new AirFactory();
			
			case( "Train" ) : return new TrainFactory();
			
			case( "Cruise" ) : return new CruiseFactory();
		}
		
		return null;
	}
}
