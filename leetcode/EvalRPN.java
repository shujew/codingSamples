/*
 * Source: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Usual Solution to problem uses a stack and adds integers to the stack. When it reaches
 * an operator, it pops the 2 last added integers from the stack and performs an addition,
 * substraction, multiplication or division on them. However, while using stacks runs in
 * O(n), the runtime is not satisfactory to me. Thus, I simulate the same process above
 * by using a simple array and an index which keeps track of the last integer added. This
 * reduces the runtime from 15ms to 8ms on LeetCode.
 * 
 * Time complexity: O(n)
 *
 */

public class EvalRPN {

	public static void main(String[] args){
		String[] data = {"2", "1", "+", "3", "*"};
		System.out.println(evalRPN(data));
    }

    public static int evalRPN(String[] tokens) {
        // Stack structure is replicated using an
        // array and an index integer
        int[] stack = new int[tokens.length];
        int index = -1;
        
        // Loop through tokens processing them as
        // described in header
        for(String s: tokens){
            if(s.length() == 1 && s.charAt(0) < '0'){
                int num2 = stack[index];
                int num1 = stack[--index];
                stack[index] = 0; stack[index+1] = 0;
                if(s.equals("+"))
                    stack[index] = num1+num2;
                else if (s.equals("-"))
                    stack[index] = num1-num2;
                else if (s.equals("*"))
                    stack[index] = num1*num2;
                else if (s.equals("/"))
                    stack[index] = num1/num2;
            } else
                stack[++index] = Integer.parseInt(s);
        }

        return stack[0];
    }
}

