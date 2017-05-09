/*
 * Source: https://leetcode.com/problems/3sum/
 * 
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find 
 * all unique triplets in the array which gives the sum of zero.
 *
 * Note: The solution set must not contain duplicate triplets.
 *
 * Solution sorts values in ascending order and loop through the values setting each as a possible
 * value of a. Two indices are then used which represent the smallest number and biggest number
 * that are BOTH LARGER than a. By not considering the values smaller than a, given that the values
 * is sorted, we avoid duplicates.
 *
 * Time complexity: O(n^2)
 *
 */

import java.util.*;


public class ThreeSum {
    public static void main(String[] args){
        int[] data = {3,0,-2,-1,1,2};
        System.out.println(threeSum(data).toString());
    }

    public static List<List<Integer>> threeSum(int[] data) {
        List<List<Integer>> l = new ArrayList<>();
        
        Arrays.sort(data);
        for(int i=0; i<data.length-2; i++){
            // Every value of the array is considered as a potential 
            // value for a
            int a = data[i];

            // Two indices are used to consider the possible b and c
            // values to find values such that a+b+c = 0
            int left = i+1; int right = data.length-1;
            while(right>left){
                int b = data[left];
                int c = data[right];
                int sum = a+b+c;
                
                if(sum == 0){
                    List<Integer> ll = new ArrayList<>();
                    ll.add(a); ll.add(b); ll.add(c);
                    if(!l.contains(ll))
                        l.add(ll);
                }
                
                // The sorted nature of the array is exploited to 
                // figure out which index (b or c) to move to make 
                // the sum a+b+c closer to 0
                if(sum > 0)
                    right--;
                else
                    left++;
            }
        }
        
        return l;
    }
}