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
			boolean liveRow = field[row][0] == 1 && field[row][1] == 1 && field[row][2] == 1;
			if (liveRow) {
				nextGen[0][row] = 1; nextGen[1][row] = 1; nextGen[2][row] = 1;
			}
		}
		
		return nextGen;
	} 

}
