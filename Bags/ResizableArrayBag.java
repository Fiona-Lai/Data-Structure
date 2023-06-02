import java.util.*;

public class ResizableArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty bag with an initial capacity of 25. */
    public ResizableArrayBag() {
        this(DEFAULT_CAPACITY);
    } // end of default constructor

    /**
     * Creates an empty bag with a given initial capacity.
     * 
     * @param capacity : An integer value of desired capacity.
     */
    public ResizableArrayBag(int capacity) {
        if (capacity <= MAX_CAPACITY) {
            numberOfEntries = 0;
            // the case is safe because the new array contains null entries.
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[capacity];
            bag = tempBag;
            integrityOK = true;
        } else
            throw new IllegalStateException("Attempt to create a bag whose"
                    + "capacity exceeds allowed maximum.");
    } // end of type constructor

    /**
     * Creates a bag containing the elements of the array.
     * 
     * @param a : An array of objects.
     */
    public ResizableArrayBag(T[] a) {
        this(a.length);
        // Add each non-null item in the array to the new bag.
        for (T it : a)
            if (it == null) {
            	// Stop adding items when we reach the end of the array.
                break;
            } else
            	// Add the item to the new bag.
                add(it);
        integrityOK = true;  // 表示构造函数执行完毕并确保对象的完整性
    }

    /**
     * Creates a bag containing the elements of the bag.
     * 
     * @param a : A bag of objects.
     */
    public ResizableArrayBag(ResizableArrayBag<T> a) {
        this(a.bag);  // 调用了另一个构造函数 this(a.bag)
    }

    /**
     * Create a new bag to store the result of the union operation.
     * @return A new bag that represents the union of the two bags.
     */
    public BagInterface<T> union(BagInterface<T> anotherBag) {
        BagInterface<T> ret = new ResizableArrayBag<T>(this);
        ResizableArrayBag<T> argBag = (ResizableArrayBag<T>) anotherBag;
        for (T it : argBag.bag)  // argBag 是一个 ResizableArrayBag<T> 类型的对象，它具有一个名为 bag 的属性或成员变量,argBag.bag 指的是 argBag 对象中的袋子，用于存储元素
                break;
            } else
                ret.add(it);
        return ret;
    }

    /**
     * Create a new bag to store the result of the intersection operation.
     * @return A new bag that represents the intersection of the two bags.
     */
    public BagInterface<T> intersection(BagInterface<T> anotherBag) {
        BagInterface<T> ret = new ResizableArrayBag<T>(this);
        ResizableArrayBag<T> argBag = new ResizableArrayBag<T>((ResizableArrayBag<T>) anotherBag);
        // Iterate over the items in this bag.
        for (T it : bag)  // bag 是当前对象的一个属性或变量，用于表示当前袋子对象。它是当前对象中存储元素的袋子
            if (it == null) {
            	// Stop iterating when we reach the end of the bag.
                break;
            } else if (argBag.contains(it)){
            	// If the other bag also contains the item, remove it from the other bag.
                argBag.remove(it);
            } else {
            	// If the other bag does not contain the item, remove it from the result bag.
                ret.remove(it);
            }
        // Return the result bag.
        return ret;
    }

    /**
     * Create a new bag to store the result of the difference operation.
     * @return A new bag that represents the difference of the two bags.
     */
    public BagInterface<T> difference(BagInterface<T> anotherBag) {
        BagInterface<T> ret = new ResizableArrayBag<T>(this);
        ResizableArrayBag<T> argBag = new ResizableArrayBag<T>((ResizableArrayBag<T>) anotherBag);
        for (T it : bag)
            if (it == null) {
                break;
            } else if (argBag.contains(it)){
            	// If the other bag also contains the item, remove it from both bags.
                argBag.remove(it);
                ret.remove(it);
            }
        return ret;
    }
    
    /**
     * Gets the current number of entries in this bag.
     * 
     * @return The integer number of entries in the bag currently.
     */
    public int getCurrentSize() {
        return numberOfEntries;
    } // end of "getCurrentSize"

    /** Throws an exception if this object is not initialized. */
    private void checkIntegrity() {
        if (!integrityOK)
            throw new SecurityException("ArrayBag object is corrupt.");
    } // end of "checkIntegrity"

    /**
     * Determines whether the bag is empty or not.
     * 
     * @returns true if the bag is empty, false otherwise.
     */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end of "isEmpty"

    /** Throws an exception if the client requests a capacity that is too large. */
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose " +
                    "capacity exceeds allowed " +
                    "maximum of " + MAX_CAPACITY);
    } // end of "checkCapacity"

    /** Doubles the size of the array bag. */
    private void doubleCapacity() {
        int newCapacity = 2 * DEFAULT_CAPACITY;
        checkCapacity(newCapacity);
        bag = Arrays.copyOf(bag, newCapacity);
    } // end of "doubleCapacity"

    /**
     * Adds a new entry to the bag.
     * 
     * @param newEntry : The object to be added as a new entry.
     * @return true if the addition is successful, false otherwise.
     */
    public boolean add(T newEntry) {
        checkIntegrity();

        // check to see if the bag is full.
        if (numberOfEntries == bag.length) {
            // if so, double the capacity.
            doubleCapacity();
        } // end of if-statement

        // add the new entry to the bag.
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;
    } // end of "add"

    /**
     * Locates a given entry within the array bag.
     * 
     * @return The index of the entry if located, or -1 otherwise.
     */
    private int getIndexOf(T anEntry) {
        int where = -1;
        boolean found = false;
        int i = 0;

        while (!found && (i < numberOfEntries)) {
            if (anEntry.equals(bag[i])) {
                found = true;
                where = i;
            } // end if
            i++;
        } // end while

        // Assertion: If where > -1, anEntry is in the array bag;
        // otherwise, anEntry was not found in this array bag.
        return where;
    } // end of "getIndexOf"

    /**
     * Removes and returns the entry at a given index within the array bag.
     * 
     * @returns An entry that is being removed, or null uf bi such entry exists.
     */
    private T removeEntry(int givenIndex) {
        T result = null;

        if (!isEmpty() && (givenIndex >= 0)) {
            result = bag[givenIndex]; // Entry to remove
            bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry
            bag[numberOfEntries - 1] = null; // Remove last entry
            numberOfEntries--;
        } // end if

        // return the entry that was removed.
        return result;
    } // end of "removeEntry"

    /**
     * Removes one unspecified entry from this bag, if possible.
     * 
     * @return The removed entry if the removal was successful, or null otherwise.
     */
    public T remove() {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);

        return result;
    } // end of "remove"

    /**
     * Removes one occurence of a given entry from this bag.
     * 
     * @param anEntry : The entry to be removed.
     * @return True if the removal was successful, false otherwise.
     */
    public boolean remove(T anEntry) {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);

        return anEntry.equals(result);
    } // end of "remove"

    /** Removes all entries from the bag. */
    public void clear() {
        while (!isEmpty())
            remove();
    } // end of "clear"

    /**
     * Counts the number of times a given entry appears in this bag.
     * 
     * @param anEntry : The entry to be counted.
     * @return The number of times anEntry appears in this bag.
     */
    public int getFrequencyOf(T anEntry) {
        checkIntegrity();
        int counter = 0;

        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry.equals(bag[i])) {
                counter++;
            } // end if
        } // end for

        return counter;
    } // end of "getFrequencyOf"

    /**
     * Tests whether the bag contains a given entry.
     * 
     * @param anEntry : The entry to locate.
     * @return True if the bag contains anEntry, or false otherwise.
     */
    public boolean contains(T anEntry) {
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    } // end of "contains"

    /**
     * Retrieves all entries that are in this bag.
     * 
     * @return A newly allocated array of all the entries in this bag.
     */
    public T[] toArray() {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        for (int i = 0; i < numberOfEntries; i++) {
            result[i] = bag[i];
        } // end for loop

        return result;
    } // end of "toArray"

} // end of "ResizableArrayBag"