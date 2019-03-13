package transportManagement;

import java.util.HashMap;

interface TransportFactory {

	TransportLine createTransportLine( String name, HashMap<String, TransportLine> lines );
	
	Transition createTransition( String lName, String orig, String dest, int year, int month, int day, String id, HashMap<String, TransportLine> airlines, HashMap<String, City> cities );
	
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
