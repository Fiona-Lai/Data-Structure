import java.util.Random;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // System.in 表示标准输入流
        
        // Ask the user to enter an array size and input it
        System.out.print("Enter the array size: ");
        int size = scanner.nextInt();  
        
        // Create two arrays of Integer equal in length to the value input by the user
        Integer[] array1 = new Integer[size];
        Integer[] array2 = new Integer[size];
        
        // Fill the arrays with random integers
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array1[i] = random.nextInt();  // random.nextInt() 是 Random 类中的一个方法，用于生成一个随机的整数值
            array2[i] = array1[i];
        }
        
        // Sort the arrays using selection sort and insertion sort
        SimpleSorts.selectionSort(array1, size);
        long selectionSortComps1 = SimpleSorts.comps;
        
        SimpleSorts.insertionSort(array2, size);
        long insertionSortComps1 = SimpleSorts.comps;
        
        // Sort the arrays again (already sorted) and store the comparison values
        SimpleSorts.selectionSort(array1, size);
        long selectionSortComps2 = SimpleSorts.comps;
        
        SimpleSorts.insertionSort(array2, size);
        long insertionSortComps2 = SimpleSorts.comps;
        
        // Reverse the data in each array
        reverseArray(array1);
        reverseArray(array2);
        
        // Sort the reverse-sorted arrays and store the comparison values
        SimpleSorts.selectionSort(array1, size);
        long selectionSortComps3 = SimpleSorts.comps;
        
        SimpleSorts.insertionSort(array2, size);
        long insertionSortComps3 = SimpleSorts.comps;
        
        // Output the values stored to see the differences
        System.out.println("Selection Sort:");
        System.out.println("Random Data Comparison values: " + selectionSortComps1);
        System.out.println("Sorted Data Comparison values: " + selectionSortComps2);
        System.out.println("Reverse Sorted Data Comparison values: " + selectionSortComps3);
        
        System.out.println("\nInsertion Sort:");
        System.out.println("Random Data Comparison values: " + insertionSortComps1);
        System.out.println("Sorted Data Comparison values: " + insertionSortComps2);
        System.out.println("Reverse Sorted Data Comparison values: " + insertionSortComps3);
    }
    
    // Method to reverse an array
    private static void reverseArray(Integer[] array) {
        int i = 0;
        int j = array.length - 1;
        
        while (i < j) {
            Integer temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            
            i++;
            j--;
        }
    }
}