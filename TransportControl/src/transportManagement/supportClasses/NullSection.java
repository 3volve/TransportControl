package transportManagement.supportClasses;

import transportManagement.TransportSection;

public class NullSection extends TransportSection
{
	public NullSection() { super(null); }

	protected void bookSeat( int row, char col) {}
	
	protected boolean isSeatAvailable( int row, char col ) { return false; }
	
	public String toString() { return "This is a Null Section"; }
}
