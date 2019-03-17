package transportManagement;

import java.util.HashMap;

class CruiseFactory implements TransportFactory {

	static TransportLine createTransportLine( String name,  HashMap<String, TransportLine> cruiselines ) {
		return new Cruiseline(name);
	}

	static Transition createTransition( Transition.DataClass data ) {
		return new CruiseTrip(data.orig, data.ID, data.departDate, data.arriveDate, data.dest);
	}
	
	static CruiseSection createSection( AirSection.DataClass data ) {
		
		
		return null;
	}

}
