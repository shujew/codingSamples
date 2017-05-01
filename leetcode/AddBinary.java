/*
 * Source: https://leetcode.com/problems/add-binary/
 * 
 * Given two binary strings, return their sum (also a binary string).
 *
 */

public class AddBinary {
    public AddBinary(){
        String a = "100101";
        String b = "10101";
        System.out.println(addBinary(a,b));
    }
    
    public String addBinary(String a, String b) {
        //pre-process(a and b should be of same length)
        if(a.length() > b.length()){
            int diff = a.length() - b.length();            
            for(int i=0; i<diff; i++)
                b = "0"+b;
        } else if (a.length() < b.length()){
            int diff = b.length() - a.length();            
            for(int i=0; i<diff; i++)
                a = "0"+a;
        }

        //perform addition
        int carry = 0;
        String ans = "";
        for(int i=a.length()-1;i>=0;i--){
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
        
        if(carry == 1)
            ans = "1" + ans;

        return ans;
    }
}