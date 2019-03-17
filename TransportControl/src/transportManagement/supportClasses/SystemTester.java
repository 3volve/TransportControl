package transportManagement.supportClasses;

public class SystemTester {
	public static boolean systemTest( String BaseErrorStr, boolean[] conditions, String... errorMsgs ) {
		for( int index = 0; index < conditions.length; index++ )
			if( conditions[index] ) { 
				System.out.println(BaseErrorStr + errorMsgs[index]);
				return false;
			}
		
		return true;
	}
}
