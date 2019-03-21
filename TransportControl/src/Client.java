import java.util.Scanner;

import transportManagement.SystemManager;
import transportManagement.supportClasses.SystemMenu;
import transportManagement.supportClasses.SystemBuilder;

public class Client
{
	public static void main(String[] args) {
		SystemManager systems = new SystemManager();
		
		Scanner keyboard = new Scanner(System.in);
		
		SystemBuilder systemsBuilder = new SystemBuilder(SystemMenu.strPrompt("default system file location", keyboard), keyboard);
		System.out.println("Default Air Transport System has now been loaded...");
		systems = systemsBuilder.getSystemManager();
		
		String type = "";
		boolean admin = false;
		
		do {
			type = SystemMenu.strPrompt("transport type to modify here[Air or Cruise]", keyboard);
			
			if( !type.equals("Air") && !type.equals("Cruise") )
				System.out.println("That was not a valid transport type, ");
		} while( !type.equals("Air") && !type.equals("Cruise") );
		
		int choice = 0;
		do {
			choice = SystemMenu.menuSwitch(type, admin, keyboard);
			
			SystemMenu.optionDecider(choice, type, systems, systemsBuilder, admin, keyboard);
			
			if( choice == SystemMenu.UI_SWITCH ) admin = !admin;
			
		} while(choice != SystemMenu.EXIT_NUM);
		
		keyboard.close();
	}
}
