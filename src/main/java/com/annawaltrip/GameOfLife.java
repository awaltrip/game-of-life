package com.annawaltrip;

public class GameOfLife {
	
	protected static int[][] play(int[][] grid) {
		int[][] nextGen = new int[grid.length][grid[0].length];
		for (int row = 0; row < grid.length; row++) {		//each row in the grid
			for (int cell = 0; cell < grid[row].length; cell++) {	//and each cell in the row
				int liveNeighbors = countLiveNeighbors(grid, row, cell);
				nextGen[row][cell] = determineCellFate(liveNeighbors, grid[row][cell]);
			}
		}
		return nextGen;
	}
	
	private static int countLiveNeighbors(int[][] grid, int row, int cell) {
		int above = 0, sameRow = 0, below = 0;
		if (cell == 0) { 	//left column
			above = (row == 0) ? 0 : grid[row-1][cell] + grid[row-1][cell+1];
			sameRow = grid[row][cell+1];
			below = (row == grid.length-1) ? 0 : grid[row+1][cell] + grid[row+1][cell+1];
		} else if (cell == grid[row].length-1) { 	//right column
			above = (row == 0) ? 0 : grid[row-1][cell-1] + grid[row-1][cell];
			sameRow = grid[row][cell-1];
			below = (row == grid.length-1) ? 0 : grid[row+1][cell] + grid[row+1][cell-1];
		} else {
			above = (row == 0) ? 0 : grid[row-1][cell-1] + grid[row-1][cell] + grid[row-1][cell+1];
			sameRow = grid[row][cell-1] + grid[row][cell+1];
			below = (row == grid.length-1) ? 0 : grid[row+1][cell] + grid[row+1][cell-1] + grid[row+1][cell+1];
		}
		return above + sameRow + below;
	}
	
	private static int determineCellFate(int liveNeighbors, int oldCell) {
		int newCell;
		if (liveNeighbors < 2 || liveNeighbors > 3) {
			newCell = 0;
		} else if (liveNeighbors == 3) {
			newCell = 1;
		} else {
			newCell = oldCell;
		}
		return newCell;
	}

}
