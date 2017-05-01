/*
 * Source: https://leetcode.com/problems/3sum/
 * 
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find 
 * all unique triplets in the array which gives the sum of zero.
 *
 * Note: The solution set must not contain duplicate triplets.et
 *
 */

import java.util.*;


public class ThreeSum {
    public ThreeSum(){
        int[] data = {3,0,-2,-1,1,2};
        System.out.println(threeSum(data).toString());
    }
          
    public List<List<Integer>> threeSum(int[] data) {
        HashMap<Integer, List<Integer>> sums = new HashMap<>();
        List<List<Integer>> l = new ArrayList<>();
        Arrays.sort(data);
        
        for(int i=0; i<data.length-2; i++){
            int a = data[i];
            if(a > 0) 
                continue;

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
                
                if(sum > 0)
                    right--;
                else
                    left++;
            }
        }
        
        return l;
    }
}