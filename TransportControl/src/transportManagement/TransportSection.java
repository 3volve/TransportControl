package transportManagement;

import java.util.HashMap;

public abstract class TransportSection
{

	class DataClass
	{
		String airline, flID;
		int rows, cols;
		SeatClass seatClass;
		HashMap<String, TransportLine> lines;
		
		DataClass(String air, String flid, int r, int c, SeatClass s, HashMap<String, TransportLine> l ) {
			airline = air; flID = flid;
			rows = r; cols = c;
			seatClass = s;
		}
	}
}
