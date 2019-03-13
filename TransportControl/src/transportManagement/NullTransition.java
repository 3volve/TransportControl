package transportManagement;

import java.util.HashMap;

public class NullTransition extends Transition {

	public NullTransition() {}

	@Override
	boolean hasCities(String orig, String... dest) { return false; }

	@Override
	String getID() { return "This is a null Transition"; }

	@Override
	void addSection(AirSection section) {}

	@Override
	HashMap<SeatClass, AirSection> getSections() { return new HashMap<SeatClass, AirSection>(); }

}
