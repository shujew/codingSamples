/*
 * Source: https://leetcode.com/problems/01-matrix/
 * 
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 * 
 * Note1: The number of elements of the given matrix will not exceed 10,000.
 * Note2: There are at least one 0 in the given matrix.
 * Note3: The cells are adjacent in only four directions: up, down, left and right. 
 *
 * Solution initializes a new array with max values and uses two passes to find the nearest 0.
 * - The first pass (forwards) is only able to find the nearest 0 if the distance is 1 because
 *   when it accesses the array location, the only values which have been processed are the
 *   left and top one
 * - The second pass (backwards) calculates the distance from nearest 0 by using the left, right,
 *   up and down values of the index to find the closest location.
 *
 * Time complexity: O(n^2)
 *
 */

public class Matrix01 {
    public static void main(String[] args){
        int[][] matrix = {
            { 1, 0, 1, 1, 0, 0, 1, 0, 0, 1 },
            { 0, 1, 1, 0, 1, 0, 1, 0, 1, 1 },
            { 0, 0, 1, 0, 1, 0, 0, 1, 0, 0 },
            { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1 },
            { 0, 1, 0, 1, 1, 0, 0, 0, 0, 1 },
            { 0, 0, 1, 0, 1, 1, 1, 0, 1, 0 },
            { 0, 1, 0, 1, 0, 1, 0, 0, 1, 1 },
            { 1, 0, 0, 0, 1, 1, 1, 1, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 0, 1, 0 },
            { 1, 1, 1, 1, 0, 1, 0, 0, 1, 1 }
        };
        System.out.println("Input");
        printMatrix(matrix);
        System.out.println("Output");
        printMatrix(updateMatrix(matrix));
    }

    public static int[][] updateMatrix(int[][] matrix){
        int[][] ans = new int[matrix.length][matrix[0].length];		
        for(int i=0; i<matrix.length; i++)
            for(int j=0;j<matrix[i].length; j++)
                ans[i][j] = 10000;
        
        //1st Pass (Forwards)
        for(int i=0; i<matrix.length; i++){
            for(int j=0;j<matrix[i].length; j++){
                int min = 10000;
                if(matrix[i][j] == 0){
                    ans[i][j] = 0;
                } else {
                    //left
                    if(i-1>=0 && ans[i-1][j] < min)
                        min = ans[i-1][j];
                    //right
                    if(i+1<matrix.length && ans[i+1][j] == 0)
                        min = ans[i+1][j];
                    //up
                    if(j-1>=0 && ans[i][j-1] < min)
                        min = ans[i][j-1];
                    //down
                    if(j+1<matrix[i].length && ans[i][j+1] == 0)
                        min = ans[i][j+1];
                    ans[i][j] = min+1;
                }
            }
        }
        
        //2nd Pass (Backwards)
        for(int i=matrix.length-1; i>=0; i--){
            for(int j=matrix[i].length-1;j>=0; j--){
                int min = 10000;
                if(matrix[i][j] == 0){
                    ans[i][j] = 0;
                } else {
                    //left
                    if(i-1>=0 && ans[i-1][j] < min)
                        min = ans[i-1][j];
                    //right
                    if(i+1<matrix.length && ans[i+1][j] < min)
                        min = ans[i+1][j];
                    //up
                    if(j-1>=0 && ans[i][j-1] < min)
                        min = ans[i][j-1];
                    //down
                    if(j+1<matrix[i].length && ans[i][j+1] < min)
                        min = ans[i][j+1];
                    ans[i][j] = min+1;
                } 
            }
        }

        return ans;
    }
    
    public static void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}