package transportManagement;

import transportManagement.supportClasses.CabinClass;

public class CruiseSection extends TransportSection {

	CabinClass getCabinClass() {
		return null;
	}
	
	@Override
	void bookSeat(int row, char col) {

	}

	@Override
	boolean isSeatAvailable(int row, char col) {
		return false;
	}

	@Override
	CabinClass getTransportClass() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}
}
