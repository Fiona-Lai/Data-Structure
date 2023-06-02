// Testing of simple deque implementations.
// This program should run without changes with SimpleDeque<T> class
// See file DequeTest-out.txt for output.
import java.util.*;

public class DequeTest
{
	public static void main(String [] args)
	{
		DequeInterface<Integer> D1 = new SimpleDeque<Integer>(4);
		DequeInterface<Integer> D2 = new SimpleDeque<Integer>(8);
		// Testing addToBack
		for(int i = 0; i < 10; i++)
		{
			Integer newItem = Integer.valueOf(2 * i);
			System.out.println("Trying to add " + newItem + " added to Deques");
			D1.addToBack(newItem);
			D2.addToBack(newItem);
			
		}
		System.out.println();
		System.out.println("D1: " + D1.toString());
		System.out.println("D2: " + D2.toString());
		System.out.println();
		
		Integer X = D1.getFront();
		System.out.println("Front of D1: " + X);
		X = D2.getBack();
		System.out.println("Back of D2: " + X);
		System.out.println();
		
		// Testing removeFront and removeBack
		while (!(D1.isEmpty()))
		{
			Integer F = D1.removeFront();
			System.out.println("Front: " + F);
		}
		Integer noItem = D1.removeFront();
		if (noItem == null)
			System.out.println("Nothing in D1");
		System.out.println();
		
		while (!(D2.isEmpty()))
		{
			Integer B = D2.removeBack();
			System.out.println("Back: " + B);
		}
		noItem = D2.removeBack();
		if (noItem == null)
			System.out.println("Nothing in D2");
		System.out.println();
		
		int count = 1;
		DequeInterface<String> D3 = new SimpleDeque<String>(5);
		String theItem = new String("Item " + count);
		System.out.println("Adding " + theItem);
		D3.addToFront(theItem);
		for (int i = 0; i < 8; i++)
		{
			count++;
			theItem = new String("Item " + count);
			System.out.println("Adding " + theItem);
			D3.addToFront(theItem);
			theItem = D3.removeBack();
			System.out.println("Removing " + theItem);
		}
		System.out.println("D3: " + D3.toString());
		
		D3.addToBack("Another item");
		D3.addToBack("Yet another item");
		System.out.println("D3: " + D3.toString());
		D3.clear();
		System.out.println("D3: " + D3.toString());	
	}
}