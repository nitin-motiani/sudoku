package practice.motiani.sudoku;

import javax.swing.JFrame;



public class App 
{
    public static void main( String[] args )
    {
    	SudokuView sudokuView = new SudokuView();
    	sudokuView.setSize(600, 600);
    	sudokuView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	sudokuView.setAlwaysOnTop(true);
    	sudokuView.setVisible(true);
    }
    
   /* public static void manualTest()
    {
    	Integer[][] grid = new Integer[9][9];
    	for(int i = 0; i < 9; i++)
    		for(int j = 0; j < 9; j++)
    			grid[i][j] = -1;
    	
    	grid[0][0] = 6;
    	grid[0][2] = 5;
    	grid[0][4] = 1;
    	grid[0][7] = 8;
    	grid[1][0] = 9;
    	grid[1][2] = 4;
    	grid[1][3] = 7;
    	grid[1][4] = 2;
    	grid[1][8] = 6;
    	grid[2][2] = 7;
    	grid[2][4] = 5;
    	grid[3][0] = 5;
    	grid[3][3] = 2;
    	grid[3][4] = 8;
    	grid[3][7] = 4;
    	grid[4][0] = 4;
    	grid[4][1] = 6;
    	grid[4][2] = 8;
    	grid[4][6] = 5;
    	grid[4][7] = 2;
    	grid[4][8] = 1;
    	grid[5][1] = 7;
    	grid[5][4] = 4;
    	grid[5][5] = 6;
    	grid[5][8] = 3;
    	grid[6][4] = 6;
    	grid[6][6] = 9;
    	grid[7][0] = 2;
    	grid[7][4] = 7;
    	grid[7][5] = 9;
    	grid[7][6] = 4;
    	grid[7][8] = 8;
    	grid[8][1] = 8;
    	grid[8][4] = 3;
    	grid[8][6] = 1;
    	grid[8][8] = 2;
    	
    	Sudoku sudoku = new Sudoku(grid);
    	
    	//Sudoku sudoku2 = new Sudoku(sudoku);
    	//sudoku2.printGrid();
    	
    	Scanner scanner = new Scanner(System.in);
    	for(int i = 0; i < 9; i++)
    	{
    		for(int j = 0; j < 9; j++)
    		{
    			grid[i][j] = scanner.nextInt();
    		}
    	}
    	scanner.close();
    	
    	Sudoku sudoku = new Sudoku(grid);
    	sudoku.play();

    }*/
}
