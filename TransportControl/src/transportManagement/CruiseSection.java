package transportManagement;

import transportManagement.supportClasses.TransportClass;

public class CruiseSection extends TransportSection {

	CruiseSection( TransportClass seatClass ) {
		super(seatClass);
	}
	
	@Override
	void bookSeat(int row, char col) {

	}

	@Override
	boolean isSeatAvailable(int row, char col) {
		return false;
	}

	@Override
	public String toString() {
		return null;
	}
}
