/**
 * 
 */

/**
 * @author Sabine
 *
 */
public class ArrayCompare {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] incomingDataList = { "1", "9", "10" };
		String[] dbDataList = { "1", "9" };
		String[] dudeDontMatch = {};
		int bubba = 0;
		boolean foundIt = false;
		for (int i = 0; i < incomingDataList.length; i++){
			foundIt = false;
			for(int j = 0; j < dbDataList.length; j++){
				if(incomingDataList[i] == dbDataList[j]){
				  if(!foundIt){
					System.out.println(incomingDataList[i]);
					foundIt = true;
				  } else {
					System.out.println("========");
					dudeDontMatch[bubba++] = incomingDataList[i];
				  }
			   }
			}
		}
		if(dudeDontMatch.length > 0){
	      for (int r = 0; r < dudeDontMatch.length; r++){
	    	System.out.println(dudeDontMatch[r]);
	      }
		}
	}
}
