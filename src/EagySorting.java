import java.util.Arrays;

/**
 * Implements various sorting algorithms.
 * 
 * @author (your name), Acuna, Sedgewick
 * @verison (version)
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
		String[] a = { "O", "S", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
		quicksortmid(a);
		assert isSorted(a); // requires assertions enabled.
		show(a);

		// Q1
		Integer[] c = { 4, 7, 3, 1, 5, 16, 9 };
		quicksortmid(c);
		assert isSorted(c); // requires assertions enabled.
		show(c);

		// Q2
		String[] b = { "S", "0", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
		mergeSort(b);
		assert isSorted(b);
		show(b);
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

	
    /**
	 * Recursively sorts a range of objects in the specified array using the
	 * merge sort algorithm.
     *
     * @param a the array to be sorted
     */
	private static void mergeSort(Comparable[] a)
	{
		if (a.length > 0)
		{
			int mid = (a.length-1) / 2;
			Comparable[] b = new Comparable[mid];
			Comparable[] c = new Comparable[a.length-1-mid];
			
			for(int i = 0; i < mid; i++) {
				b[i] = a[i];
			}
			
			for(int i = 0; i < c.length; i++) {
				c[i] = a[i+mid];
			}
			//mergeSort(data, min, mid);
			//mergeSort(data, mid+1, max);
			//merge(data, min, mid, max);
			mergeSort(b);
			mergeSort(c);
			Comparable[] temp;
			temp = merge(b,c);
			for (int i = 0; i < temp.length; i++) {
				a[i] = temp[i];
			}
		}
			
	}
	
	/**
     * Merges two sorted subarrays of the specified array.
     *
     * @param a the array to be sorted
     * @param b 
     */
	@SuppressWarnings("unchecked")
	private static Comparable[] merge(Comparable[] a, Comparable[] b) {
		int size = (a.length)+(b.length);
		Comparable[] merged = new Comparable[size];

		int aIndex = 0;
		int bIndex = 0;
		int index = 0;
		
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
/*		T[] temp = (T[])(new Comparable[a.length]);
		
		int first1 = first, last1 = mid;  // endpoints of first subarray
		int first2 = mid+1, last2 = last;  // endpoints of second subarray
		int index = first1;  // next index open in temp array
		
		//  Copy smaller item from each subarray into temp until one
		//  of the subarrays is exhausted
		while (first1 <= last1 && first2 <= last2)
		{
			if (a[first1].compareTo(a[first2]) < 0)
			{
				temp[index] = a[first1];
				first1++;
			}
			else
			{
				temp[index] = a[first2];
				first2++;
			}
			index++;
		}
		
		//  Copy remaining elements from first subarray, if any
		while (first1 <= last1)
		{
			temp[index] = a[first1];
			first1++;
			index++;
		}
		
		//  Copy remaining elements from second subarray, if any
		while (first2 <= last2)
		{
			temp[index] = a[first2];
			first2++;
			index++;
		}
		
		//  Copy merged data into original array
		for (index = first; index <= last; index++)
			a[index] = temp[index];*/
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