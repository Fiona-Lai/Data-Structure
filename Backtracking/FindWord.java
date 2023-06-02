// CS 0445 Spring 2023
// Backtracking example: Find a word within a two-d grid of characters
// Idea: A word can be formed by sequential letters in any of the 8 directions
//       (right, down, left, up).  Each letter in the grid can only be used
//       one time within a word.

// Note: I have added a StringBuilder to this code to help you to trace
//       the backtracking.  It is not necessary for the algorithm but seeing
//       how the characters are added and removed from the potential
//       solution should help you understand the backtracking process better. 

//		 To see extra tracing of the algorithm, remove the comments on the
//       println() statements in the recursive method.
//
// To run this program enter file "findWord1.txt" or you own properly formatted
// text file when prompted.  Note that you will need to download "findWord1.txt"
// in order to use it.  Then enter words to find and see the result.

import java.io.*;
import java.util.*;

public class FindWord
{	
	private StringBuilder currWord;
	
	public static void main(String [] args)
	{
		new FindWord();
	}
	
	// Constructor to set things up and make the initial search call.
	public FindWord()
	{
        Scanner inScan = new Scanner(System.in);
		Scanner fReader;
		File fName;
        String fString = "", word = "";
       
       	// Make sure the file name is valid
        while (true)
        {
           try
           {
               System.out.println("Please enter grid filename:");
               fString = inScan.nextLine();
               fName = new File(fString);
               fReader = new Scanner(fName);
              
               break;
           }
           catch (IOException e)
           {
               System.out.println("Problem " + e);
           }
        }

		// Parse input file to create 2-d grid of characters
		String [] dims = (fReader.nextLine()).split(" ");
		int rows = Integer.parseInt(dims[0]);
		int cols = Integer.parseInt(dims[1]);
		
		char [][] theBoard = new char[rows][cols];

		for (int i = 0; i < rows; i++)
		{
			String rowString = fReader.nextLine();
			for (int j = 0; j < rowString.length(); j++)
			{
				theBoard[i][j] = Character.toLowerCase(rowString.charAt(j));
			}
		}

		// Show user the grid
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				System.out.print(theBoard[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Please enter the word to search for:");
        word = (inScan.nextLine()).toLowerCase();
		while (!(word.equals("")))
		{
			int x = 0, y = 0;
		
			// Search for the word.  Note the nested for loops here.  This allows us to
			// start the search at any of the locations in the board.  The search itself
			// is recursive (see findWord method for details).  Note also the boolean
			// which allows us to exit the loop before all of the positions have been
			// tried -- as soon as one solution has been found we can stop looking.
			boolean found = false;
			for (int r = 0; (r < rows && !found); r++)
			{
				for (int c = 0; (c < cols && !found); c++)
				{
				// Start search for each position at index 0 of the word
					currWord = new StringBuilder("");
					found = findWord(r, c, word, 0, theBoard);
					if (found)
					{
						x = r;  // store starting indices of solution
						y = c;
					}
				}
			}

			if (found)
			{
				System.out.println("The word: " + word);
				System.out.println("was found starting at location (" + x + "," + y + ")");
				for (int i = 0; i < rows; i++)
				{
					for (int j = 0; j < cols; j++)
					{
						System.out.print(theBoard[i][j] + " ");
						theBoard[i][j] = Character.toLowerCase(theBoard[i][j]);
					}
					System.out.println();
				}
			}
			else
			{
				System.out.println("The word: " + word);
				System.out.println("was not found");
			}
			
			System.out.println("Please enter the word to search for:");
        	word = (inScan.nextLine()).toLowerCase();
		}
			
	}

	// Recursive method to search for the word.  Return true if found and false
	// otherwise.
	public boolean findWord(int r, int c, String word, int loc, char [][] bo)
	{
		//System.out.println("findWord: " + r + ":" + c + " " + word + ": " + loc); // trace code
		
		// Check boundary conditions
		if (r >= bo.length || r < 0 || c >= bo[0].length || c < 0)
			return false;
		else if (bo[r][c] != word.charAt(loc))  // char does not match
			return false;
		else  	// current character matches
		{
			bo[r][c] = Character.toUpperCase(bo[r][c]);  // Change it to
				// upper case.  This serves two purposes:
				// 1) It will no longer match a lower case char, so it will
				//    prevent the same letter from being used twice
				// 2) It will show the word on the board when displayed
			currWord.append(bo[r][c]);
			System.out.println("Curr: " + currWord);
			boolean answer;
			if (loc == word.length()-1)		// base case - word found and we
				answer = true;				// are done!
				
			else	// Still have more letters to match, so recurse.
			{		// Try all eight directions if necessary.
				answer = findWord(r, c+1, word, loc+1, bo);  // Right
				if (!answer)
					answer = findWord(r+1, c, word, loc+1, bo);  // Down
				if (!answer)
					answer = findWord(r, c-1, word, loc+1, bo);  // Left
				if (!answer)
					answer = findWord(r-1, c, word, loc+1, bo);  // Up
				if (!answer)
					answer = findWord(r - 1, c - 1, word, loc + 1, bo);  // Left up
				if (!answer)
					answer = findWord(r - 1, c + 1, word, loc + 1, bo);  // Right up
				if (!answer)
					answer = findWord(r + 1, c - 1, word, loc + 1, bo);  // Left down
				if (!answer)
					answer = findWord(r + 1, c + 1, word, loc + 1, bo);  // Right down
				// If answer was not found, backtrack.  Note that in order to
				// backtrack for this algorithm, we need to move back in the
				// board (r and c) and in the word index (loc) -- these are both 
				// handled via the activation records, since after the current AR 
				// is popped, we revert to the previous values of these variables.
				// However, we also need to explicitly change the character back
				// to lower case before backtracking.
				if (!answer)
				{
					bo[r][c] = Character.toLowerCase(bo[r][c]);
					currWord.deleteCharAt(currWord.length()-1);
					System.out.println("Curr: " + currWord);
				}
			}
			//System.out.println("findWord: " + r + ":" + c + " " + word + ": " + loc + " FINISHED"); // trace code
			return answer;
		}
	}			
}