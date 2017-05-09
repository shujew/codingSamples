/*
 * Source: https://codefights.com/arcade/intro/level-2/2mxbGwLzvkTCKAJMG
 *
 * Problem: Given a sequence of integers as an array, determine whether it is 
 * possible to obtain a strictly increasing sequence by removing no more than 
 * one element from the array.
 *
 * Solution first tries to find an erroneous pair i and i+1 which are not in 
 * ascending order. It then carries two actions:
 *   1. It tries to find another erroneous pair ignoring the value i
 *   2. It tries to find another erroneous pair ignoring the value i+1
 * If 2 of 3 fails, then we have an answer else there is not answer.
 *
 */

import java.util.*;

public class AlmostIncreasingSequence {
	public static void main(String[] args){
        int[] seq1 = {1, 2, 3, 4, 5, 3, 5, 6};
        int[] seq2 = {1, 2, 3, 4, 99, 5, 6};
        System.out.println(Arrays.toString(seq1) + ": " + almostIncreasingSequence(seq1));
        System.out.println(Arrays.toString(seq2) + ": " + almostIncreasingSequence(seq2));
    }

    // Implements solution described in header
    public static boolean almostIncreasingSequence(int[] n) {    
	    int firstCulprit = findCulprit(n, -1);
	    
	    if(firstCulprit == -1)
	        return true;
	    
	    int possibleCulprit1 = findCulprit(n, firstCulprit);
	    int possibleCulprit2 = findCulprit(n, firstCulprit+1);
	    
	    if(possibleCulprit1 == -1 || possibleCulprit2 == -1)
	        return true;
	    
	    return false;
	}

    // Use a forwards loop to find erroneous pair
    public static int findCulprit(int[] n, int pos){    
	    if(pos > 0 && pos < n.length-1){
	        if(n[pos-1] >= n[pos+1])
	            return pos-1;
	    }
	        
	    for(int i=pos+1; i<n.length-1; i++){
	        if(n[i] >= n[i+1])
	            return i;
	    }
	    
	    return -1;
	}

}