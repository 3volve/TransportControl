package transportManagement;

import java.util.HashMap;

abstract class Transition {

	abstract boolean hasCities( String orig, String... dest );
	
	abstract String getID();

	abstract void addSection(Section section);

	abstract HashMap<SeatClass, Section> getSections();
}
