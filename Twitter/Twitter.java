package Twitter;
import java.util.ArrayList;
import java.io.*;
import java.util.HashSet;
import java.util.HashMap;

public class Twitter {
	/*
	Suzy Liu 
	*/	
	//main method
	public static void main(String[] args) throws IOException{
		Twitter example = new Twitter();
		example.loadDB("tweets.txt");
	}
	//a private attribute
	private ArrayList<Tweet> tweets;
	//a constructor 
	public Twitter() {
		tweets = new ArrayList<Tweet>();
	}
	//method loadDB
	public void loadDB(String t) throws IOException  {
		//create a new hashset tweet
		HashSet<String> tweet = new HashSet<String>();   
		//read a file
		FileReader fr = new FileReader(t);
		BufferedReader br = new BufferedReader(fr);
		String currentLine = br.readLine();
		Tweet newTweet = null;
		while(currentLine!=null) {
			//fill the hashset with things in the file
			tweet.add(currentLine);
			currentLine = br.readLine();
			}
		br.close();
		fr.close();
		//split every string in hashset tweet
		try {
		for(String s : tweet) {
			String[] aTweet = s.split("\t");
			String user = aTweet[0];
			String date = aTweet[1];
			String time = aTweet[2];
			String message = aTweet[3];
			newTweet = new Tweet(user,date,time,message);
			//check whether the message is true or not
			if(newTweet.checkMessage()==true) {
				//when the message is true, add it to the arraylist tweets
				this.tweets.add(newTweet);
			}
		}
		}
		catch(NullPointerException e) {
			System.out.print("Error checking the stopWords database: ");
			System.out.println("The file of stopWords has not been loaded yet");
		}
		sortTwitter();
	}
	
	public void sortTwitter() {
		//set int sort as 0
		int sort = 0;
		//create a Tweet temp, which stores temporary tweet
		Tweet temp;
		//go through the unsorted arraylist
		while(sort<tweets.size()-1) {
			int pre = sort;
			for(int i=sort;i<tweets.size();i++) {
				//use isBefore in Tweet class
				//when previous tweet is not posted earlier than new tweet
					if(getTweet(pre).isBefore(getTweet(i))==false) {
						pre = i;
				}
			}
			//swap the position of tweets
			temp = tweets.get(pre);
			tweets.set(pre, tweets.get(sort));
			tweets.set(sort, temp);
			//update
			sort++;
		}
	}
	//method getSizeTwitter
	public int getSizeTwitter() {
		return this.tweets.size();
	}
	//method getTweet
	public Tweet getTweet(int i) {
		return this.tweets.get(i);
	}
	//method prinDB
	public String printDB() {
		String DB = "";
		//call method toString in class TWeet
		for(int i=0;i<tweets.size();i++) {
			Tweet one = tweets.get(i);
			DB = DB+one.toString()+"\n";
		}
		return DB;
	}
	//method rangeTweet
	public ArrayList<Tweet> rangeTweets(Tweet tweet1,Tweet tweet2) {
		//create a new arraylist rangeT
		ArrayList<Tweet> rangeT = new ArrayList<Tweet>();
		//get index of two tweets
		int t1 = this.tweets.indexOf(tweet1);
		int t2 = this.tweets.indexOf(tweet2);
		//when tweet1 is posted before tweet2
		if(t1<t2) {
			for(int i=t1;i<t2+1;i++) {
				rangeT.add(tweets.get(i));
			}
		}
		//when tweet2 is posted before tweet1
		else if(t2<t1) {
			for(int i=t2;i<t1+1;i++) {
				rangeT.add(tweets.get(i));
			}
		}
		//return arraylist rangeT
		return rangeT;
		
	}
	//method saveDB
	public void saveDB(String t) throws IOException {
		//write a file
		FileWriter fw = new FileWriter(t);
		BufferedWriter bw = new BufferedWriter(fw);
		String newT = printDB();
		bw.write(newT);
		bw.close();
		fw.close();
	}
	//a private method filter
		private static String filter(String s) {
			//converts words to lowercase
			String n = s.toLowerCase();
			char atN = n.charAt(n.length()-1);
			//check the last character in a word
			//check if the character is ".",",",";"or":"
			if(atN=='.') {
				n = n.substring(0, n.length()-1);
			}else if(atN==',') {
				n = n.substring(0, n.length()-1);
			}else if(atN==';') {
				n = n.substring(0, n.length()-1);
			}else if(atN==':') {
				n = n.substring(0, n.length()-1);
			}
			//return the filtered word
			return n;
		}
		//a private method stopWords
		private static HashSet<String> stopWords() throws IOException{
			//initialized stopWords
			HashSet<String> stopWords = new HashSet<String>();
			//read a file
			FileReader fr = new FileReader("stopWords.txt");
			BufferedReader br = new BufferedReader(fr);
			String currentLine = br.readLine();
			while(currentLine!=null) {
				//fill the hashset stopwords
				stopWords.add(currentLine);
				currentLine = br.readLine();
				}
			br.close();
			fr.close();
			return stopWords;
			}
	//method trendingTopic
	public String trendingTopic() throws IOException {
		String trend = "";
		//create a hashmap trendC
		HashMap<String, Integer> trendC = new HashMap<String, Integer>();
		//create a hashset trendSet
		HashSet<String> trendSet = new HashSet<String>();
		//get every tweet in tweets
		for(int i=0;i<tweets.size();i++) {
			trendSet = new HashSet<String>();
			//get message from each tweet
			String tMessage = tweets.get(i).getMessage();
			//split the message into words
			String[] message = tMessage.split(" ");
			//check if the word is a stopword
			for(int j=0;j<message.length;j++) {
				String word = message[j];
				String fword = filter(word);
				boolean check = stopWords().contains(fword);                      
				//if the word is a stopword, continue
				if(check==true) {
					continue;
				}
				//if the word is not a stopword
				else {
					//add the word to the trendset
					//String fword = Tweet.filter(word);
					trendSet.add(fword);
				}
			}
			//go through trendSet
			for(String word : trendSet) {
				//if hashmap trendC contains word in trendSet
				if(trendC.containsKey(word)==true) {
					int count = trendC.get(word);
					trendC.put(word, count+1);
				}
				//if hashmap trendC does not contain word in trendSet
				else if(trendC.containsKey(word)==false) {
					trendC.put(word, 1);
				}
			}
		}
		int maxValue = 0;
		//find a word in trendC which contains the large value
        for (String key : trendC.keySet()) {  
            if (trendC.get(key) > maxValue) {
            	maxValue = trendC.get(key);
                trend = key; 
            }
        }
        return trend;
	}
}

