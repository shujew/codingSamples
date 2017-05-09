/*
 * Source: https://leetcode.com/problems/add-binary/
 *
 * Problem: Given two binary strings, return their sum (also a binary string).
 *
 * Solution first pre-processes binary strings to ensure that they are of the
 * same length. Then it carries out the addition keeping track of carry values.
 * There wasn't a specific max length set for the binary numbers so there is no
 * handling of overflows implemented.
 *
 * Time complexity: O(n)
 *
 */

public class AddBinary {
    public static void main(String[] args){
        String a = "100101";
        String b = "10101";
        System.out.println(addBinary(a,b));
    }

    public static String addBinary(String a, String b) {
        // ensure that a and b are of the same length
        if(a.length() > b.length()){
            int diff = a.length() - b.length();            
            for(int i=0; i<diff; i++)
                b = "0"+b;
        } else if (a.length() < b.length()){
            int diff = b.length() - a.length();            
            for(int i=0; i<diff; i++)
                a = "0"+a;
        }

        // perform binary addition
        int carry = 0;
        String ans = "";

        for(int i=a.length()-1; i>=0; i--){
            int x = a.charAt(i) - '0';
            int y = b.charAt(i) - '0';
            
            String result = "";
            int sum = x + y + carry;
            if(sum == 0){
                result = "0";
                carry = 0;
            }
            else if(sum == 1){
                result = "1";
                carry = 0;
            }
            else if(sum == 2){
                result = "0";
                carry = 1;
            } else if(sum == 3){
                result = "1";
                carry = 1;
            }
         
            ans = result + ans;
        }
        
        // last carry value not accounted for in above loop
        if(carry == 1)
            ans = "1" + ans;

        return ans;
    }
}