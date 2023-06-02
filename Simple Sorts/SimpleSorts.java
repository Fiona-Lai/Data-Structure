// CS 0445 Spring 2023
// This file contains two of the author's simple sorting algorithms,
// selectionsort and insertionsort. I have added some simple code to
// keep track of the number of comparisons done during a sort for each
// algorithm. This number is stored in the public static variable comps.

/**
   Class for sorting an array of Comparable objects from smallest to 
   largest.
*/
public class SimpleSorts
{
	public static long comps;

   /** Sorts the first n objects in an array into ascending order.
       @param a  an array of Comparable objects
       @param n  an integer > 0 */
       
   // CS 0445 Spring 2023
   // Note that this is the method that you will call from your main program.  It
   // takes an argument of an array (of any Comparable type) and an int (the length
   // of the array).
   public static <T extends Comparable<? super T>> void selectionSort(T[] a, int n)
   {
   	  comps = 0;
      for (int index = 0; index < n - 1; index++)
      {
         int indexOfNextSmallest = getIndexOfSmallest(a, index, n - 1);
         swap(a, index, indexOfNextSmallest);
         // Assertion: a[0] <= a[1] <= . . . <= a[index] <= all other a[i]
      } // end for
   } // end selectionSort

   /** Finds the index of the smallest value in a portion of an array.
       @param a      an array of Comparable objects
       @param first  an integer >= 0 and < a.length that is the index of 
                     the first array entry to consider
       @param last   an integer >= first and < a.length that is the index 
                     of the last array entry to consider
       @return the index of the smallest value among
               a[first], a[first + 1], . . . , a[last] */
   private static <T extends Comparable<? super T>>
           int getIndexOfSmallest(T[] a, int first, int last)
   {
      T min = a[first];
      int indexOfMin = first;
      for (int index = first + 1; index <= last; index++)
      {
      	 comps++;
         if (a[index].compareTo(min) < 0)
         {
            min = a[index];
            indexOfMin = index;
         } // end if
         // Assertion: min is the smallest of a[first] through a[index].
      } // end for

      return indexOfMin;
   } // end getIndexOfSmallest

   /** Swaps the array entries a[i] and a[j].
       @param a  an array of objects
       @param i  an integer >= 0 and < a.length
       @param j  an integer >= 0 and < a.length */
   private static void swap(Object[] a, int i, int j)
   {
      Object temp = a[i];
      a[i] = a[j];
      a[j] = temp; 
   } // end swap
   
   // CS 0445 Spring 2023
   // Note that this is the method that you will call from your main program.  It
   // takes an argument of an array (of any Comparable type) and an int (the length
   // of the array).
   public static <T extends Comparable<? super T>> void insertionSort(T[] a, int n) 
   {   
   		comps = 0;
   		insertionSort(a, 0, n - 1);	
   }
   
   public static <T extends Comparable<? super T>> 
	       void insertionSort(T[] a, int first, int last)
   {
		int unsorted, index;		
		for (unsorted = first + 1; unsorted <= last; unsorted++)
		{   	// Assertion: a[first] <= a[first + 1] <= ... <= a[unsorted - 1]	
			T firstUnsorted = a[unsorted];
			insertInOrder(firstUnsorted, a, first, unsorted - 1);
		} // end for
	} // end insertionSort
	
	private static <T extends Comparable<? super T>> 
	        void insertInOrder(T element, T[] a, int begin, int end)
	{
		int index;		
		for (index = end; index >= begin; index--)
		{
			comps++;
			if (element.compareTo(a[index]) < 0)
				a[index + 1] = a[index]; // make room
			else
				break;
		} // end for		
		a[index + 1] = element;
	} // end insertInOrder
   
} // end SortArray