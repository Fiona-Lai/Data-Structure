// CS 0445 Spring 2023
// Author's MergeSort code plus a 2nd version, MergeSortB.  In MergeSortB
// a temp array is made for each call of the mergeB() method, rather than 
// just making a single temp array and reusing it for all of the
// calls.  See the code for MergeSortB below.

public class TextMerge
{
	public static final int MIN_SIZE = 5; // for quick sort
	
	// MERGE SORT
	public static <T extends Comparable<? super T>>
	       void mergeSort(T[] a, int n)
	{
		mergeSort(a, 0, n - 1);
	} // end mergeSort

	public static <T extends Comparable<? super T>>
	       void mergeSort(T[] a, int first, int last)
	{
	  T[] tempArray = (T[])new Comparable<?>[a.length];
	  mergeSort(a, tempArray, first, last);
	} // end mergeSort
	
	private static <T extends Comparable<? super T>>
	        void mergeSort(T[] a, T[] tempArray, int first, int last)
	{
	   if (first < last)
	   {  // sort each half
	      int mid = (first + last)/2;// index of midpoint
	      mergeSort(a, tempArray, first, mid);  // sort left half array[first..mid]
	      mergeSort(a, tempArray, mid + 1, last); // sort right half array[mid+1..last]

			if (a[mid].compareTo(a[mid + 1]) > 0)      // Question 2, Chapter 9
	     	 	merge(a, tempArray, first, mid, last); // merge the two halves
	   //	else skip merge step
	   }  // end if
	}  // end mergeSort
	
	// MergeSort but where the temp array is created in the merge() method rather
	// than in a non-recursive stub method.
	public static <T extends Comparable<? super T>>
	       void mergeSortB(T[] a, int n)
	{
		mergeSortB(a, 0, n - 1);
	} // end mergeSortB
	
	private static <T extends Comparable<? super T>>
	        void mergeSortB(T[] a, int first, int last)
	{
	   if (first < last)
	   {  // sort each half	
			int mid = (first + last)/2;// index of midpoint
			mergeSortB(a, first, mid);  // sort left half array[first..mid]
			mergeSortB(a, mid + 1, last); // sort right half array[mid+1..last]

			// Call mergeB to merge -- see details on mergeB below
			if (a[mid].compareTo(a[mid + 1]) > 0)
	     	 	mergeB(a, first, mid, last); // merge the two halves
	   //	else skip merge step
	   }  // end if
	}  // end mergeSortB
	
	private static <T extends Comparable<? super T>> 
	        void merge(T[] a, T[] tempArray, int first, int mid, int last)
	{
		// Two adjacent subarrays are a[beginHalf1..endHalf1] and a[beginHalf2..endHalf2].
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// while both subarrays are not empty, copy the
	   // smaller item into the temporary array
		int index = beginHalf1; // next available location in
								            // tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
	   {  // Invariant: tempArray[beginHalf1..index-1] is in order
	   
	      if (a[beginHalf1].compareTo(a[beginHalf2]) <= 0)
	      {  
	      	tempArray[index] = a[beginHalf1];
	        beginHalf1++;
	      }
	      else
	      {  
	      	tempArray[index] = a[beginHalf2];
	        beginHalf2++;
	      }  // end if
	   }  // end for

	   // finish off the nonempty subarray

	   // finish off the first subarray, if necessary
	   for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
	      // Invariant: tempArray[beginHalf1..index-1] is in order
	      tempArray[index] = a[beginHalf1];

	   // finish off the second subarray, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
	      // Invariant: tempa[beginHalf1..index-1] is in order
	      tempArray[index] = a[beginHalf2];
		
	   // copy the result back into the original array
	   for (index = first; index <= last; index++)
	      a[index] = tempArray[index];
	}  // end merge
	
	private static <T extends Comparable<? super T>> 
	        void mergeB(T[] a, int first, int mid, int last)
	{
		// Two adjacent subarrays are a[beginHalf1..endHalf1] and a[beginHalf2..endHalf2].
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// Make a temporary array big enough to hold the current data to be merged.
		T[] tempArray = (T[])new Comparable<?>[last - first + 1];
		
		int index = 0;  // start temp array at index 0
		
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
	   {  // Invariant: tempArray[beginHalf1..index-1] is in order
	   
	      if (a[beginHalf1].compareTo(a[beginHalf2]) <= 0)
	      {  
	      	tempArray[index] = a[beginHalf1];
	        beginHalf1++;
	      }
	      else
	      {  
	      	tempArray[index] = a[beginHalf2];
	        beginHalf2++;
	      }  // end if
	   }  // end for

	   // finish off the nonempty subarray

	   // finish off the first subarray, if necessary
	   for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
	      // Invariant: tempArray[beginHalf1..index-1] is in order
	      tempArray[index] = a[beginHalf1];

	   // finish off the second subarray, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
	      // Invariant: tempa[beginHalf1..index-1] is in order
	      tempArray[index] = a[beginHalf2];
		
	   // copy the result back into the original array.  Since the indices of the
	   // temp array and the original array do not match we must index each array
	   // appropriately.
	   for (index = 0; index < tempArray.length; index++)
	      a[first + index] = tempArray[index];
	}  // end merge
	
	
// -------------------------------------------------------------------------------
}