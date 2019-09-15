package Twitter;
import java.util.HashSet;
import java.io.*;

public class Tweet {
	/*
	Suzy Liu 260761416
	*/	
	//four private attributes
	private String userAccount;
	private String date;
	private String time;
	private String message;
	private static HashSet<String> stopWords;
	//a constructor Tweet
	public Tweet(String userAccount, String date, String time, String message) throws IOException {
		//initialize four attributes
		this.userAccount = userAccount;
		this.date = date;
		this.time = time;
		this.message = message;
		
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
	//method check message
	public boolean checkMessage(){
		boolean check = false;
		//split words in a message
		String[] words = message.split(" ");
		//create a new array string
		String[] fWords = new String[words.length];
		int length = words.length;
		//use filter method to change the word
		for(int j=0;j<words.length;j++) {
			fWords[j] = filter(words[j]);
		}
		//when stopwords is not loaded
			if(stopWords==null) {
				throw new NullPointerException();
			}
		//when stopwords is loaded
		if(stopWords!=null) {
			//check each word in the array words
			for(int i=0;i<words.length;i++) {
				for(String s : stopWords) {
					//if a word is a stop word
					if(s.equals(fWords[i])) {
						//length of the message minus one
						length--;
						
					}
				}
			}
			//check the length of message
			if(length<16&&length>0) {
				check = true;
			}
		}
		return check;
	}
	//method getDate	
	public String getDate() {
		return this.date;
	}
	//method getTime
	public String getTime() {
		return this.time;
	}
	//method getMessage
	public String getMessage() {
		return this.message;
	}
	//message getUserAccount
	public String getUserAccount() {
		return this.userAccount;
	}
	//message toString
	public String toString() {
		//to get a tweet message
		String user = this.userAccount;
		String uDate = this.date;
		String uTime = this.time;
		String uMessage = this.message;
		String aTweet = user+" "+uDate+" "+uTime+" "+uMessage;
		return aTweet;
	}
	//method isBefore
	public boolean isBefore(Tweet tweet) {
		boolean before = false;
		//get date and time of the existed tweet and input tweet
		String d = this.date;
		String t = this.time;
		String inD = tweet.date;
		String inT = tweet.time;
		//compare the date and time of these two tweets
		int compareD = d.compareTo(inD);
		int compareT = t.compareTo(inT);
		//when the this tweet is posted on an earlier date than input tweet, before is true
		if(compareD<0) {
			before = true;
		}
		//when the this tweet and input tweet were posted on same date,but this tweet posted earlier in time
		else if(compareD==0&&compareT<0) {
			before = true;
		}
		return before;
	}
	//method loadStopWords
	public static void loadStopWords(String t) throws IOException{
		//initialized stopWords
		stopWords = new HashSet<String>();
		//read a file
		FileReader fr = new FileReader(t);
		BufferedReader br = new BufferedReader(fr);
		String currentLine = br.readLine();
		while(currentLine!=null) {
			//fill the hashset stopwords
			stopWords.add(currentLine);
			currentLine = br.readLine();
			}
		br.close();
		fr.close();
		}
}

