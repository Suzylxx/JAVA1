public class JudgeScore {
	public static void main(String[] args) {
	
		/*
		Suzy Liu 
		*/	
	
		//Declaring the variables for storing the judges scores.
		int judge1, judge2, judge3, judge4;
		judge1 = Integer.valueOf(args[0]);
		judge2 = Integer.valueOf(args[1]);
		judge3 = Integer.valueOf(args[2]);
		judge4 = Integer.valueOf(args[3]);
		
		//Your code Starts here
		int max, min;
		max=judge1;
		min=judge1;
		//declare two variables min and max
		if(judge4>=judge3&&judge4>=judge2&&judge4>=judge1)
		{max=judge4;}
		else if(judge3>=judge1&&judge3>=judge2&&judge3>=judge4)
		{max=judge3;}
		else if(judge2>=judge3&&judge2>=judge4&&judge2>=judge1)
		{max=judge2;}
		else
		{max=judge1;}
		//list all situations of max
		if(judge4<=judge1&&judge4<=judge2&&judge4<=judge3)
		{min=judge4;}
		else if(judge3<=judge1&&judge3<=judge2&&judge3<=judge4)
		{min=judge3;}
		else if(judge2<=judge1&&judge2<=judge3&&judge2<=judge4)
		{min=judge2;}
		else
		{min=judge1;}
		//list all situations of min
		double sum;
		double average;
		sum=judge1+judge2+judge3+judge4;
		average=(sum-max-min)/2;
		//declare the variable of sum and average
		{System.out.println(average);}
		//get the result
		//Your code Ends here
	}
}
