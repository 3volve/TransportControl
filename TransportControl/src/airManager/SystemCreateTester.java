package airManager;

public class SystemCreateTester {
	static boolean systemCreateTest( String BaseErrorStr, boolean[] conditions, String... errorMsgs ) {
		for( int index = 0; index < conditions.length; index++ )
			if( conditions[index] ) { 
				System.out.println(BaseErrorStr + errorMsgs[index]);
				return false;
			}
		
		return true;
	}
}
