package transportManagement.supportClasses;

import transportManagement.Transition;

public class NullTransition extends Transition {

	public NullTransition() { super("", "This is a null transition.", null, null); }
	
	protected boolean hasCities(String orig, String... dest) { return false; }

}
