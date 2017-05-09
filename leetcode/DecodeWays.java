/*
 * Source: https://leetcode.com/problems/decode-ways/
 * 
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * .
 * .
 * 'Z' -> 26
 *
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 *
 * Solution recursively traverses string and validates each digit or each paur of 2 digits, 
 * returning 1 if it reached the end with everything being valid. Meanwhile, values are 
 * stored in a lookup table as optimization to avoid recalculating already calculated paths.
 * 
 * Time complexity: O(n) <-- O(2^n) without memoization
 *
 */

public class DecodeWays {
	static int[] lookupTbl;
    static boolean[] lookupTblVerif;

    public static void main(String[] args){
        String s = "123123";
        System.out.println(numDecodings(s));
    }

    public static int numDecodings(String s) {
    	// Initializes lookup table and start decoding s recursively
        if(s.length() > 0){
            lookupTbl = new int[s.length()];
            lookupTblVerif = new boolean[s.length()];
            return recursiveDecode(s, 0);
        }
        else
            return 0;
    }

    public static int recursiveDecode(String s, int pos) {
        // If we reach end of s, we found a valid way
        if(pos >= s.length())
            return 1;
        
        // If this call has already previously been made, use value
        // returned from that call
        if(lookupTblVerif[pos] == true)
            return lookupTbl[pos];
        
        // Consider the digit and the digit right as possible 
        // encoded values. Recursively decode next character(s)
        // if valid and store return value in lookup table
        int s1 = Integer.valueOf(s.substring(pos,pos+1));
        int s2 = 0;
        
        if(s1 < 3 && pos+2 <= s.length())
            s2 = Integer.valueOf(s.substring(pos,pos+2));
        
        if(isValid(s1) && isValid(s2))
            lookupTbl[pos] = recursiveDecode(s, pos+1) + recursiveDecode(s, pos+2);
        else if (isValid(s1))
            lookupTbl[pos] = recursiveDecode(s, pos+1);
        else
            lookupTbl[pos] = 0;
        lookupTblVerif[pos] = true;
        
        return lookupTbl[pos];
    }

    public static boolean isValid(int i){
    	// Validates whether i is an encoded value
        return i >= 1 && i <= 26;
    }

}