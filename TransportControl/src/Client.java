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
		
		boolean admin = SystemMenu.initializeAdmin(keyboard);
		String type = "";
		
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
