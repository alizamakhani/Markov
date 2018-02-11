import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EfficientWordMarkov extends WordMarkovModel{
	Map<WordGram, ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		myMap.clear();
		
		int pos = 0;
		while (pos<myWords.length+1) {
			WordGram key = new WordGram(myWords, pos, myOrder);
			ArrayList<String> val = myMap.get(key);
			if (val == null) {
				val = new ArrayList<String>();
			}
			if (pos + myOrder >= myWords.length) {
				val.add(PSEUDO_EOS);
				myMap.put(key, val);
				break;
			}
			else {
				val.add(myWords[pos+myOrder]);
				myMap.put(key, val);
			}
			pos+=1;
		}
	}
	public ArrayList<String> getFollows(WordGram key){
		return myMap.get(key);
	}
}
