package transportManagement.supportClasses;

import java.io.*;
import java.util.Scanner;

import transportManagement.SystemManager;

public class SystemBuilder {

	private SystemManager systemManager;
	
	public SystemBuilder( String fileName, Scanner keyboard ) {
		systemManager = new SystemManager();
		
		Scanner fin = null; File file;
		
		do {
			file = new File(fileName);
			
			if( file.exists() ) {
				try { fin = new Scanner(file); }
			
				catch( FileNotFoundException e ) { e.printStackTrace(); }
			
				String tempStr = "";
				String[] splitStr;
				
				while( fin.hasNext() ) { tempStr = tempStr + fin.nextLine(); } 
				
				splitStr = tempStr.trim().split("\\{");
				splitStr[1] = splitStr[1].substring(0, splitStr[1].length() - 1);
				
				airportBuilder(splitStr[0]);
				airlineBuilder(splitStr[1]);
			}
			
			else {
				System.out.print("Given file does not exist, please give us an alternative: ");
				fileName = SystemMenu.strPrompt("new file", keyboard);
			}
		} while( !file.exists() );
		
		fin.close();
	}
	
	public SystemManager getSystemManager() { return systemManager; }
	
	private void airportBuilder( String allAirports ) {
		String[] splitStr = allAirports.split(", ");
		int indexSize = splitStr.length - 1;
		if( splitStr[0].length() > 3 ) splitStr[0] = splitStr[0].substring(1);
		
		if( splitStr[indexSize].length() > 3 ) splitStr[indexSize] = splitStr[indexSize].substring(0, 3);
		
		for( String airport : splitStr )
			systemManager.createPort("Air", airport);
	}
	
	private void airlineBuilder( String allAirlines ) {
		String[] splitStr = allAirlines.split("\\]\\], "), tempSplit;
		String airlineName, flightStr;

		splitStr[splitStr.length - 1] = splitStr[splitStr.length - 1].substring(0, splitStr[splitStr.length - 1].length() - 2);	
		
		for( String tempStr : splitStr ) {
			tempSplit = tempStr.split("\\[", 2);
			airlineName = tempSplit[0];
			flightStr = tempSplit[1];
			
			systemManager.createTransportLine("Air", airlineName, "");
			
			flightBuilder(flightStr, airlineName);
		}
	}
	
	private void flightBuilder( String allFlights, String airline ) {
		String[] splitStr = allFlights.split("\\], "), tempSplit;
		String flightStr, sectionStr;
		String flightID, origin, destination;
		int year, month, day, hour, minute;
		MyDate depart = null;
		
		for( String tempStr : splitStr ) {
			tempSplit = tempStr.split("\\[");
			flightStr = tempSplit[0];
			sectionStr = tempSplit[1];
			
			tempSplit = flightStr.split("\\|");
			flightID = tempSplit[0];
			origin = tempSplit[2];
			destination = tempSplit[3];
			
			tempSplit = tempSplit[1].split(", ");
			
			year = Integer.parseInt(tempSplit[0]); month = Integer.parseInt(tempSplit[1]); day = Integer.parseInt(tempSplit[2]);
			hour = Integer.parseInt(tempSplit[3]); minute = Integer.parseInt(tempSplit[4]);
			depart = new MyDate(minute, hour, day, month, year);

			systemManager.createTransit("Air", airline, origin, depart, depart, flightID, destination);
			
			sectionBuilder(sectionStr, airline, flightID );
		}
	}
	
	private void sectionBuilder( String allSections, String airline, String flightID ) {
		String[] splitStr = allSections.split(","), tempSplit;
		String layout;
		int price, rows;
		SeatClass seatClass;
		
		for( String sectionStr : splitStr ) {
			tempSplit = sectionStr.split(":");
			
			seatClass = strToSeatClass(tempSplit[0].trim());
			price = Integer.parseInt(tempSplit[1]);
			layout = tempSplit[2].trim();
			rows = Integer.parseInt(tempSplit[3]);
			
			systemManager.createSection("Air", airline, flightID, rows, layout, seatClass, price);
		}
			
	}
	
	private SeatClass strToSeatClass( String str ) {	
		
		switch(str)
		{
			case("B") : return SeatClass.business;
			case("F") : return SeatClass.first;
			case("E") : return SeatClass.economy;
		}
		
		return null;
	}
}
