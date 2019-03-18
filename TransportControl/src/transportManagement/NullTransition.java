package transportManagement;

import java.util.HashMap;

import transportManagement.supportClasses.TransportClass;

public class NullTransition extends Transition {

	public NullTransition() { super("", "This is a null transition.", null, null); }

	@Override
	boolean hasCities(String orig, String... dest) { return false; }

	@Override
	void addSection(TransportSection section) {}

	@Override
	HashMap<TransportClass, TransportSection> getSections() { return new HashMap<TransportClass, TransportSection>(); }

}
