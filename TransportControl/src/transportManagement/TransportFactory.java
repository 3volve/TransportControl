package transportManagement;

import java.util.HashMap;

interface TransportFactory {

	static TransportLine createTransportLine( String type, String name, HashMap<String, TransportLine> lines ) {
		
		switch( type )
		{
			case( "Air" ) : return AirFactory.createTransportLine(name, lines);
			
			case( "Train" ) : return TrainFactory.createTransportLine(name, lines);
			
			case( "Cruise" ) : return CruiseFactory.createTransportLine(name, lines);
		}
		
		return null;
	}
	
	static Transition createTransition( String type, TransitionDataClass data ) {

		switch( type )
		{
			case( "Air" ) : return AirFactory.createTransition(data);
			
			case( "Train" ) : return TrainFactory.createTransition(data);
			
			case( "Cruise" ) : return CruiseFactory.createTransition(data);
		}
		
		return null;
	}
}
