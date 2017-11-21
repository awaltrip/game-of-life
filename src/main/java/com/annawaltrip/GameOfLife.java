package com.annawaltrip;

public class GameOfLife {
	
	public static int[][] life(int[][] field) {
		int[][] nextGen = { { 0, 0, 0 },
				{ 0, 0, 0 },
				{ 0, 0, 0 } };
		
		boolean diagonal1 = field[0][2] == 1 && field[1][1] == 1 && field[2][0] == 1;
		boolean diagonal2 = field[0][0] == 1 && field[1][1] == 1 && field[2][2] == 1;
		if (diagonal1 || diagonal2)
			nextGen[1][1] = 1;
		
		boolean block1 = field[0][0] == 1 && field[0][1] == 1 && field[1][0] == 1 && field[1][1] == 1;
		boolean block2 = field[1][1] == 1 && field[1][2] == 1 && field[2][1] == 1 && field[2][2] == 1;
		boolean tub = field[0][1] == 1 && field[1][0] == 1 && field[1][2] == 1 && field[2][1] == 1;
		if (block1 || block2 || tub) {
			nextGen = field;
		}
		
		for (int row = 0; row < field.length; row++) {
			for (int cell = 0; cell < field[row].length; cell++) {
				int above = 0, sameRow = 0, below = 0, allNeighbors = 0;
				if (cell == 0) { //left
					above = (row == 0) ? 0 : field[row-1][cell] + field[row-1][cell+1];
					sameRow = field[row][cell+1];
					below = (row == field.length-1) ? 0 : field[row+1][cell] + field[row+1][cell+1];
				} else if (cell == field[row].length-1) { //right
					above = (row == 0) ? 0 : field[row-1][cell-1] + field[row-1][cell];
					sameRow = field[row][cell-1];
					below = (row == field.length-1) ? 0 : field[row+1][cell] + field[row+1][cell-1];
				} else {
					above = (row == 0) ? 0 : field[row-1][cell-1] + field[row-1][cell] + field[row-1][cell+1];
					sameRow = field[row][cell-1] + field[row][cell+1];
					below = (row == field.length-1) ? 0 : field[row+1][cell] + field[row+1][cell-1] + field[row+1][cell+1];
				}
				allNeighbors = above + sameRow + below;
				if (allNeighbors < 2 || allNeighbors > 3) {
					nextGen[row][cell] = 0;
				} else if (allNeighbors == 3) {
					nextGen[row][cell] = 1;
				} else {
					nextGen[row][cell] = field[row][cell];
				}
			}
		}
		return nextGen;
	} 

}
