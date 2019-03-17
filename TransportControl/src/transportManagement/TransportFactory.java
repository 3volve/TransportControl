package transportManagement;

public interface TransportFactory {

	static TransportLine createTransportLine( String type, String... name ) {
		
		switch( type )
		{
			case( "Air" ) : return AirFactory.createTransportLine(name[0]);
			
			case( "Train" ) : return TrainFactory.createTransportLine(name[0]);
			
			case( "Cruise" ) : return CruiseFactory.createTransportLine(name);
		}
		
		return null;
	}
	
	static Transition createTransition( String type, Transition.DataClass data ) {

		switch( type )
		{
			case( "Air" ) : return AirFactory.createTransition(data);
			
			case( "Train" ) : return TrainFactory.createTransition(data);
			
			case( "Cruise" ) : return CruiseFactory.createTransition(data);
		}
		
		return new NullTransition();
	}
	
	static TransportSection createSection( String type, AirSection.DataClass data ) {

		switch( type )
		{
			case( "Air" ) : return AirFactory.createSection(data);
			
			case( "Train" ) : return TrainFactory.createSection(data);
			
			case( "Cruise" ) : return CruiseFactory.createSection(data);
		}
		
		return new NullSection();
	}
}
