package transportManagement;

import java.util.HashMap;

public class Trip extends Transition {

	@Override
	boolean hasCities(String orig, String... dest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void addSection(TransportSection section) {
		// TODO Auto-generated method stub

	}

	@Override
	HashMap<SeatClass, TransportSection> getSections() {
		// TODO Auto-generated method stub
		return null;
	}

}
