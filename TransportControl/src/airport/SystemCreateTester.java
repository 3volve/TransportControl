package airport;

public class SystemCreateTester {
	static boolean systemCreateTest( String errorStr, boolean[] conditions, String... errorMsg ) {
		for( int index = 0; index < conditions.length; index++ )
			if( conditions[index] ) { 
				System.out.println(errorStr + errorMsg[index]);
				return false;
			}
		
		return true;
	}
}
