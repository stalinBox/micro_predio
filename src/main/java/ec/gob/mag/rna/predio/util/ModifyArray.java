package ec.gob.mag.rna.predio.util;

public class ModifyArray {
	
	public static String[] copyArrayString(String[] array, int limitInitial, int LimitFinal) {
		String [] arrayReturn=null;
		for( ; limitInitial <= LimitFinal ; limitInitial++) {
			arrayReturn[limitInitial]=array[limitInitial];		
		}
		return arrayReturn;
	}

}
