package transportManagement.supportClasses;

public enum SeatClass implements TransportClass
{ first, business, economy; 
	public static String toString( TransportClass seat ) {
		if( seat == first ) return "F";
		if( seat == business ) return "B";
		else return "E";
	}
}
