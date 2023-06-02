import java.util.Random;
import java.util.Scanner;

public class MergeSortComparison {
    public static final int MIN_SIZE = 5;

    public static void main(String[] args) {
        // Ask the user to enter an array size and input it
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        scanner.close();

        // Create two arrays of Integer equal in length to the value input by the user
        Integer[] array1 = new Integer[size];
        Integer[] array2 = new Integer[size];

        // Fill the arrays with random integers
        fillArrayWithRandomIntegers(array1);
        fillArrayWithRandomIntegers(array2);

        // Time a single call to mergeSort() and a single call to mergeSortB()
        long startTime, endTime, mergeSortTime, mergeSortBTime;

        // Time mergeSort()
        startTime = System.nanoTime();  // Java中的一个方法，用于获取当前系统时间的纳秒级别精度
        TextMerge.mergeSort(array1, size);
        endTime = System.nanoTime();
        mergeSortTime = endTime - startTime;

        // Time mergeSortB()
        startTime = System.nanoTime();
        TextMerge.mergeSortB(array2, size);
        endTime = System.nanoTime();
        mergeSortBTime = endTime - startTime;

        // Print out the results for each sort
        System.out.println("mergeSort() runtime: " + mergeSortTime + " nanoseconds");
        System.out.println("mergeSortB() runtime: " + mergeSortBTime + " nanoseconds");
    }

    // Method to fill the arrays with random integers
    private static void fillArrayWithRandomIntegers(Integer[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
    }
}