package transportManagement;

import java.util.HashMap;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.TransportClass;

class Flight extends Transition
{
	private String dest;
	
	Flight( String orig, MyDate depart, String id, String de )
	{ super(orig, id, depart, new HashMap<TransportClass, TransportSection>()); dest = de; }
	
	protected void addSection( TransportSection section ) { super.getSections().put(section.getTransportClass(), section); }

	protected boolean hasCities( String o, String... d ) {
		if( o.equals(origin) && d[0].equals(dest) ) return true;
		
		return false;
	}
	
	public String toString() {
		return ID + "|" + departDate.toFileString() + "|" + origin + "|" + dest + super.getSections().values().toString();
	}
	
	@Override
	public String toViewingString() {
		String str = "\n	" + ID + ", departing: " + departDate.toString() + ", traveling from " + origin + " to " + dest;
		
		if( !super.getSections().isEmpty() ) {
			 str += ", with sections:";
			for(int index = 0; index < super.getSections().size(); index++ ) {
				if( index != 0 ) str += ", ";
				
				str += ((TransportSection) (super.getSections().values().toArray()[index])).toViewingString();
			}
			 super.getSections().values().toString();
		}
		return str;
	}
}
