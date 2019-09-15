import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class Tic_Tac_Toe {
	
	/*
	 Suzy Liu 260761416
	 */
	//main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		play();
		
	}
	
	//Part A
	public static char[][] creatBoard(int n){
		char[][] board;//create a 2 dimensional array
		board = new char[n][n];//set the dimension of the array as n*n
		//build the board
		for(int i=0; i<n; i++) {
			for(int j=0; j<n;j++)
			board[i][j]=' ';
		}
		return board;
	}
	
	//Part B
	public static void displayBoard(char[][] creatBoard) {	
	//draw the board
	for(int i=1; i<2*creatBoard.length+2; i++) {
		//draw the inside part of the board
		if(i%2==0) {
			for(int j=1; j<2*creatBoard.length+2; j++) {
				if(j%2==1) {
					System.out.print("|");
					}else {
						System.out.print(creatBoard[(i/2)-1][(j/2)-1]);
						}
					}
				}
		//draw the frame of the board
		else if(i%2==1) {
			for(int j=1; j<2*creatBoard.length+2; j++) {
				if(j%2==0) {
					System.out.print("-");
					}else {
					System.out.print("+");
					}
				}
			}
		  System.out.println();
		}
	}
	
	//Part C
	public static void writeOnBoard(char[][] displayBoard, char c, int x, int y) {
		int n = displayBoard.length;
		//check if the value entered is valid
		try{
			//when it's invalid throw an exception
			if(x<0||y<0||x>n||y>n||displayBoard[x][y]!=' ') {
				throw new IllegalArgumentException("The inputs received are invalid!");
		}else {
			//when it's valid, put char c in the place on the board
			displayBoard[x][y] = c;
			}
		}
		//catch the exception
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	//Part D
	public static void getUserMove(char[][] displayBoard) {
		Scanner scanner = new Scanner(System.in);
		int x;//number of row
		int y;//number of column
		//let user enter the number of row
		int a = scanner.nextInt();
		//let user enter the number of column
		int b = scanner.nextInt();
		int n = displayBoard.length;//length of the array
		//while the input numbers are invalid, ask the user to input again
		while(a<0||b<0||a>n-1||b>n-1||displayBoard[a][b]!=' ') {
			System.out.println("The numbers inputed are not valid, please try again.");
			a = scanner.nextInt();
			b = scanner.nextInt();
		}
		x = a;//let x equals to the number entered for row
		y = b;//let y equals to the number entered for column
		writeOnBoard(displayBoard, 'x', x, y);//print the new board
	}

	//Part E
	//count number of characters in a row/column/diagonal
	public static int countCharacters(char[] characters, char c) {
		int n = 0;//before counting, the number of counts is 0
		for(int i=0; i<characters.length; i++) {
			if(characters[i]==c) {
				n++;//while there's a character c in a row/column/diagonal, counts plus 1
			}
		}
		return n;//after running the loop, return the number of counts
	}
	
	//look for a cell
	public static int lookForCell(char[] characters) {
		int n = 0;//before counting, the number of the space is 0
		for(int i=0; i<characters.length; i++) {
			if(characters[i]==' ') {
				n = i;//let n equals to the number of position of empty space
				return n;
			}
		}
		System.out.println(n);
		return n;
	}
	
	//check whether the AI is winning on a row
	public static boolean AIWinOnRow(char[][] displayBoard, char c) {
		boolean b = false;
		int n = displayBoard.length;//n is the length of the array
		char[] row = new char[displayBoard.length];//build a 1 dimensional array, and it's length equal to n
		for(int i=0; i<displayBoard.length; i++) {
			row = new char[displayBoard.length];
			for(int j=0; j<displayBoard.length; j++) {
				row[j] = displayBoard[i][j];
				int countO = countCharacters(row, 'o');//number of 'o' in a row
				int countX = countCharacters(row, 'x');//number of 'x' in a row
				if(countO==n-1&&countX==0) {
					b = true;
				}
			}
			
			if(b==true) {
			int numCell = lookForCell(row);//find an empty cell in the row
			System.out.println(numCell);
			writeOnBoard(displayBoard, c, i, numCell);//put the character in the empty cell
			return b;
			}
			
		}
		return b;//return the value of b
	}
	
	//check whether the AI is winning on  column
	public static boolean AIWinOnColumn(char[][] displayBoard, char c) {
		boolean b = false;
		int n = displayBoard.length;//n is the length of the array
		char[] column = new char[displayBoard.length];//build a 1 dimensional array, and it's length equal to n
		for(int i=0; i<displayBoard.length; i++) {
			//column = new char[displayBoard.length];
			for(int j=0; j<displayBoard.length; j++) {
				column[j] = displayBoard[j][i];
				int countO = countCharacters(column, 'o');//number of 'o' in a column
				int countX = countCharacters(column, 'x');//number of 'x' in a column
				if(countO==n-1&&countX==0) {
					b = true;
				}
			}
			if(b==true) {
			int numCell = lookForCell(column);//find an empty cell in a column
			System.out.println(numCell);
			writeOnBoard(displayBoard, c, numCell, i);//put the character in the empty cell
			return b;
			}
			column = new char[displayBoard.length];
		}
		return b;//return the value of b 
	}

	//check whether the AI is winning on diagonal
	public static boolean AIWinOnDiagonal(char[][] displayBoard, char c) {
		boolean b = false;
		int n = displayBoard.length;//n is the length of the array
		char[] diagonal1 = new char[displayBoard.length];//build a 1 dimensional array, and it's length equal to n
		//when the diagonal is from top left to bottom right
		for(int i=0; i<displayBoard.length; i++) {
				diagonal1[i] = displayBoard[i][i];
				int countO = countCharacters(diagonal1, 'o');//number of 'o' in a diagonal
				int countX = countCharacters(diagonal1, 'x');//number of 'x' in a diagonal
				if(countO==n-1&&countX==0) {
					b = true;
			}
		}
				if(b==true) {
				int numCell = lookForCell(diagonal1);//find an empty cell in a diagonal
				writeOnBoard(displayBoard, c, numCell, numCell);//put the character in the empty cell
				return b;
				}
				diagonal1 = new char[displayBoard.length];
		//when the diagonal is from top right to bottom left
		char[] diagonal2 = new char[displayBoard.length];
		for(int i=0; i<displayBoard.length; i++) {
				diagonal2[i] = displayBoard[i][displayBoard.length-1-i];
				int countO = countCharacters(diagonal2, 'o');//number of 'o' in a diagonal
				int countX = countCharacters(diagonal2, 'x');//number of 'x' in a diagonal
				if(countO==n-1&&countX==0) {
					b = true;
				}
		}
				if(b==true) {
				int numCell = lookForCell(diagonal2);//find an empty cell in a diagonal
				writeOnBoard(displayBoard, c, numCell, displayBoard.length-numCell-1);//put the character in the empty cell
				return b;
				}
				diagonal2 = new char[displayBoard.length];
		return b;//return the value of b
	}
	
	//check whether the User is winning on a row
		public static boolean userWinOnRow(char[][] displayBoard, char c) {
			boolean b = false;
			int n = displayBoard.length;//n is the length of the array
			char[] row = new char[displayBoard.length];//let the length of new 1 dimensional array equals to n
			for(int i=0; i<displayBoard.length; i++) {
				row = new char[displayBoard.length];
				for(int j=0; j<displayBoard.length; j++) {
					row[j] = displayBoard[i][j];
					int countO = countCharacters(row, 'o');//number of 'o' in a diagonal
					int countX = countCharacters(row, 'x');//number of 'x' in a diagonal
					if(countX==n-1&&countO==0) {
						b = true;
					}
				}
				if(b==true) {
				int numCell = lookForCell(row);//find an empty cell in a diagonal
				System.out.println(numCell);
				writeOnBoard(displayBoard, c, i, numCell);//put the character in the empty cell
				return b;
				}
			}
			return b;//return the value of b
		}
		
		//check whether the User is winning on  column
		public static boolean userWinOnColumn(char[][] displayBoard, char c) {
			boolean b = false;
			int n = displayBoard.length;//n is the length of the array
			char[] column = new char[displayBoard.length];//let the length of new 1 dimensional array equals to n
			for(int i=0; i<displayBoard.length; i++) {
				column = new char[displayBoard.length];
				for(int j=0; j<displayBoard.length; j++) {
					column[j] = displayBoard[j][i];
					int countO = countCharacters(column, 'o');//number of 'o' in a diagonal
					int countX = countCharacters(column, 'x');//number of 'x' in a diagonal
					if(countX==n-1&&countO==0) {
						b = true;
					}
				}
				if(b==true) {
				int numCell = lookForCell(column);//find an empty cell in a diagonal
				System.out.println(numCell);
				writeOnBoard(displayBoard, c, numCell, i);//put the character in the empty cell
				return b;
				}
			}
			return b;//return the value of b
		}

		//check whether the User is winning on diagonal
		public static boolean userWinOnDiagonal(char[][] displayBoard, char c) {
			boolean b = false;
			int n = displayBoard.length;//n is the length of the array
			char[] diagonal1 = new char[displayBoard.length];//let the length of new 1 dimensional array equals to n
			//when the diagonal is from top left to bottom right
			for(int i=0; i<displayBoard.length; i++) {
					diagonal1[i] = displayBoard[i][i];
					int countO = countCharacters(diagonal1, 'o');//number of 'o' in a diagonal
					int countX = countCharacters(diagonal1, 'x');//number of 'x' in a diagonal
					if(countX==n-1&&countO==0) {
						b = true;
					}
			}
					if(b==true) {
					int numCell = lookForCell(diagonal1);//find an empty cell in a diagonal
					writeOnBoard(displayBoard, c, numCell, numCell);//put the character in the empty cell
					return b;
					}
			
			//when the diagonal is from top right to bottom left
			char[] diagonal2 = new char[displayBoard.length];
			for(int i=0; i<displayBoard.length; i++) {
					diagonal2[i] = displayBoard[i][displayBoard.length-1-i];
					int countO = countCharacters(diagonal2, 'o');//number of 'o' in a diagonal
					int countX = countCharacters(diagonal2, 'x');//number of 'x' in a diagonal
					if(countX==n-1&&countO==0) {
						b = true;
					}
			}
					if(b==true) {
					int numCell = lookForCell(diagonal2);//find an empty cell in a diagonal
					writeOnBoard(displayBoard, c, numCell, displayBoard.length-1-numCell);//put the character in the empty cell
					return b;
					}
			return b;	
		}
	
	public static boolean checkForObviousMove(char[][] displayBoard) {
		boolean b = false;
		//when the user is going to win for his/her next step
		if(AIWinOnDiagonal(displayBoard, 'o')==true){
			b = true;
		}else if(AIWinOnColumn(displayBoard, 'o')==true) {
			b = true;
		}else if(AIWinOnRow(displayBoard, 'o')==true) {
			b = true;
		}//when AI is going to win for its next step
		else if(userWinOnDiagonal(displayBoard,'o')==true){
			b = true;
		}else if(userWinOnColumn(displayBoard,'o')==true) {
			b = true;
		}else if(userWinOnRow(displayBoard,'o')==true) {
			b = true;
		}//when none of two players win
		else {
			b = false;
		}
		return b;//return the value of b
	}
		
	//Part F
	public static void getAIMove(char[][] displayBoard) {
		boolean check = checkForObviousMove(displayBoard);
		//when check is false, input random numbers
		if(check==false) {
			Random row = new Random();
			Random column = new Random();
			int r = row.nextInt(displayBoard.length);
			int c = column.nextInt(displayBoard.length);
			//check if the random numbers entered are valid
			while(displayBoard[r][c]!=' ') {	
				//when the numbers are not valid, input two new numbers
				r = row.nextInt(displayBoard.length);
				c = column.nextInt(displayBoard.length);
			}
					//write the character on a valid cell on board
					writeOnBoard(displayBoard, 'o', r, c);
			}
		}
	
	//Part G	
	public static char checkForWinner(char[][] displayBoard) {
		int countO = 0;
		int countX = 0;
		int num = displayBoard.length;
		//check if AI or the user wins the game on a row
		for(int i=0; i<num; i++) {
			for(int j=0; j<num; j++) {
				if(displayBoard[i][j]=='o') {
					countO++;
				}else if(displayBoard[i][j]=='x') {
					countX++;
				}
			}
			if(countO==num) {
				return 'o';
			}else if(countX==num) {
				return 'x';
			}
			countO = 0;
			countX = 0;
		}
		
		//check if AI or the user wins the game on a column
		countO = 0;
		countX = 0;
		for(int i=0; i<num; i++) {
			for(int j=0; j<num; j++) {
				if(displayBoard[j][i]=='o') {
					countO++;
				}else if(displayBoard[j][i]=='x') {
					countX++;
				}
			}
			if(countO==num) {
				return 'o';
			}else if(countX==num) {
				return 'x';
			}
			countO = 0;
			countX = 0;
		}
		
		//check if AI or the user wins the game on a diagonal from top left to bottom right
		countO = 0;
		countX = 0;
		for(int i=0; i<num; i++) {
			if(displayBoard[i][i]=='o') {
				countO++;
			}else if(displayBoard[i][i]=='x') {
				countX++;
				}
			if(countO==num) {
				return 'o';
			}else if(countX==num) {
				return 'x';
			}
		}
		
		//check if AI or the user wins the game on a diagonal from top right to bottom left
		countO = 0;
		countX = 0;
		for(int i=0; i<num; i++) {
			if(displayBoard[i][num-1-i]=='o') {
				countO++;
			}else if(displayBoard[i][num-i-1]=='x') {
				countX++;
			}
			if(countX==num) {
				return'x';
			}else if(countO==num) {
				return 'o';
			}
		}
		//when none of players wins
		return ' ';
	}
	
	//Part H
	public static void play() {
		Scanner scanner = new Scanner(System.in);//a scanner to take in the player's name
		System.out.println("Please enter your name: ");
		String name = scanner.nextLine();
		System.out.println("Welcome "+name+"! Are you ready to play?");
		System.out.println("Please choose the dimension of the board: ");
		Scanner dimension = new Scanner(System.in);//a scanner to take in the dimension of the board
		int d = 0;//d is the dimension of the board
		//when the value entered is not int
		if(!dimension.hasNextInt()) {
			System.out.println("Please enter an interger!");
		}else {
			d = dimension.nextInt();
		}
		char[][] board = creatBoard(d);//create a new array with length of d
		Random coin = new Random();//a Random for tossing a coin
		int c = coin.nextInt(2);//the number of the coin tossed is 0 or 1
		System.out.println("The result of the coin toss is: "+c);
		String player;
		char winner = checkForWinner(board);//a variable for checkForWinner
		String AI = "The AI made its move:";
		String user = "Please enter your move:";
		//when coin tossed is 0, user moves first
		if(c==0) {
			player = "You";
			System.out.println(player+" have the first move");
			//play the game
			for(int i=0; i<(d*d)/2; i++) {
				System.out.println(user);
				getUserMove(board);//user moves first
				displayBoard(board);//display the board
				winner = checkForWinner(board);//check if either of players wins
				if(winner=='x'||winner=='o') {
					break;
				}
				System.out.println(AI);
				getAIMove(board);//AI moves
				displayBoard(board);//display the board
				winner = checkForWinner(board);//check if either of players wins
				if(winner=='o'||winner=='x') {
					break;
				}
			}
		}//when coin tossed is 1, AI moves first
		else {
			player = "The AI";
			System.out.println(player+" has the first move");
			//play the game
			for(int i=0; i<(d*d)/2; i++) {
				System.out.println(AI);
				getAIMove(board);//AI moves first
				displayBoard(board);//display the board
				winner = checkForWinner(board);//check if either of players wins
				if(winner=='o'||winner=='x') {
					break;
				}
				System.out.println(user);
				getUserMove(board);//user moves
				displayBoard(board);//display the board
				winner = checkForWinner(board);//check if either of players wins
				if(winner=='x'||winner=='o') {
					break;
				}
		}
		
	}
		System.out.println("Game Over!");
		//when user wins
		if(winner=='x') {
			System.out.println("You win");
		}//when AI wins
		else if(winner=='o') {
			System.out.println("You lost");
		}//when none of players win
		else {
			System.out.println("Tie");
		}
	}
}		
	





	
	
	
	
	
	

