/*
 * Source: https://leetcode.com/problems/divide-two-integers/
 * 
 * Divide two integers without using multiplication, division and mod operator.
 *
 * If it is overflow, return MAX_INT.
 *
 * Solution simulates the process of long division. In the case that both dividend and 
 * divisor are multiples of 2, they are divided by 2 using bit shifting to optimize the
 * runtime. Afterwards, the sign of the answer is calculated and long division is
 * performed with the magnitude (+ve value) of the dividend and divisor
 * 
 * Time complexity: O(n)
 *
 */

public class DivideTwoIntegers {

	public static void main(String[] args){
		System.out.println(divide(400, 4));
		System.out.println(divide(2147483647, 2));
   		System.out.println(divide(-2147483648, -1017100424));
   		System.out.println(divide(-2147483648, 1));
    }

    public static int divide(int dividend, int divisor){
		// Checking for base cases
		if(divisor == 0)
            throw new ArithmeticException("Divisor should be greater than 0");
		if(divisor == dividend)
			return 1;
		if(dividend == 0)
            return 0;
        if(divisor == 1)
        	return dividend;

        // Reduce dividend and divisor using a right bit shift if they are
        // multiples of 2 and check whether divisor has been reduced to 1 or 2
        while(divisibleByTwo(dividend) && divisibleByTwo(divisor)){
        	dividend = dividend >> 1;
        	divisor = divisor >> 1;
        }
        if(divisor == 1)
        	return dividend;
        if(divisor == 2)
        	return dividend >> 1;

        // Calculate sign of answer
		boolean isNegative = false;
		if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0))
            isNegative = true;

        // Convert divisor and dividend to the respective magnitude if needed.
        // Strings are used to avoid problems with Integer.MIN_VALUE since
        // |Integer.MIN_VALUE| > |Integer.MAX_VALUE|
       	String strDividend = String.valueOf(dividend);
       	String strDivisor = String.valueOf(divisor);
       	if(dividend < 0)
       		strDividend = strDividend.substring(1);
       	if(divisor < 0)
       		strDivisor = strDivisor.substring(1);

       	// Calculate result through long division if needed and return
       	// the answer with the correct sign
       	int result = 0;
       	if(strDivisor.equals("1"))
       		result = dividend;
       	else if(strDivisor.equals(strDividend))
       		result = 1;
       	else
       		result = longDivision(strDividend, strDivisor);

       	return(setSign(result, isNegative));
    }

    public static int longDivision(String strDividend, String strDivisor){
    	
    	// Handle edge case where divisor or dividend == Integer.MIN_VALUE
    	String minIntValue = String.valueOf(Integer.MIN_VALUE).substring(1);
    	if(strDivisor.equals(minIntValue))
    		return 0;
    	else if(!strDividend.equals(minIntValue))
    		if(Integer.valueOf(strDivisor) > Integer.valueOf(strDividend))
		    	return 0;

    	String result = "";
    	String remainder = "";
    	int divisor = Integer.valueOf(strDivisor);

    	for(int i=0; i<strDividend.length(); i++){
    		int quotient = 0;
    		int digit = strDividend.charAt(i) - '0';
    		
    		remainder += digit;
    		int currentRemainder;
    		if(remainder.equals(minIntValue))
    			currentRemainder = Integer.MAX_VALUE;
    		else 
				currentRemainder = Integer.valueOf(remainder);

    		while(currentRemainder >= divisor){
	    		currentRemainder -= divisor;
	    		quotient++;
	    	}

	    	if(remainder.equals(minIntValue)){
	    		currentRemainder++;
	    		if(currentRemainder >= divisor){
	    			currentRemainder -= divisor;
	    			quotient++;
	    		}
	    	}

	    	result += quotient;
	    	int integerRemainder; 
			if(remainder.equals(minIntValue))
	    		integerRemainder = Integer.valueOf(Integer.MAX_VALUE) - naiveMultiplier(quotient, divisor) + 1;
	    	else
	    		integerRemainder = Integer.valueOf(remainder) - naiveMultiplier(quotient, divisor);
	    	remainder = String.valueOf(integerRemainder);
    	}

    	return Integer.valueOf(result);
    }

    public static int naiveMultiplier(int a, int b){
    	// Multiplies answer by adding the biggest value
    	// to itself the smallest value number of times
    	int ans = 0;
    	if(a > b){
    		while(b > 0){
	    		b--;
	    		ans += a;
	    	}
    	} else {
    		while(a > 0){
	    		a--;
	    		ans += b;
	    	}
    	}
    	return ans;
    }

    public static int setSign(int n, boolean isNegative){
    	// Ensures that n has the correct sign
    	if((isNegative && n > 0) || (!isNegative && n < 0)){
    		if(n == Integer.MIN_VALUE)
    			return Integer.MAX_VALUE;
    		else
    			return 0-n;
    	}
    	else
    		return n;
    }


    public static boolean divisibleByTwo(int n){
    	// Uses a bit mask to return whether n is
    	// divisible by 2
        int mask = 1;
        n = n & mask;
        return n == 0;
    }
}





