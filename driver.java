import java.util.*;
import java.io.*;
import java.text.*;

public class driver {

	public static void main(String[] args) throws Exception {
		//List<Bigram> bigramCounts = new ArrayList<Bigram>();
		//Map unigramCounts = new HashMap();
		//Map bigramCounts = new HashMap();
		//List<Unigram> nGrams = new ArrayList<Unigram>();
		Map nGrams = new HashMap();
		
		File corpus = new File("HadoopOutput.txt");
		System.out.println(new File("HadoopOutput.txt").getAbsolutePath());
		System.out.println(corpus);
		Scanner ScanCorpus = new Scanner(corpus);
		while(ScanCorpus.hasNext()) {
			String strtemp = ScanCorpus.nextLine();
			String[] arr = strtemp.split("\\s+");
			String tmpBigram = arr[0] + " " + arr[1];
			Unigram tmpUG = new Unigram(arr[0]);
			//Bigram temp = new Bigram(arr[0], arr[1], Integer.parseInt(arr[2]));
			//bigramCounts.add(temp);
			
			/*
			if (unigramCounts.containsKey(arr[0])) {
				Integer val = (int) unigramCounts.get(arr[0]);
				unigramCounts.put(arr[0], val + Integer.parseInt(arr[2]));
			}
			else
				unigramCounts.put(arr[0], Integer.parseInt(arr[2]));
			*/
			
			if (nGrams.containsKey(arr[0])) {
				Unigram val = (Unigram) nGrams.get(arr[0]);
				val.addAssociatedBigrams(tmpBigram, Integer.parseInt(arr[2]));
				nGrams.put(arr[0], val);
			}
			else {
				Unigram val = new Unigram(arr[0]);
				val.addAssociatedBigrams(tmpBigram, Integer.parseInt(arr[2]));
				nGrams.put(arr[0], val);
			}
			//if (bigramCounts.containsKey(tmpBigram)) {
			//	Integer val = (int) bigramCounts.get(tmpBigram);
			//	bigramCounts.put(tmpBigram, val + Integer.parseInt(arr[2]));
			//}
			//else
			//bigramCounts.put(tmpBigram, Integer.parseInt(arr[2]));
			
			//System.out.println(arr[0] + " " + arr[1] + " - Count:" + arr[2]);
		}
		//System.out.println("Bigram: 'over there'\t" + "Unigram Count: " + (int) unigramCounts.get("over") + "\tBigram Count: " + (int) bigramCounts.get("over there"));
		//System.out.println("Unigram Count:" + (int) unigramCounts.get("over"));
		
		Scanner sc= new Scanner(System.in);
		String tmp = "";
		while (true) {
			System.out.print("\nEnter starting word for sentence ('//s' to stop): ");
			tmp = sc.next().toLowerCase();
			if(tmp.equals("//s"))
				System.exit(0);
			String sentence = tmp;
			
			for (int i = 0; i <=7; i++) {
				Unigram temp = (Unigram) nGrams.get(tmp);
				if (temp != null) {
					tmp = temp.generateLotteryWord();
					sentence += " " + tmp;
				}
				else
					tmp = "error";
			}
			System.out.println("Generated Sentence: " + sentence);
			//System.out.println(sentence);
			//String whatever = sc.next();
			//break;
		}
		//Unigram temp = (Unigram) nGrams.get("united");
		//System.out.println(temp);
		//System.out.println(temp.generateLotteryWord());
		//temp.printAssociatedBigrams();
	}
}
