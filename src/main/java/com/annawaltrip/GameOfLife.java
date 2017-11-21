package com.annawaltrip;

public class GameOfLife {
	
	public static int[][] life(int[][] field) {
		int[][] nextGen = new int[field.length][field[0].length];
		for (int row = 0; row < field.length; row++) {
			for (int cell = 0; cell < field[row].length; cell++) {
				int liveNeighbors = checkAllNeighbors(row, cell, field);
				nextGen[row][cell] = determineCellFate(liveNeighbors, field[row][cell]);
			}
		}
		return nextGen;
	} 
	
	private static int checkAllNeighbors(int row, int cell, int[][] field) {
		int above = 0, sameRow = 0, below = 0;
		if (cell == 0) { 	//left column
			above = (row == 0) ? 0 : field[row-1][cell] + field[row-1][cell+1];
			sameRow = field[row][cell+1];
			below = (row == field.length-1) ? 0 : field[row+1][cell] + field[row+1][cell+1];
		} else if (cell == field[row].length-1) { 	//right column
			above = (row == 0) ? 0 : field[row-1][cell-1] + field[row-1][cell];
			sameRow = field[row][cell-1];
			below = (row == field.length-1) ? 0 : field[row+1][cell] + field[row+1][cell-1];
		} else {
			above = (row == 0) ? 0 : field[row-1][cell-1] + field[row-1][cell] + field[row-1][cell+1];
			sameRow = field[row][cell-1] + field[row][cell+1];
			below = (row == field.length-1) ? 0 : field[row+1][cell] + field[row+1][cell-1] + field[row+1][cell+1];
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
