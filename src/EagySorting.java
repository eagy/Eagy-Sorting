import java.util.Arrays;

/**
 * Implements various sorting algorithms.
 * 
 * @author Daniel Eagy, Acuna, Sedgewick
 * @verison 1.0 
 */

public class EagySorting {

	/**
	 * Entry point for sample output.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// Q1
		String[] a = { "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
		quicksortmid(a);
		assert isSorted(a); // requires assertions enabled.
		show(a);

		// Q2
		String[] b = { "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
		mergeSort(b);
		assert isSorted(b);
		show(b);
		
		// TESTING //

		// Test 1: Already Sorted (quicksort)
		Integer[] test1 = {1,2,3,4,5,6,7,8,9,10};
		System.out.println("Test 1: Already Sorted (quicksort)");
		System.out.print("Before: ");
		show(test1);
		quicksortmid(test1);
		assert isSorted(test1);
		System.out.print("After: ");
		show(test1);
		
		// Test 1: Already Sorted  (mergesort)
		test1 = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		System.out.println("Test 1: Already Sorted  (mergesort)");
		System.out.print("Before: ");
		show(test1);
		mergesort(test1);
		assert isSorted(test1);
		System.out.print("After: ");
		show(test1);
		
		// Test 2: Mostly Sorted (quicksort)
		Integer[] test2 = {1,2,3,4,5,6,7,9,8,10};
		System.out.println("Test 2: Mostly Sorted (quicksort)");
		System.out.print("Before: ");
		show(test2);
		quicksortmid(test2);
		assert isSorted(test2);
		System.out.print("After: ");
		show(test2);
		
		// Test 2: Mostly Sorted (mergesort)
		test2 = new Integer [] {1,2,3,4,5,6,7,9,8,10};
		System.out.println("Tetst 2: Mostly Sorted (mergesort)");
		System.out.print("Before: ");
		show(test2);
		mergesort(test2);
		assert isSorted(test2);
		System.out.print("After: ");
		show(test2);
		
		// Test 3: Random Order (quicksort)
		Integer[] test3 = {4, 7, 5, 9, 1, 8, 3, 10, 2, 6}; 
		System.out.println("Test 3: Random Order (quicksort)");
		System.out.print("Before: ");
		show(test3);
		quicksortmid(test3);
		assert isSorted(test3);
		System.out.print("After: ");
		show(test3);
		
		// Test 3: Random Order (mergesort)
		test3 = new Integer[] {4, 7, 5, 9, 1, 8, 3, 10, 2, 6}; 
		System.out.println("Test 3: Random Order (mergesort)");
		System.out.print("Before: ");
		show(test3);
		mergesort(test3);
		assert isSorted(test3);
		System.out.print("After: ");
		show(test3);
		
		// Test 4: Has Duplicates (quicksort)
		Integer[] test4 = {1, 2, 3, 6, 6, 5, 7, 10, 9};
		System.out.println("Test 4: Has Duplicates (quicksort)");
		System.out.print("Before: ");
		show(test4);
		quicksortmid(test4);
		assert isSorted(test4);
		System.out.print("After: ");
		show(test4);
		
		// Test 4: Has Duplicates (mergesort)
		test4 = new Integer[] {1, 2, 3, 6, 6, 5, 7, 10, 9};
		System.out.println("Test 4: Has Duplicates (mergesort)");
		System.out.print("Before: ");
		show(test4);
		mergesort(test4);
		assert isSorted(test4);
		System.out.print("After: ");
		show(test4);
		
		// Test 5: Reversed Order (quicksort)
		Integer[] test5 = {10,9,8,7,6,5,4,3,2,1};
		System.out.println("Test 5: Reversed Order (quicksort)");
		System.out.print("Before: ");
		show(test5);
		quicksortmid(test5);
		assert isSorted(test5);
		System.out.print("After: ");
		show(test5);
		
		// Test 5: Has Duplicates (mergesort)
		test5 = new Integer[] {10,9,8,7,6,5,4,3,2,1};
		System.out.println("Test 5: Reversed Order (mergesort)");
		System.out.print("Before: ");
		show(test5);
		mergesort(test5);
		assert isSorted(test5);
		System.out.print("After: ");
		show(test5);
	}

	public static <T extends Comparable<T>> void quicksortmid(T[] a) {

		quickSort(a);

	}

	/**
	 * Sorts the specified array of objects using the quick sort algorithm.
	 * 
	 * @param data
	 *            the array to be sorted
	 */
	public static <T extends Comparable<T>> void quickSort(T[] data) {
		quickSort(data, 0, data.length - 1);
	}

