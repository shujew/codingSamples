/*
 * Source: https://leetcode.com/problems/132-pattern/
 * 
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such 
 * that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input 
 * and checks whether there is a 132 pattern in the list.
 *
 * Note: n will be less than 15,000.
 * 
 * Solution stores pairs of k and j in a List of Pairs(defined below). It traverses the array while 
 * checking if the current index stores an i valid for any previously stored pair and adds/updates
 * new Pairs
 *
 */

import java.util.*;


public class Pattern132 {
    public static void main(String[] args){
        int[] data1 = {7, 6, 5, 4, 3 , 2 ,1};
        System.out.println(Arrays.toString(data1) + ":" + pattern132(data1));
    }
    
    public static boolean pattern132(int[] nums){
        // list contain possible Pair(k and j) values
        // last reprents the last number added to a Pair
        List<Pair> list = new ArrayList<>();
        int last = Integer.MIN_VALUE;

        for(int i=nums.length-1;i>=0;i--){
            // adds first Pair to list
            if(list.isEmpty()){
                list.add(new Pair(nums[i]));
                last = nums[i];
            }
            
            // Skip continuous duplicate values
            if(nums[i] == last){
                continue;
            } last = nums[i];
            
            // If number greater than previous one, it has to be
            // in a new pair because aj > ak
            if (nums[i] > last){
                list.add(new Pair(nums[i]));
                continue;
            }
            
            // Loop through previously added Pair to see if the value
            // fits as an i values for the pairs' j and k values if
            // Pair has both i and j values set. If value of j not
            // set in the Pair, check if value fits as a valid j for
            // the k value of the pair
            boolean added = false;
            for(int j=0; j<list.size(); j++){
                Pair p = list.get(j);

                if(p.isFull()){
                    if(nums[i]<p.k){
                        return true;
                    } else if (nums[i]>p.j){
                        // Since number is bigger than j value we can
                        // use number as new value of j since the bigger
                        // the j value the more options for i and k
                        if(p.j > p.k){
                            p.setK(p.j);
                        }
                        p.setJ(nums[i]);
                        list.set(j, p);
                        added = true;
                    }
                } else {
                    if(nums[i]>p.k){
                        p.setJ(nums[i]);
                        list.set(j, p);
                        added = true;
                    }
                }
            }
            
            // If we can't find a place to add the number in existing pairs, then
            // add it to a new pair
            if(!added){
                list.add(new Pair(nums[i]));
            }
            
        }
        return false;
    }
    
    public static void printList(List<Pair> list){
        for(Pair p: list){
            System.out.print(p + " ");
        }
        System.out.println();
    }
}


class Pair{ 
    // j and k represents indexes in the sequence where k > j
    // kSet and jSet represent whether k/j is assigned a value
    Integer j, k; 
    private boolean kSet, jSet;
    
    public Pair(int k) { 
      this.k = k; 
      jSet = false;
      kSet = true;      
    }
    
    public Pair(int k, int j) { 
      this.k = k; 
      this.j = j; 
      jSet = true;
      kSet = true;      
    }
    
    public void setJ(int j){
        this.j = j; 
        jSet = true;
    }

    public void setK(int k){
        this.k = k; 
        kSet = true;
    }
        
    public boolean isFull(){
        return jSet & kSet;
    }
        
    @Override
    public String toString(){
        return "(" + this.j + "," + this.k + ")";
    }
}  