package hw3;

import java.util.ArrayList;
import java.util.Random;
import api.Direction;
import api.Move;
import api.TilePosition;

/**
 * Utility class containing some elements of the basic logic for performing
 * moves in a game of "Threes".
 * @author GeonHee Cho
 */
public class GameUtil {

	/**
	 * Constructor does nothing, since this object is stateless.
	 * 
	 */
	public GameUtil() {
		// do nothing
	}

	/**
	 * Returns the result of merging the two given tile values, or zero if they
	 * can't be merged. The rules are: a 1 can be merged with a 2, and two
	 * values greater than 2 can be merged if they match. The result of a merge
	 * is always the sum of the tile values.
	 * 
	 * @param a
	 *            given tile value
	 * @param b
	 *            given tile value
	 * @return result of merging the two values, or zero if no merge is possible
	 */
	public int mergeValues(int a, int b) {
		if (a > 0 && b > 0 && (a + b == 3) || (a >= 3 && b == a)) {
			return a + b;
		} else {
			return 0;
		}
	}

	/**
	 * Returns the score for a single tile value. Tiles with value less than 3
	 * have score zero. All other values result from starting with value 3 and
	 * doubling N times, for some N; the score is 3 to the power N + 1. For
	 * example: the value 48 is obtained from 3 by doubling N = 4 times (48 / 3
	 * is 16, which is 2 to the 4th), so the score is 3 to the power 5, or 243.
	 * 
	 * @param value
	 *            tile value for which to compute the score
	 * @return score for the given gile value
	 */
	public int getScoreForValue(int value) {
		int temp = 1;
		if ( value < 3 ) //if it is smaller han 3, return 0
		{ 
			return 0;
		}
		else  // otherwise while n is not 1, then n 
		{
			int n = value/3;
			
			while ( n != 1 )
			{
				n /= 2;
				temp ++;
			}
			return (int) Math.pow(3,temp);
		}
	}

	/**
	 * Returns a new size x size array with two nonzero cells. The nonzero cells
	 * consist of a 1 and a 2, placed randomly in the grid using the given
	 * Random instance.
	 * 
	 * @param size
	 *            width and height of the new array
	 * @param rand
	 *            random number generator to use for positioning the nonzero
	 *            cells
	 * @return new size x size array
	 */
	public int[][] initializeNewGrid(int size, Random rand) {
		int[][] grid = new int[size][size];
		int numCells = size * size;

		// To select two distinct cells, think of the numCells cells as ordered
		// left to right within rows, with the rows ordered top to bottom.
		// First select two distinct integers between 0 and numCells
		int first = rand.nextInt(numCells);
		int second = rand.nextInt(numCells - 1);
		if (second >= first) {
			second += 1;
		}

		// Then, divide by size to get the row, mod by size to get column,
		// put a 1 in the first and a 2 in the other
		grid[first / size][first % size] = 1;
		grid[second / size][second % size] = 2;
		return grid;
	}

	/**
	 * Returns the total score for the given grid. The grid is not modified.
	 * 
	 * @param grid
	 *            given grid
	 * @return sum of scores for the values in the grid
	 */
	public int calculateTotalScore(int[][] grid) {
		int total = 0;
		for (int row = 0; row < grid.length; ++row) {
			for (int col = 0; col < grid[0].length; ++col) {
				total += getScoreForValue(grid[row][col]);
			}
		}
		return total;
	}

	/**
	 * Makes a copy of the given 2D array. The array must be nonempty and
	 * rectangular.
	 * 
	 * @param grid
	 *            given 2D array to copy
	 * @return copy of the given array
	 */
	public int[][] copyGrid(int[][] grid) {
		int[][] ret = new int[grid.length][grid[0].length]; //copy the grid
		
		for (int row = 0; row < grid.length; ++row) {
			for (int col = 0; col < grid[0].length; ++col) {
				ret[row][col] = grid[row][col];
			}
		}
		return ret;
	}

	/**
	 * Generate a new tile value using the given instance of Random. Values are
	 * generated such that there are 40% 1's, 40% 2's, 10% 3's, and 10% 6's.
	 * 
	 * @param rand
	 *            random number generator to use
	 * @return the value 1, 2, 3, or 6 with the specified probability
	 */
	public int generateRandomTileValue(Random rand) {
		int r = rand.nextInt(99) + 1;
		
		if (r <= 40) //40% of 1
		{
			return 1;
		} 
		else if (r <= 80)  //40% of 2
		{
			return 2;
		} 
		else if (r <= 90) // 10% of 3
		{
			return 3;
		} 
		
		else 
		{
			return 6; //10% of 6
		}
	}

