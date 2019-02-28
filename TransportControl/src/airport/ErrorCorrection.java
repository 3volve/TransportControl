package airport;

import java.util.*;

class ErrorCorrection {

	static String fixString( String strRequire ) {
		Scanner fin = new Scanner(System.in);
		
		System.out.print(strRequire);
		String fixed = fin.next();
		
		fin.close();
		
		return fixed;
	}
}
