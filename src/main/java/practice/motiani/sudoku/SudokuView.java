package practice.motiani.sudoku;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SudokuView extends JFrame implements ActionListener{
	
	private JPanel panel = new JPanel(new GridLayout(3, 3));
	private JTextField[][] textFields = new JTextField[9][9];
	
	private final static Color inputCellTextColor = Color.BLUE;
	private final static Color solvedCellTextColor = Color.BLACK;

	public SudokuView()
	{
		//JTextField[][] textFields = new JTextField[9][9];
		
		JPanel[][] subPanels = new JPanel[3][3];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				subPanels[i][j] = new JPanel(new GridLayout(3, 3));
				subPanels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			}
		}
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				textFields[i][j] = new JTextField("");
				textFields[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				textFields[i][j].setHorizontalAlignment(JTextField.CENTER);
				textFields[i][j].setForeground(inputCellTextColor);
				
				subPanels[i / 3][j / 3].add(textFields[i][j]);
			}
		}
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				panel.add(subPanels[i][j]);
			}
		}
		
		add(panel, BorderLayout.PAGE_START);
		
		JButton button = new JButton("Solve");
		button.addActionListener(this);
		add(button, BorderLayout.PAGE_END);
	}
	
	/*
	public static void main(String[] args)
	{
		SudokuView sudokuView = new SudokuView();
		sudokuView.setSize(600, 600);
		//sudokuView.pack();
		sudokuView.setVisible(true);
		sudokuView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sudokuView.setAlwaysOnTop(true);
	}*/
	
	private Integer[][] createGrid()
	{
		Integer[][] grid = new Integer[9][9];
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				String text = textFields[i][j].getText();
				if(text.length() == 0)
					grid[i][j] = -1;
				else
					grid[i][j] = Integer.parseInt(text);
			}
		}
		
		return grid;
	}
	
	private void setTextFields(Integer[][] grid)
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				String text;
				if(grid[i][j] == -1)
					text = "";
				else
					text = grid[i][j].toString();
				
				// Show the values entered by the solver in different color
				if(!text.equals(textFields[i][j].getText()))
					textFields[i][j].setForeground(solvedCellTextColor);
				
				textFields[i][j].setText(text);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		Integer[][] grid = createGrid();
		
		Sudoku sudoku;
		try {
			sudoku = new Sudoku(grid);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "Invalid. Duplicates in the puzzle");
			return;
		}
		boolean validResult = sudoku.play();
		if(validResult)
			setTextFields(sudoku.getGrid());
		else
			JOptionPane.showMessageDialog(this, "Incorrect puzzle. No solution found");
	}
}
