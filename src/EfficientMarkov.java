import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.HashMap;

public class EfficientMarkov extends MarkovModel {
	Map<String, ArrayList<String>> myMap;
	
	public EfficientMarkov (int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();		
	}
	
	//setTraining to store text parameter, clears map, initializes map
	
	//given text, build map 
	
	public void setTraining(String text) {
		myText = text;
		myMap.clear(); //dont keep
		
		//split text into character array then put all chars in string array
		char[] letArr = myText.toCharArray(); 
		
		//initializing myMap
		StringBuilder sb = new StringBuilder();
		int pos = 0;
		while (pos<letArr.length+1) {
			if(sb.length() == myOrder) {
				String kStr = sb.toString();
				ArrayList<String> val = myMap.get(kStr);
				if (val == null) {
					val = new ArrayList<String>();
				}
				if (pos + myOrder >= letArr.length) {
					val.add(PSEUDO_EOS);
					myMap.put(kStr, val);
					break;
				}
				else {
					val.add(Character.toString(letArr[pos]));
					myMap.put(kStr, val);
					sb.setLength(0);
					pos = pos-myOrder;
				}
			}
			else {
				sb.append(letArr[pos]);
			}
			pos += 1;
		}
		
	}
	
		
	
	
	public ArrayList<String> getFollows(String key) throws NoSuchElementException{
		ArrayList<String> value = myMap.get(key);
		return value;
	}
}
