/*
 * Source: https://codefights.com/arcade/intro/level-2/yuGuHvcCaFCKk56rJ
 *
 * Problem: Below we will define an n-interesting polygon. Your task is to 
 * find the area of a polygon for a given n.
 *
 * A 1-interesting polygon is just a square with a side of length 1. An 
 * n-interesting polygon is obtained by taking the (n-1)-interesting polygon 
 * and appending 1-interesting polygons to its rim, side by side. 
 *
 * I used math (and picture provided on website) to figure out that the area
 * of an n-shaped interesting polygon is the area of the (n-1)-shaped interesting
 * polygon and adding (2*(2*n-2)) to it. Thus solution takes advantage of this
 * and solves the problem using recursion.
 * 
 * Time complexity: O(n)
 *
 */

public class InterestingPolygon{

	public static void main(String[] args){
        System.out.println(areaShape(2));
        System.out.println(areaShape(5));
    }

    // Uses recursion to solve problem
    public static int areaShape(int n) {
	    if(n == 1)
	        return 1;

	    return areaShape(n-1) + (2*(2*n-2));
	}

}