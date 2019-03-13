package transportManagement;

import java.util.HashMap;

class TransitionDataClass {
	final String lName, orig, ID;
	final String[] dest;
	final int year, month, day;
	final HashMap<String, TransportLine> lines;
	final HashMap<String, City> cities;
	
	TransitionDataClass( String n, String o, int y, int m, int da, String id, HashMap<String, TransportLine> l, HashMap<String, City> c, String... de ) {
		lName = n; orig = o; dest = de; ID = id;
		year = y; month = m; day = da;
		lines = l; cities = c;
	}
}
