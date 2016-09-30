
package com.nowshad;

/**
 * * It selects the Kth largest element from an array of elements.
 * If an array  contains 5,6,3,2,1 and we want to find 2nd largest element it will return 5.
 * @author Nowshad
 * @version 1.0
  
 */
public class QuickSelect {
	/**
	 * swap the two elements of an array. 
	 * @param arr array of elements. 
	 * @param x  first element. 
	 * @param y  second element. 
	 */
	public static void swap(int arr[], int x,  int y) {
	    int temp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = temp;
	}

	/**
	 * Partition group the array into two parts, elements which are less than a pivot and elements which are greater than a pivot.
	 * it keeps all elements less than the pivot on the right side and all elements greater than the pivot on the left side.
	 * @param arr  
	 * @param first 
	 * @param last  
	 * @return
	 */
	public static int partition(int[] arr,  int first,  int last) {
	     int pivot = first;
	     swap(arr,last,pivot);
	     for(int i = first; i<last;i++) {
	    	 if(arr[i]>arr[last]) {
	    		 swap(arr, i, first);
	    		 first++;
	    	 }
	    	 
	     }
	     swap(arr,first,last);
	     return first;
	}

	/**
	 * Returns the Kth largest element 
	 * Left side of the pivot is greater than pivot , so the pivot is kth greatest element when there are k elements on the right
	 * including the pivot. 
	 * 
	 * @param arr
	 * @param first
	 * @param last
	 * @param k
	 * @return
	 */

	public  int kthLargest( int[] arr,  int first,  int last,  int k) {

		if(first>last) return Integer.MIN_VALUE;  
		int pivot = RandomizedPartition(arr, first, last); //get a randomized pivot. 
		if(pivot==k) return arr[pivot];
		if (pivot>k) return kthLargest(arr, first, pivot-1,k);
		else return kthLargest(arr, pivot+1,last,k);
	  
	}
	
	/**
	 * This method selects a randomized pivot to eliminate the worst case complexity of O(n^2) and then calls the partition
	 * @param A
	 * @param first first index
	 * @param last  last index
	 * @return  
	 */
	public static int RandomizedPartition(final int[] A,  int first,  int last) {
	    final int i = (int) Math.round(first + Math.random() * (last - first));
	    swap(A, i, last);
	    return partition(A, first, last);
	}
}
