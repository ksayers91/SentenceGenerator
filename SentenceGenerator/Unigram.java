import java.util.HashMap;
import java.util.Map;

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
    public int compareTo(Unigram p) {
       // return (this.count).compareTo(p.count);
        		//compareTo(p.count);
    	return word.compareTo(p.word);
    }

    public boolean equals(Object p) {
    	Unigram temp;
    	temp = (Unigram) p;
        return word.equals(temp.word);
    }
    
    public String toString () {
    	return "Unigram: " + word + "\tCount: " + count;
    }
    
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
    
    public void printAssociatedBigrams() {
    	for (Object key : bigramCounts.keySet()) {
    		key = (String) key;
    	    System.out.println(key + " " + bigramCounts.get(key));
    	}
    }
}
