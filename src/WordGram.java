import java.util.ArrayList;
import java.util.Arrays;

public class WordGram implements Comparable<WordGram>{
	
	private String[] myWords;
	private int myHash;
	
	public WordGram(String[] source, int start, int size) {
		// complete this constructor
		//store size strings from the array source starting from index

		ArrayList<String> myWordsList = new ArrayList<String>();
		for (int i=start; i<start+size; i++) {
				myWordsList.add(source[i]);
		}
		myWords = myWordsList.toArray(new String[size]);
		myHash = 17;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		for (int i=0; i<myWords.length;i++) {
			hash = 31*hash + (myWords[i].hashCode())/(i+1); //dividing by i+1 takes position into account
		}
		myHash = hash;
		return myHash;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<myWords.length;i++) {
			sb.append(myWords[i]);
			if(myWords[i] == myWords[myWords.length-1]) {
				break;
			}
			else {
				sb.append(" ");
			}
		}
		String myWordsStr = sb.toString();
		return myWordsStr;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof WordGram)) {
			return false;
		}
		
		WordGram wg = (WordGram) other;
		if (myWords.length != wg.myWords.length) {
			return false;
		}
		
		for (int i=0; i<myWords.length; i++) {
			if (! myWords[i].equals(wg.myWords[i])) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int compareTo(WordGram wg) {
		if (equals(wg)) {
			return 0;
		}
		if (myWords.length == wg.myWords.length) {
			for (int i=0; i<myWords.length; i++) {
				int cmp = myWords[i].compareTo(wg.myWords[i]);
				if (cmp != 0) {
					return cmp;
				}
			}
		}
		if (!(myWords.length == wg.myWords.length)) {
			if (wg.myWords.length>myWords.length) {
				return -1;
			}
			else {
				return 1;
			}
		}
		return -1;
		
	}
	
	public int length() {
		return myWords.length;
	}
	
	public WordGram shiftAdd(String last) {
		ArrayList<String> wgShiftedList = new ArrayList<String>(Arrays.asList(myWords));
		
		wgShiftedList.remove(0);
		wgShiftedList.add(last);
		
		String[] wgShiftedArr = wgShiftedList.toArray(new String[myWords.length]);
		
		return new WordGram(wgShiftedArr,0,myWords.length);
	}
}
