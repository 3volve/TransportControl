package transportManagement;

import java.util.HashMap;

public class TrainFactory implements TransportFactory {

	@Override
	public TransportLine createTransportLine(String name,  HashMap<String, TransportLine> trainlines) {
		return null;
	}

	@Override
	public Transition createTransition( String lName, String orig, String dest, int year, int month, int day, String id,  HashMap<String, TransportLine> trainlines, HashMap<String, City> cities ) {
		return null;
	}

}
