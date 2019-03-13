package transportManagement;

import java.util.HashMap;

abstract class Transition {

	abstract boolean hasCities( String orig, String... dest );
	
	abstract String getID();

	abstract void addSection(AirSection section);

	abstract HashMap<SeatClass, AirSection> getSections();
	
	class DataClass {
		final String lName, orig, ID;
		final String[] dest;
		final int year, month, day;
		final HashMap<String, TransportLine> lines;
		final HashMap<String, City> cities;
		
		DataClass( String n, String o, int y, int m, int da, String id, HashMap<String, TransportLine> l, HashMap<String, City> c, String... de ) {
			lName = n; orig = o; dest = de; ID = id;
			year = y; month = m; day = da;
			lines = l; cities = c;
		}
	}
}
