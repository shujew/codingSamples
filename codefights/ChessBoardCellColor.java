/*
 * Source: https://codefights.com/arcade/intro/level-6/t97bpjfrMDZH8GJhi
 *
 * Problem: Given two cells on the standard chess board, determine whether 
 * they have the same color or not.
 *
 * Solution generates a model of the board with its alternative color represented
 * as the boolean values true and false. Then it simply compares whether the
 * values(colors) are similar
 *
 * Time complexity: O(n^2)
 *
 */

public class ChessBoardCellColor{

	public static void main(String[] args){
        System.out.println(chessBoardCellColor("A1", "H3"));
        System.out.println(chessBoardCellColor("A1", "B2"));
    }

    // Parses and return whether two cells are of the same color
    public static boolean chessBoardCellColor(String cell1, String cell2) {
	    boolean[][] board = generateBoard();
	        
	    int i1 = Integer.valueOf(cell1.charAt(0) - 'A');
	    int j1 = Integer.valueOf(cell1.charAt(1) - '1');
	    int i2 = Integer.valueOf(cell2.charAt(0) - 'A');
	    int j2 = Integer.valueOf(cell2.charAt(1) - '1');
	    
	    return board[i1][j1] == board[i2][j2];
	}

    // Generates board with alternative colors
    public static boolean[][] generateBoard(){
	    boolean[][] board = new boolean[8][8];
	    boolean start = true;
	    
	    for(int i=0; i<8; i++){
	        board[i][0] = start;
	        for(int j=1; j<8; j++){
	            board[i][j] = !board[i][j-1];
	        }
	        start = !start;
	    }

	    return board;
	}
}