public class Mexico {
	
	
	/*
	Suzy Liu 
	*/
	
	//main method
	public static void main(String[] args) {
		 playMexico(10.0,5.0);
	}
	
	//A method to simulate a dice roll
	static int diceRoll(){
		int x;
		x=((int)(Math.random()*6+1));//define the variable x using Math.random"
		return x;//return the value of x to method diceRoll
	}
	
	//A method to compute the score of a Player
	static int getScore(int a, int b)//a and b are the number of dice rolled
	{	int score;
		if(a>b)//compare the two input variables
		{score=10*a+b;}//case 1: when variable a is larger than variable b
		else
		{score=10*b+a;}//case 2: when variable b is larger than variable a
		return score;//return the value of score to method getScore		
	}

	//A method to simulate one round of Mexico
	static int playOneRound(String p)//p is the name of the player
	{	int x=diceRoll();//use the value from method diceRoll
		int y=diceRoll();//use the value from method diceRoll
		int m=getScore(x,y);//use the two values from method diceRoll and then operate them in method getScore 
		System.out.println(p+" rolled: "+x+" "+y+"\n"+p+" 's score is: "+m);
	    return m;//return the score obtained by the player to method playOneRound
	}
	
	//A method to determine the winner of one round
	static String getWinner(int g,int d)//g is Giulia's score, and d is David's score
	{	String w;
		if(g==d)
		{w="tie";}//case 1: when there is a tie
		else if(g==21)
		{w="Giulia";}//case 2: when Giulia rolled 21
		else if(d==21)
		{w="David";}//case 3: when David rolled 21
		else if(g%11==0&&d%11!=0)
		{w="Giulia";}//case 4: when Giulia rolled a number which is a double, and David didn't
		else if(d%11==0&&g%11!=0)
		{w="David";}//case 5: when David rolled a number which is a double, and Giulia didn't
		else if(g>d)
		{w="Giulia";}//case 6: when Giulia rolled a number that is larger than David, and neither of them rolled doubles
		else
		{w="David";}//case 7: when David rolled a number that is larger than Giulia, and neither of them rolled doubles
		return w;
	}
	
	//A method to check if the buy in and the base bet are set correctly
	static boolean canPlay(double m,double n)//a is buy in, and b is value needed to pay
	{	boolean a=true;//define boolean type
		if(m>=n&&m%n==0&&m>0&&n>0)//write the condition when the game is allowed to be played
		{a=true;}//case 1: when buy in and bet are eligible
		else
		{a=false;}//case 2: when buy in and bet are not eligible
		return a;	
	}
	
	//A method to simulate a game of Mexico
	static void playMexico(double a, double b)//a is buy in, and b is value needed to pay
	{
		String p1 = "Giulia";//define p1 as Giulia
		String p2 = "David";//define p2 as David
		double p = a;//p is the money that Giulia has 
		double q = a;//q is the money that David has
		int numRound = 1;//times of rounds played
		String finalWinner;//it represents the final winner of the game
		int count1 = 0;//times of win of Giulia
		int count2 = 0;//times of win of David
		if(canPlay(a,b)==true)//game can be played
		{	while(p>0&&q>0)//each player has money in their hand
			{
				System.out.println("Round"+numRound+"\n\n");//round number
				int player1 = playOneRound(p1);//dice Giulia rolled, and score she got
				int player2 = playOneRound(p2);//dice David rolled, and score he got
				String winner = getWinner(player1,player2);//get the winner of the game 
				if(winner.equals("Giulia")) // Giulia wins the round 
				{	
					q = q-b;//money that David left
					System.out.println(winner+" wins this round\n\n");
					count1++;//times of Giulia win increase by 1
				}
				else if (winner.equals("David")) // David wins the round
				{   
					p = p-b;//money that Giulia left
					System.out.println(winner+" wins this round\n\n");
					count2++;//times of David win increase by 1
				}
				else // Tied
				{System.out.println("It's a tie.\tRoll again!");}
				numRound++;//times of round played increase by 1
			}
			if(count1>count2)//when Giulia wins more times than David, Giulia wins the game
			{finalWinner="Giulia";}
			else
			{finalWinner="David";}//when David wins more times than Giulia, David wins the game
			System.out.println(finalWinner+" won the game!");//print out the final winner of the game
		}
		else//when values of buy in and base bet are not eligible
		{System.out.println("The fund is insufficient in order to play.");}
	}
}