	/**
	 * Recursively sorts a range of objects in the specified array using the quick
	 * sort algorithm.
	 * 
	 * @param data
	 *            the array to be sorted
	 * @param min
	 *            the minimum index in the range to be sorted
	 * @param max
	 *            the maximum index in the range to be sorted
	 */
	private static <T extends Comparable<T>> void quickSort(T[] data, int min, int max) {
		if (min < max) {
			// create partitions
			int indexofpartition = partition(data, min, max);

			// sort the left partition (lower values)
			quickSort(data, min, indexofpartition - 1);

			// sort the right partition (higher values)
			quickSort(data, indexofpartition + 1, max);
		}
	}

	/**
	 * Used by the quick sort algorithm to find the partition.
	 * 
	 * @param data
	 *            the array to be sorted
	 * @param min
	 *            the minimum index in the range to be sorted
	 * @param max
	 *            the maximum index in the range to be sorted
	 */
	private static <T extends Comparable<T>> int partition(T[] data, int min, int max) {

		T partitionelement;
		int left, right;
		int middle = (min + max) / 2;
		
		// Middle-of-three method
		// comparing min, max, and mid and making sure their in the correct order prior to 
		// running through the lists
		while (data[min].compareTo(data[middle]) > 0 || data[middle].compareTo(data[max]) > 0) {
			if (data[min].compareTo(data[middle]) > 0) {
				swap(data, min, middle);
			}
			if (data[middle].compareTo(data[max]) > 0) {
				swap(data, middle, max);
			}

		}

		// use the middle data value as the partition element
		partitionelement = data[middle];
		// move it out of the way for now
		swap(data, middle, min);

		left = min;
		right = max;

		while (left < right) {
			// search for an element that is > the partition element
			while (left < right && data[left].compareTo(partitionelement) <= 0)
				left++;

			// search for an element that is < the partition element
			while (data[right].compareTo(partitionelement) > 0)
				right--;

			// swap the elements
			if (left < right)
				swap(data, left, right);
		}

		// move the partition element into place
		swap(data, min, right);

		return right;
	}

	/**
	 * Swaps to elements in an array. Used by various sorting algorithms.
	 * 
	 * @param data
	 *            the array in which the elements are swapped
	 * @param index1
	 *            the index of the first element to be swapped
	 * @param index2
	 *            the index of the second element to be swapped
	 */
	private static <T extends Comparable<T>> void swap(T[] data, int index1, int index2) {
		T temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
	public static void mergesort(Comparable[] a) {
		a = mergeSort(a);
	}
	
    /**
	 * Recursively sorts a range of objects in the specified array using the
	 * merge sort algorithm.
     *
     * @param a the array to be sorted
     */
	private static Comparable[] mergeSort(Comparable[] a) {
		if (a.length > 1)
		{
			int mid = (a.length) / 2;
			Comparable[] b = Arrays.copyOfRange(a, 0, mid);
			Comparable[] c = Arrays.copyOfRange(a, mid, a.length);
						
			for(int i = 0; i < mid; i++) {
				b[i] = a[i];
			}
			
			for(int i = 0; i < c.length; i++) {
				c[i] = a[i+mid];
			}

			mergeSort(b);
			mergeSort(c);

			
			Comparable[] temp;
			temp = merge(b,c);
			for (int i = 0; i < temp.length; i++) {
				a[i] = temp[i];
			}
			
			return a;
		}
		else
			return a;
			
	}
	
	/**
     * Merges two sorted subarrays of the specified array.
     *
     * @param a the array to be sorted
     * @param b 
     */
	@SuppressWarnings("unchecked")
	private static Comparable[] merge(Comparable[] a, Comparable[] b) {
		
		Comparable[] merged = new Comparable[a.length+b.length];
		int aIndex = 0, bIndex = 0, index = 0;
		while (aIndex < a.length && bIndex < b.length) {
			if (a[aIndex].compareTo(b[bIndex]) < 0) {
				merged[index] = a[aIndex];
				aIndex++;
			}
			else {
				merged[index] = b[bIndex];
				bIndex++;
			}
			index++;
		}
		
		while (aIndex < a.length) {
			merged[index] = a[aIndex];
			index++;
			aIndex++;
		}
		
		while (bIndex < b.length) {
			merged[index] = b[bIndex];
			index++;
			bIndex++;
		}
		return merged; 
   }

	/**
	 * Displays contents of array, space separated.
	 * 
	 * @author Sedgewick
	 * @param a
	 *            Array to display.
	 */
	private static void show(Comparable[] a) {
		for (Comparable a1 : a)
			System.out.print(a1 + " ");

		System.out.println();
	}

	/**
	 * Checks if array is in sorted order.
	 * 
	 * @author Sedgewick
	 * @param a
	 *            Array to be checked.
	 * @return Returns true if array is sorted.
	 */
	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1]))
				return false;

		return true;
	}

	// See previous method.
	private static boolean less(Comparable<Comparable> v, Comparable w) {
		return v.compareTo(w) < 0;
	}
}