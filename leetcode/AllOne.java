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

public class AllOne {
    private int size = 100000;
    private String[] keys;
    private int[] values;
    private List<Integer> sortedIndex;

    /** Initialize your data structure here. */
    public AllOne() {
        keys = new String[size];
        values = new int[size];
        sortedIndex = new ArrayList<>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int index = getHash(key);
        if(values[index] == 0){
            keys[index] = key;
        }
        values[index]++;
        updateMaxMin(index);
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        int index = getHash(key);
        if(values[index] > 0){
            values[index]--;
            if(values[index] == 0){
                keys[index] = "";
            }
            updateMaxMin(index);
        }
    }
    
    private void updateMaxMin(int index){
        if(!sortedIndex.contains(index))
            sortedIndex.add(index);

        Comparator<Integer> comp = (Integer a, Integer b) -> {
            Integer x = values[a]; Integer y = values[b];
            return x.compareTo(y);
        };

        Collections.sort(sortedIndex, comp);
        
        if(values[sortedIndex.get(0)] == 0)
            sortedIndex.remove(0);
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(sortedIndex.size() > 0)
            return keys[sortedIndex.get(sortedIndex.size()-1)];
        else
            return "";
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(sortedIndex.size() > 0)
            return keys[sortedIndex.get(0)];
        else
            return "";
    }
    
    private int getHash(String s){
        int sum = 0;
        for(int i=0; i<s.length(); i++)
            sum += s.charAt(0);
        return sum%(Integer.MAX_VALUE-1);
    }
}