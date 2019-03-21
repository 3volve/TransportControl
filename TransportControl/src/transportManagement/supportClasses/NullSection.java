package transportManagement.supportClasses;

import transportManagement.TransportSection;

public class NullSection extends TransportSection
{
	public NullSection() { super(null, 0); }

	protected void bookSeat( int row, char col) {}
	
	protected boolean isSeatAvailable( int row, char col ) { return false; }
	
	@Override
	public String toViewingString() { return "This is a Null Section"; }

	@Override
	public String toString() { return "NULL"; }
}
