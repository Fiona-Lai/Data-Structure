// CS 0445 Spring 2023
// Assignment of Recursion Main Program
public class Recursion {
	public static String[] data = { "first", "second", "third", "fourth", "fifth" };

	public static void main(String[] args) {
		// These statements should work with the DoubleRecList class as provided
		// to you.
		DoubleRecList list = new DoubleRecList(data);
		System.out.println(list.toString());

		// Once you have implemented the reverse() method, uncomment the code
		// below to test it. This should print out the list in reverse order
		// and then back in the original order.
		list.reverse();
		System.out.println(list.toString());
		list.reverse();
		System.out.println(list.toString());

	}
}