/*
 * Source: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * 
 * Define a pair (u,v) which consists of one element from the first array and one 
 * element from the second array.
 *
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 *
 * Solution loops over the first array, uses a helper function to find
 * the next smallest value and adds it to the list to be returned. It
 * keeps track of the smallest solutions using 2 arrays:
 *   - Array min holds the minimum sum for the values from the first 
 *     array. When all pairs with nums2 are generated for an index, 
 *     value changes to max Integer.MAX_VALUE
 *   - Array next holds the index of the value in second array for which
 *     the corresponding value in the first array has the smallest sum
 *     with
 * 
 */

import java.util.*;

public class KPairsSmallestSums {

	public static void main(String[] args){
		int[] nums1 = {1, 7, 11};
		int[] nums2 = {2, 4, 6};

		List<int[]> l =  kSmallestPairs(nums1, nums2, 3);
		for(int[] arr: l)
			System.out.print(Arrays.toString(arr) + " ");
		System.out.print("\n");
    }
    
    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> l = new ArrayList<>();
        
        if(nums1.length == 0 || nums2.length == 0 || k == 0)
            return l;
        
        int[] min = new int[nums1.length];
        int[] next = new int[nums1.length];

        while(k > 0){
            //update minimum sum values
            int over = 0;
            for(int i = 0; i < nums1.length; i++){
                if(next[i] >= nums2.length){
                    min[i] = Integer.MAX_VALUE;
                    over++;
                }
                else
                    min[i] = nums1[i] + nums2[next[i]];
            }

            //check if all pairs have been generated
            if(over == nums1.length)
            	break;
                
            //get minimum index
            int minID = getMinIndex(min);
            
            //add to list
            int[] toAdd = {nums1[minID], nums2[next[minID]]};
            l.add(toAdd);
            next[minID] += 1;
            
            k--;            
        }
        
        return l;
    }
    
    // returns minimum index from possible sum values
    public static int getMinIndex(int[] min){
        int minVal = Integer.MAX_VALUE;
        int minID = Integer.MAX_VALUE;
        for(int i=0; i<min.length; i++){
            if(minVal > min[i]){
                minVal = min[i];
                minID = i;
            }
        }
        return minID;
    }
}