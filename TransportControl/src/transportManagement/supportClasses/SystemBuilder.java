package transportManagement.supportClasses;

import java.io.*;
import java.util.Scanner;

import transportManagement.SystemManager;

public class SystemBuilder {

	private SystemManager systemManager;

	
	public SystemBuilder( String fileName ) {
		systemManager = new SystemManager();
		
		Scanner fin = null;
		
		try { fin = new Scanner(new File(fileName)); }
		
		catch( FileNotFoundException e ) { System.out.println(e); }
		
		String tempStr = "";
		String[] splitStr;
		
		while( fin.hasNext() ) { tempStr = tempStr + fin.nextLine(); } 
		
		splitStr = tempStr.trim().split("\\{");
		splitStr[1] = splitStr[1].substring(0, splitStr[1].length() - 1);
		
		airportBuilder( splitStr[0]);//takes the first chunk of [ sag, asf, ars ] {...
		airlineBuilder( splitStr[1]);//takes the second half of all the airlines {asfdg....}
	}
	
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
		
		for( String tempStr : splitStr ) {
			tempSplit = tempStr.split("\\[", 2);
			airlineName = tempSplit[0];
			flightStr = tempSplit[1];
			
			systemManager.createTransportLine("Air", airlineName, "");
			
			flightSectionSplitter(flightStr, airlineName);
		}
	}
	
	private void flightSectionSplitter( String allFlights, String airline ) {
		System.out.println("\nDEBUG: " + allFlights);
		String[] splitStr = allFlights.split("\\], "), tempSplit;
		String flightStr, sectionStr;
		String flightID, origin, destination;
		int year, month, day, hour, minute;
		MyDate depart;
		
		for( String tempStr : splitStr ) {
			tempSplit = tempStr.split("\\[");
			flightStr = tempSplit[0];
			sectionStr = tempSplit[1];
			
			System.out.println("DEBUG: " + tempSplit[0] + ", " + tempSplit[1]);
			
			tempSplit = flightStr.split("|");
			flightID = tempSplit[0];
			origin = tempSplit[2];
			destination = tempSplit[3];
			
			System.out.println("DEBUG: " + tempSplit[0] + ", " + tempSplit[1] + ", " + tempSplit[2] + ", " + tempSplit[3]);
			
			tempSplit = tempSplit[1].split(", ");
			
			year = Integer.parseInt(tempSplit[0]); month = Integer.parseInt(tempSplit[1]); day = Integer.parseInt(tempSplit[2]);
			hour = Integer.parseInt(tempSplit[3]); minute = Integer.parseInt(tempSplit[4]);
			depart = new MyDate(minute, hour, day, month, year);
			
			systemManager.createTransit("Air", airline, origin, depart, depart, flightID, destination);
			
			sectionBuilder(sectionStr, airline, flightID );
		}
	}
	
	private void sectionBuilder( String section, String airline, String flightID ) {
		
	}
	
	public SystemManager getSystemManager() { return systemManager; }
}
