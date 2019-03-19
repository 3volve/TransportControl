package transportManagement;

import transportManagement.supportClasses.NullSection;
import transportManagement.supportClasses.NullTransition;

class TrainFactory implements TransportFactory {

	static TransportLine createTransportLine( String name ) {
		int nameMinSize = 1, nameMaxSize = 5;
		
		if( name.length() < nameMinSize || nameMaxSize < name.length() )
			System.out.println("Trainline " + name + " was unable to be created: incorrect trainline naming syntax.");
		else return new Trainline(name);
		
		return null;
	}

	static Transition createTransition( Transition.DataClass data ) {
		
		
		return new NullTransition();
	}
	
	static TransportSection createSection( TransportSection.DataClass data ) {
		
		
		return new NullSection();
	}

}
