package airport;

class TransportHubFactory
{
	Airport createTransportHub( String n )	{
		if( n.length() == 3 ) return new Airport(n);
		
		System.out.println("Invalid airport naming input.");
		return null;
	}
}
