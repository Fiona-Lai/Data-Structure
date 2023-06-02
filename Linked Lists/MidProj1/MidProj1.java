// CS 0445 Spring 2023
// Test Program for Midterm Exam Project 1
// This program should run as is with your LList.java file (modified to add
// the two additional methods).
//
// See the output in file P1Out.txt

import java.util.*;
public class MidProj1
{	
	public static <T> void addItems(LList<T> L, T [] data)
	{
		for (T val: data)
			L.add(val);  // add at end of the list
	}
	
	public static <T> void showItems(LList<T> L)
	{
		for (int i = 1; i <= L.getLength(); i++)
			System.out.print(L.getEntry(i) + " ");
		System.out.println();
	}
		
	public static void main(String [] args)
	{
		String [] data = {"Westley", "Fezzik", "Buttercup", "Inigo"};
		
		LList<String> list1 = new LList<String>();
		LList<String> list2 = new LList<String>();
		addItems(list1, data);
		addItems(list2, data);

		System.out.println("L1 contains:");
		showItems(list1);
		System.out.println();
		System.out.println("L2 contains:");
		showItems(list2);
		System.out.println();
	
		if (list1.equals(list2))
			System.out.println("L1 and L2 are equal");
		else
			System.out.println("L1 and L2 are NOT equal");
		
		System.out.println("\nAdding some data...");
		list1.add(5, new String("Fezzik"));
		list2.add(5, new String("Max"));

		if (list1.equals(list2))
			System.out.println("L1 and L2 are equal");
		else
			System.out.println("L1 and L2 are NOT equal");
			
		System.out.println("\nRemoving some data...");
		list1.remove(list1.getLength());
		
		if (list1.equals(list2))
			System.out.println("L1 and L2 are equal");
		else
			System.out.println("L1 and L2 are NOT equal");		
			
		System.out.println();
		LList<String> list3 = new LList<String>(list1);
		System.out.println("L3 contains:");
		showItems(list3);
		System.out.println();
		
		if (list1.equals(list3))
			System.out.println("L1 and L3 are equal");
		else
			System.out.println("L1 and L3 are NOT equal");

		System.out.println("\nAdding some data...");		
		// Make sure list1 and list3 are separate lists
		list1.add(1, new String("Rugen"));
		list3.add(3, new String("Humperdinck"));
		System.out.println("L1 contains:");
		showItems(list1);
		System.out.println();
		
		System.out.println("L3 contains:");
		showItems(list3);
		System.out.println();			
	}
}