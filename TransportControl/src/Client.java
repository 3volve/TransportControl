import java.util.Scanner;

//Added things for testing purposes, remove MyDate and CabinClass after finished testing.
import transportManagement.CabinClass;
import transportManagement.SystemManager;
import transportManagement.supportClasses.SystemMenu;
import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.SystemBuilder;

public class Client
{
	public static void main(String[] args) {
		SystemManager systems = new SystemManager();
		
		Scanner keyboard = new Scanner(System.in);
		
		SystemBuilder systemsBuilder = new SystemBuilder("C:\\Users\\evolv\\git\\TransportControl\\TransportControl\\src\\Transport_Saved_Data.txt", keyboard); //SystemMenu.strPrompt(" default system file location", keyboard)
		System.out.println("\nDefault Air Transport System has now been loaded...");
		systems = systemsBuilder.getSystemManager();
		
		boolean admin = SystemMenu.initializeAdmin(keyboard);
		String type = "";
		
	/*----- For output Testing, Don't forget to remove ----*/
		System.out.println("\nTesting Cruise example set-up has now been loaded...");
		
		systems.createPort("Cruise", "BAD");
		systems.createPort("Cruise", "GAD");
		systems.createPort("Cruise", "JAD");
		systems.createTransportLine("Cruise", "EVIL", "Magistrate", "Mrs. Jennings", "Alexander the Great");
		systems.createTransportLine("Cruise", "GOOD", "Priest", "Robin Hood");
		systems.createTransit("Cruise", "EVIL", "BAD", new MyDate(10, 10, 2020), new MyDate(10, 20, 2020), "EV001", "JAD", "GAD");
		systems.createTransit("Cruise", "EVIL", "JAD", new MyDate(10, 10, 2020), new MyDate(10, 21, 2020), "EV002", "BAD", "GAD");
		systems.createTransit("Cruise", "GOOD", "JAD", new MyDate(10, 10, 2020), new MyDate(10, 21, 2020), "GD001", "GAD");
		systems.createSection("Cruise", "EVIL", "Magistrate", 20, "ABC", CabinClass.family, 750);
		systems.createSection("Cruise", "EVIL", "Magistrate", 30, "A", CabinClass.deluxeFamily, 1750);
		systems.createSection("Cruise", "GOOD", "Priest", 30, "ABC", CabinClass.family, 50);

	/*----- For Testing, Don't forget to remove ----*/
		
		if( !admin ) type = SystemMenu.initializeType(keyboard);
		
		int choice = 0;
		do {
			
			choice = SystemMenu.menuSwitch(type, admin, keyboard);
			
			SystemMenu.optionDecider(choice, type, systems, systemsBuilder, admin, keyboard);
			
			if( choice == SystemMenu.UI_SWITCH ) {
				if( admin ) type = SystemMenu.initializeType(keyboard);
				
				admin = !admin;
			}
			
		} while(choice != SystemMenu.EXIT_NUM);
		
		keyboard.close();
	}
}
