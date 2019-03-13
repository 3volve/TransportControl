package transportManagement;

import java.util.HashMap;

public class NullTransition extends Transition {

	public NullTransition() {}

	@Override
	boolean hasCities(String orig, String... dest) { return false; }

	@Override
	String getID() { return "This is a null Transition"; }

	@Override
	void addSection(Section section) {}

	@Override
	HashMap<SeatClass, Section> getSections() { return new HashMap<SeatClass, Section>(); }

}
