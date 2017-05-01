/*
 * Source: https://leetcode.com/problems/132-pattern/
 * 
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such 
 * that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input 
 * and checks whether there is a 132 pattern in the list.
 *
 * Note: n will be less than 15,000.
 * 
 * Solution stores pairs of k and j in a List of Pairs (defined below). It traverses the array while 
 * checking if the current index stores an i valid for any previously stored pair and adds/updates
 * new Pairs
 *
 */

import java.util.*;


public class Pattern132 {
    
    public Pattern132(){
        int[] data1 = {7, 6, 5, 4, 3 , 2 ,1};
        System.out.println(Arrays.toString(data1) + ":" + process(data1));
    }
    
    public boolean process(int[] nums){
        List<Pair> list = new ArrayList<>();
        
        int last = Integer.MIN_VALUE;
        for(int i=nums.length-1;i>=0;i--){
            
            //adds first Pair to list
            if(list.isEmpty()){
                list.add(new Pair(nums[i]));
                last = nums[i];
            }
            
            //Optimization: Skip duplicates
            if(nums[i] == last){
                continue;
            } last = nums[i];
            
            //Optimization: if number greater than last one, add it as a new Pair
            if (nums[i] > last){
                list.add(new Pair(nums[i]));
                continue;
            }
            
            //loop through already existing pairs of j and k
            boolean added = false;
            for(int j=0; j<list.size(); j++){
                Pair p = list.get(j);

                if(p.isFull()){
                    if(nums[i]<p.k){
                        //found valid i
                        return true;
                    } else if (nums[i]>p.j){
                        //found a bigger value for J
                        //Optimization: instead of creating a new pair for old J, use it as the
                        //value of k 
                        if(p.j > p.k){
                            p.setK(p.j);
                        }
                        p.setJ(nums[i]);
                        list.set(j, p);
                        added = true;
                    }
                } else {
                    //if Pair only has a value of k added to it
                    if(nums[i]>p.k){
                        p.setJ(nums[i]);
                        list.set(j, p);
                        added = true;
                    }
                }
            }
            
            if(!added){
                //if couldn't merge pair with existing pairs, add it as a new pair
                list.add(new Pair(nums[i]));
            }
            
        }
        return false;
    }
    
    public void printList(List<Pair> list){
        for(Pair p: list){
            System.out.print(p + " ");
        }
        System.out.println();
    }
}


class Pair{ 
    public Integer j, k; 
    boolean kSet, jSet;
        
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