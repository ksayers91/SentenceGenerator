import java.util.HashMap;
import java.util.Map;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Unigram Class. This is a data structure that is assigned to each unique unigram in the input file
//Each Unigram object is composed of the word, count of appearances of the word, and a Map object that refrences every
//Bigram pair with this unigram as the first word of the bigram.
public class Unigram implements Comparable<Unigram>{
	
	String word;
	int count;
	Map bigramCounts;
	
	public Unigram(String w) {
		word = w;
		count = 0;
		bigramCounts = new HashMap();
		  //accessors
	}

	public void addAssociatedBigrams (String bg, int ct) {
		String[] arr = bg.split(" ");
		bigramCounts.put(arr[1], ct);
		count += ct;
	}
//Classes allow for sorting
    public int compareTo(Unigram p) {
       // return (this.count).compareTo(p.count);
        		//compareTo(p.count);
    	return word.compareTo(p.word);
    }
//Classes allow for sorting
    public boolean equals(Object p) {
    	Unigram temp;
    	temp = (Unigram) p;
        return word.equals(temp.word);
    }
    //Used for debugging
    public String toString () {
    	return "Unigram: " + word + "\tCount: " + count;
    }
    
//This is responsible for picking and choosing the next word occurance in a bulding sentence.
//It is a weighted lottery that favors the more common appearances.
    public String generateLotteryWord() {
    	int sum = 0;
    	int lot = (int)(Math.random()*count) + 1;

    	//System.out.println(lot + "/" + count);
    	
	    for (Object key : bigramCounts.keySet()) {
	    	key = (String) key;
	    	sum += (int) bigramCounts.get(key);
	    	if (lot <= sum)
	    	 return (String) key;
	    }
    	return "";
    }

//Used in debugging
    public void printAssociatedBigrams() {
    	for (Object key : bigramCounts.keySet()) {
    		key = (String) key;
    	    System.out.println(key + " " + bigramCounts.get(key));
    	}
    }
}
