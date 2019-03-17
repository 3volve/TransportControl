import transportManagement.SystemManager;
import transportManagement.supportClasses.MyDate;

public class Client
{
	public static void main(String[] args) {
		SystemManager systems = new SystemManager();
		
		systems.createPort("Air", "JON");
		systems.createPort("Air", "DEN");
		systems.createPort("Cruise", "JON");
		systems.createPort("Cruise", "DEN");
		systems.createPort("Cruise", "BOB");
		systems.createPort("Cruise", "EVO");
		systems.createPort("Train", "JON");
		
		systems.createTransportLine("Air", "JONNY");
		systems.createTransportLine("Air", "JONNY");
		systems.createTransportLine("Cruise", "Cradl");
		systems.createTransportLine("Cruise", "Cradl");
		systems.createTransportLine("Cruise", "Alimo");
		
		systems.createTransit("Air", "JONNY", "JON", new MyDate(3, 20, 2020), null, "Jon-ded", "DEN");
		systems.createTransit("Cruise", "Cradil", "JON", new MyDate(3, 20, 2019), new MyDate(3, 29, 2019), "PartyBoat", "DEN", "BOB", "EVO");
		systems.createTransit("Cruise", "Cradil", "JON", new MyDate(4, 20, 2019), new MyDate(5, 20, 2019), "PartyBoat", "DEN", "BOB", "EVO");
	}
}
