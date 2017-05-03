/*
 * Source: https://leetcode.com/problems/decode-string/
 * 
 * Given an encoded string, return it's decoded string. 
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is 
 * being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are 
 * well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits 
 * are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Solution continuously loops over string until all encoded values are expanded. In case of nested
 * encoded values, solution starts by expanding nested encoded values then expand its parent encoded
 * values.
 *
 */

import java.util.*;

public class DecodeString {
	 public static void main(String[] args){
        String s = "20[abc]3[cd]ef";
        System.out.println(decodeString(s));
    }

    public static String decodeString(String s){
        int openIndex=0, closeIndex=0;
        
        // Loop over string and keep track of open and close brackets until
        // one is found without an open bracket in between
        while(openIndex != -1 && closeIndex != -1){
            openIndex = -1; closeIndex = -1;
            
            for(int i=0; i<s.length(); i++){
                char c = s.charAt(i);
                if(c == '[')
                    openIndex = i;
                if(c == ']')
                    closeIndex = i;

                if(openIndex != -1 && closeIndex != -1){
                	// Expand encoded part of string between brackets
                    int count = getCount(s, openIndex-1);
                    String encoded = s.substring(openIndex-String.valueOf(count).length(), closeIndex+1);
                    String str = s.substring(openIndex+1, closeIndex);
                    String decoded = expandString(count, str);
                    s = s.replace(encoded, decoded);
                    break;
                }
            }
        }
        return s;
    }

    public static int getCount(String s, int end){
    	// Returns the number of times that an encoded value
    	// needs to be duplicated. It supports single digit
    	// values (e.g 5) or multi-digit values (e.g. 142)
        String count = "";
        for(int i=end; i>=0; i--){
            char c = s.charAt(i);
            if(c -'0'< 10 && c - '0' >= 0)
                count = c + count;
            else
                break;
        }
        return Integer.valueOf(count);
    }

    public static String expandString(int count, String s){
        // Returns an expanded string with s duplicated count times
        String ss = "";
        for(int i=0; i<count; i++)
            ss += s;
        return ss;
    }
}