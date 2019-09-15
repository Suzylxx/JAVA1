public class ISBN 
{
	public static void main(String[] args)
	{	
		/*
		Suzy Liu 
		*/
		int n = Integer.parseInt(args[0]);
		
		//Your code Starts here
		int d5,d4,d3,d2,d1,sum;
		//declared variables
		d5=n/1000;
		//express the value of d5
		d4=(n-1000*d5)/100;
		//express the value of d4
		d3=(n-1000*d5-100*d4)/10;
		//express the value of d3
		d2=(n-1000*d5-100*d4-10*d3);
		//express the value of d2
		d1=0;
		//express the value of d1
		sum=5*d5+4*d4+3*d3+2*d2+d1;
		//express the value of sum
		System.out.print("The full ISBN number is " + args[0]);
		if(sum%11==0)
		{System.out.println(0);}
		else if(sum%11==1)
		{System.out.println("X");}
		else
		{System.out.println(11-sum%11);}
		//three different cases with three different outputs
		//Your code Ends here
    }
}
