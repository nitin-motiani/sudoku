package practice.motiani.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Sudoku {
	
	private static Integer[] boxValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	private Integer[][] grid;
	private ArrayList<TreeSet<Integer>> rowFilters = new ArrayList<TreeSet<Integer>>(9);; 
	private ArrayList<TreeSet<Integer>> columnFilters = new ArrayList<TreeSet<Integer>>(9);
	private ArrayList<TreeSet<Integer>> shortSquareFilters = new ArrayList<TreeSet<Integer>>(9);
	
	private void copyGrid(Integer[][] grid)
	{
		this.grid = new Integer[grid.length][];
		for(int i = 0; i < grid.length; i++)
		{
			this.grid[i] = Arrays.copyOf(grid[i], grid[i].length);
		}
	}
	
	private int getShortSquareIndex(int row, int column)
	{
		return ( row - row % 3 + column / 3);
	}
	
	private void setFilters() throws Exception
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(grid[i][j] == -1)
					continue;
				
				if(!rowFilters.get(i).contains(grid[i][j]) 
					|| !columnFilters.get(j).contains(grid[i][j])
					|| !shortSquareFilters.get(getShortSquareIndex(i, j)).contains(grid[i][j])
				)
				{
					throw new Exception("Invalid input. Puzzle has duplicates.");
				}
				
				rowFilters.get(i).remove(grid[i][j]);
				columnFilters.get(j).remove(grid[i][j]);
				shortSquareFilters.get(getShortSquareIndex(i, j)).remove(grid[i][j]);
			}
		}
	}
	
	public Sudoku(Integer[][] grid) throws Exception {
		copyGrid(grid);
		

		for(int i = 0; i < 9; i++)
		{
			rowFilters.add(new TreeSet<Integer>(Arrays.asList(boxValues)));
			columnFilters.add(new TreeSet<Integer>(Arrays.asList(boxValues)));
			shortSquareFilters.add(new TreeSet<Integer>(Arrays.asList(boxValues)));
		}
		
		setFilters();
	}
	
	public Sudoku(Sudoku that)
	{
		copyGrid(that.grid);
		
		for(int i = 0; i < 9; i++)
		{
			rowFilters.add(new TreeSet<Integer>(that.columnFilters.get(i)));	
			columnFilters.add(new TreeSet<Integer>(that.columnFilters.get(i)));
			shortSquareFilters.add(new TreeSet<Integer>(that.shortSquareFilters.get(i)));
		}
	}
	
	public Integer[][] getGrid()
	{
		return grid;
	}
	
	private void move(int row, int column, int value) throws Exception
	{
		if(grid[row][column] != -1)
		{
			throw new Exception("Value already set in the cell");
		}
		
		grid[row][column] = value;
		rowFilters.get(row).remove(value);
		columnFilters.get(column).remove(value);
		shortSquareFilters.get(getShortSquareIndex(row, column)).remove(value);
	}
	
	private void undoMove(int row, int column) throws Exception
	{
		if(grid[row][column] == -1)
		{
			throw new Exception("No value in the cell");
		}
		
		int value = grid[row][column];
		rowFilters.get(row).add(value);
		columnFilters.get(column).add(value);
		shortSquareFilters.get(getShortSquareIndex(row, column)).add(value);
		
		grid[row][column] = -1;
	}
	
	private TreeSet<Integer> getMoves(int row, int column)
	{
		TreeSet<Integer> validOptions = new TreeSet<Integer>(rowFilters.get(row));
		validOptions.retainAll(columnFilters.get(column));
		validOptions.retainAll(shortSquareFilters.get(getShortSquareIndex(row, column)));
		
		return validOptions;
	}
	
	private boolean isFullyFilled()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(grid[i][j] == -1)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean isValid()
	{
		if(!isFullyFilled())
			return false;
		for(int i = 0; i < 9; i++)
		{
			if(!rowFilters.get(i).isEmpty() || !columnFilters.get(i).isEmpty() || !shortSquareFilters.get(i).isEmpty())
				return false;
		}
		
		return true;
	}
	
	private boolean playHelper() throws Exception
	{
		if(isValid())
			return true;

		TreeSet<Integer> minPossibleMoves = null;
		int minPossibleMoveRow = -1, minPossibleMoveColumn = -1;
		
		playloop:
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(grid[i][j] != -1)
					continue;
				
				TreeSet<Integer> possibleMoves = getMoves(i, j);
				if(minPossibleMoves == null || minPossibleMoves.size() > possibleMoves.size())
				{
					minPossibleMoves = possibleMoves;
					minPossibleMoveRow = i;
					minPossibleMoveColumn = j;
				}
				
				if(minPossibleMoves.size() <= 1)
					break playloop;
			}
		}
		
		if(minPossibleMoves == null || minPossibleMoves.size() == 0)
			return false;
		
		for(Integer possibleMove : minPossibleMoves)
		{
			move(minPossibleMoveRow, minPossibleMoveColumn, possibleMove);
			if(playHelper())
				return true;
			undoMove(minPossibleMoveRow, minPossibleMoveColumn);
		}
		
		return false;
	}
	
	/*public void printGrid()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(grid[i][j] + " ");
			}
			System.out.println("\n");
		}
	}*/
	
	public boolean play()
	{
		try {
			if(playHelper())
			{
				return true;
				//System.out.println("Final solution");
				//printGrid();
			}
			else
			{
				//System.out.println("No solution");
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
}
