package airport;

public class Airport implements Comparable<Airport>
{
	private String name;
	
	Airport() { name = "null"; }
	
	Airport( String n ) { name = n; }
	
	@Override
	public String toString() { return name; }
	
	boolean equals( Airport that )
	{ return name.equals(that.toString()); }

	@Override
	public int compareTo( Airport that )
	{ return name.compareTo(that.toString()); }
}
