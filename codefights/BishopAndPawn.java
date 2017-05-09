/*
 * Source: https://codefights.com/arcade/intro/level-6/t97bpjfrMDZH8GJhi
 *
 * Problem: Given two cells on the standard chess board, determine whether 
 * they have the same color or not.
 *
 * Solution uses the mathematical knowledge that for a bishop to be able
 * capture anything, it needs a diagonal move which is only possible if
 * the diaognal(hyp) has two equal sides.
 *
 */

public class BishopAndPawn{

	public static void main(String[] args){
        System.out.println(bishopAndPawn("a1", "c3"));
        System.out.println(bishopAndPawn("a1", "h7"));
    }

    // Parses values and return whether bishop can capture pawn in one move
    public static boolean bishopAndPawn(String bishop, String pawn) {
	    int bi = bishop.charAt(0) - 'a' + 1;
	    int bj = bishop.charAt(1) - '0';

	    int pi = pawn.charAt(0) - 'a' + 1;
	    int pj = pawn.charAt(1) - '0';
	    
	    return Math.abs(pi-bi) == Math.abs(pj-bj);
	}
}