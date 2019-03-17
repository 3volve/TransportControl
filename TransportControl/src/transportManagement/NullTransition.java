package transportManagement;

import java.util.HashMap;

public class NullTransition extends Transition {

	public NullTransition() { super("", "", null, null); }

	@Override
	boolean hasCities(String orig, String... dest) { return false; }

	@Override
	String getID() { return "This is a null Transition"; }

	@Override
	void addSection(TransportSection section) {}

	@Override
	HashMap<SeatClass, TransportSection> getSections() { return new HashMap<SeatClass, TransportSection>(); }

}
