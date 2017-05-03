/*
 * Source: https://leetcode.com/problems/all-oone-data-structure/
 * 
 * Implement a data structure supporting the following operations:
 *  - Inc(Key) - Inserts a new key with value 1. Or increments an existing key 
 *    by 1. Key is guaranteed to be a non-empty string.
 *  - Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise 
 *    decrements an existing key by 1. If the key does not exist, this function 
 *    does nothing. Key is guaranteed to be a non-empty string.
 *  - GetMaxKey() - Returns one of the keys with maximal value. If no element 
 *    exists, return an empty string "".
 *  - GetMinKey() - Returns one of the keys with minimal value. If no element 
 *    exists, return an empty string "".
 *
 * Note: The solution set must not contain duplicate triplets.
 *
 * Solution meets requirement above. However, the following optimizations were not
 * implemented given that this version passed all test cases on leetcode:
 *   - updateMaxMin() can be optimized to track min/max value in O(1)
 *   - getHash() can be upgraded to handle hash collisions
 *   - SIZE value should be intelligently handled (maybe start with a small value
 *     and double the size everytime the array is full)
 *
 */

import java.util.*;

public class AllOne {
    static final int SIZE = 100000;
    static private String[] keys;
    static private int[] values;
    static private List<Integer> sortedIndex;

    public static void main(String[] args){
        // Only here so class can be run independently
        AllOne obj = new AllOne();

        System.out.println("Incrementing key first 5 times");
        for(int i=0; i<5; i++)
            obj.inc("first");
        System.out.println("Min Key = " + obj.getMinKey() + "\nMax Key = " + obj.getMaxKey());

        System.out.println("Incrementing key second 10 times");
        for(int i=0; i<10; i++)
            obj.inc("second");
        System.out.println("Min Key = " + obj.getMinKey() + "\nMax Key = " + obj.getMaxKey());

        System.out.println("Incrementing key third 15 times");
        for(int i=0; i<15; i++)
            obj.inc("third");
        System.out.println("Min Key = " + obj.getMinKey() + "\nMax Key = " + obj.getMaxKey());

        System.out.println("Incrementing key first 15 times");
        for(int i=0; i<15; i++)
            obj.inc("first");
        System.out.println("Min Key = " + obj.getMinKey() + "\nMax Key = " + obj.getMaxKey());
    }

    public AllOne(){
        // Initializes the data structure
        keys = new String[SIZE];
        values = new int[SIZE];
        sortedIndex = new ArrayList<>();
    }
    
    public static void inc(String key){
        // Finds the hashcode for the key and inserts/increments it by 1
        int index = getHash(key);
        if(values[index] == 0)
            keys[index] = key;
        values[index]++;
        updateMaxMin(index);
    }
    
    public static void dec(String key){
        // Finds the hashcode for the key and decrements it by 1 or remove
        // entry if value < 1
        int index = getHash(key);
        if(values[index] > 0){
            values[index]--;
            if(values[index] == 0){
                keys[index] = "";
            }
            updateMaxMin(index);
        }
    }
    
    private static void updateMaxMin(int index){
        // Uses a comparator to keep list of keys sorted by their values
        // to keep easily be able to retrieve key with max/min value
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
    
    public static String getMaxKey(){
        // Returns key of entry with maximum value
        if(sortedIndex.size() > 0)
            return keys[sortedIndex.get(sortedIndex.size()-1)];
        else
            return "";
    }
    
    public static String getMinKey(){
        // Returns key of entry with minimum value
        if(sortedIndex.size() > 0)
            return keys[sortedIndex.get(0)];
        else
            return "";
    }
    
    private static int getHash(String s){
        // Hash function which determines the array index depending
        // on the sum of ASCII values of the characters in s
        int sum = 0;
        for(int i=0; i<s.length(); i++)
            sum += s.charAt(0);
        return sum%(Integer.MAX_VALUE-1);
    }
}