	/**
	 * Generates a position for a new tile. The new position is on the side of
	 * the grid opposite that of the previous move and is randomly selected from
	 * available positions in the given grid. The value of the tile is zero
	 * (typically filled in later by an associated Game instance). The given
	 * grid is not modified. If <code>lastMove</code> is null, this method
	 * returns null.
	 * 
	 * @param grid
	 *            given square array
	 * @param rand
	 *            random number generator to use
	 * @param lastMove
	 *            given direction
	 * @return new tile position
	 */
	public TilePosition generateRandomTilePosition(int[][] grid, Random rand, Direction lastMove) 
	{
		int column = 0, row = 0; //make the column and row
		int randVal = generateRandomTileValue(rand); //random value
		boolean notChanged=true;
		if (lastMove == api.Direction.LEFT) 
		{
			while(notChanged)
			{
			column = grid.length - 1; // the right most column of the grid
			row = rand.nextInt(grid.length);// randomly select available tile (using row)
				
				if(grid[row][column] == 0)
				{
					notChanged = false;
					return new TilePosition(row, column, randVal);
				}	
			}
		} 
		else if (lastMove == api.Direction.RIGHT)
		{
			while(notChanged){
				// the left most column of the grid
				column = 0;
				// randomly select available tile (using row)
				row = rand.nextInt(grid.length);
				if(grid[row][column]== 0)
				{
						notChanged=false;
						return new TilePosition(row, column, randVal);
				}
			}

		} 
		else if (lastMove == api.Direction.UP)
		{	
			while(notChanged)
			{
				row = grid.length-1;
				// randomly select available tile (using column)
				column = rand.nextInt(grid.length);
				if(grid[row][column]== 0)
				{
						notChanged=false;
						return new TilePosition(row, column, randVal);
				}
			}

		} 
		else if (lastMove == api.Direction.DOWN)
		{
			// DOWN
			while(notChanged)
			{
				row = 0;
				// randomly select available tile (using column)
				column = rand.nextInt(grid.length);
				if(grid[row][column]== 0)
				{
						notChanged=false;
						return new TilePosition(row, column, randVal);
				}
			}
		}
		return null;
	}

	/**
	 * Shifts the array elements to the left according to the rules of the
	 * Threes game. This method only operates on a one-dimensional array of
	 * integers representing the tile values in one row or column. The Game
	 * class can use this method to shift a row or column in any direction by
	 * copying that row or column, either forward or backward, into a temporary
	 * one-dimensional array to be passed to this method. The rules are that if
	 * there is a pair of adjacent cells that can be merged, and has no empty
	 * (zero) cells to its left, then the leftmost such pair of cells is merged
	 * and everything to its right is shifted left by one cell. Otherwise, if
	 * there is an empty cell, then everything to the right of the leftmost
	 * empty cell is shifted left by one cell. Otherwise, the array is
	 * unmodified and an empty list is returned.
	 * <p>
	 * The new value for a pair of merged cells is determined by the method
	 * <code>mergeValues</code>. The method returns a list of Move objects
	 * representing the moved cells. All returned Move objects will have
	 * unspecified row/column and direction (typically filled in later by the
	 * associated Game instance). The list is in no particular order.
	 * 
	 * @param arr
	 *            array to be shifted
	 * @return list of all moves and/or merges performed in the shift
	 */
	public ArrayList<Move> shiftArray(int[] arr)
	{
		ArrayList<Move> temp = new ArrayList<>();
		for (int i=0; i < arr.length-1; i++ ) //check the arr array
		{
			if (mergeValues(arr[i], arr[i+1]) != 0 ) // merge part  
			{
				Move move = new Move(i+1, i, arr[i+1], arr[i], mergeValues(arr[i],arr[i+1]));
				arr[i]= mergeValues(arr[i],arr[i+1]);
				arr[i+1] =0;
				temp.add(move);
			}
			else if(arr[i]==0 && arr[i+1] != 0) //non merge part. 
			 {
				//if it is zero, it will shift it right to left
				Move move= new Move(i+1, i, arr[i+1]);
				arr[i] = arr[i + 1];
				arr[i + 1] = 0;				
				temp.add(move);	
			 }
		// "shiftArray", only the non-zero tiles move to their left if needed. 
		//In other words, the ArrayList<Move> should not contain any Move which has a tile-value 0. Sorry, I could not solve that.
		}
		return temp;
	}
}
