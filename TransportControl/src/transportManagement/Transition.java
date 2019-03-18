package transportManagement;

import java.util.ArrayList;
import java.util.HashMap;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.TransportClass;

abstract class Transition {

	final String origin, ID;
	final MyDate departDate;
	private HashMap<TransportClass, TransportSection> sections;
	
	protected Transition( String orig, String id, MyDate depart, HashMap<TransportClass,TransportSection> hashMap )
	{ origin = orig; ID = id; departDate = depart; sections = hashMap; }
	
	abstract boolean hasCities( String orig, String... dest );
	
	abstract void addSection(TransportSection section);

	HashMap<TransportClass, TransportSection> getSections() { return sections; }
	
	class DataClass {
		final String orig, ID;
		final TransportLine line;
		final ArrayList<String> dest;
		final MyDate departDate, arriveDate;
		
		DataClass( TransportLine l, String o, MyDate deD, MyDate arD, String id, String... de ) {
			line = l; orig = o; ID = id;
			departDate = deD; arriveDate = arD;
			
			dest = new ArrayList<String>();
			for( String d : de ) dest.add(d);
		}
	}
}
