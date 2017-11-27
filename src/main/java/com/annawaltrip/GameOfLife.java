package com.annawaltrip;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameOfLife {
	
	public static void main(String[] args) {
		System.out.println("~*~*~*~*~ CONWAY'S GAME OF LIFE ~*~*~*~*~");
		System.out.println("For this game, you will create a grid of 'cells'.");
		System.out.println("Dead cells are represented by a '0', and live cells by a '1'.");
		System.out.println("The game will then calculate the next generation of the grid, and print it for you.");
		System.out.println("You'll create the grid by entering lines of '0's and '1's. Let's get started.");
		System.out.println();
		int[][] field; int x = 0, y = 0; boolean validSize = false;
		while (!validSize) {
			Scanner input;
			try {
				input = new Scanner(System.in);
				System.out.println("How many rows would you like to make your grid? (at least 3)");
				x = input.nextInt();
				System.out.println("And how many columns? (at least 3)");
				y = input.nextInt();
				input.reset();
			} catch (InputMismatchException e) {
				x = 0; y = 0;
			}
			if (x < 3 || y < 3) {
				System.out.println("Those were not valid entries, please try again.");
			} else {
				validSize = true;
			}
		}
		System.out.println("\nYou have chosen "+x+" rows and "+y+" columns!");
		System.out.println("Now you will enter your "+x+" rows. Remember to enter "+y+" numbers in each row.\n");
		field = new int[x][y];
		try (Scanner input = new Scanner(System.in)) {
			for (int i = 0; i < x; i++) {
				System.out.println("Please enter your row #"+(i+1)+":");
				String line = "";
				while (line.equals("")) {
					line = input.nextLine();
					if (!line.matches("[0-1]+") || line.length() != y) {
						line = "";
						System.out.println("That is not a valid entry. Try row #"+(i+1)+" again:");
					}
				}
				int row[] = stringToIntArray(line);
				field[i] = row;
			}
		} 
		printNextGenFromInput(field);
	}
	
	private static int[] stringToIntArray(String row) {
		int[] intArr = new int[row.length()];
		for (int i = 0; i < row.length(); i++) {
			intArr[i] = Character.getNumericValue(row.charAt(i));
		}
		return intArr;
	}
	
	protected static void printNextGenFromInput(int[][] field) {
		System.out.println("\nYou have created this field:\n");
		for (int[] row : field) {
			for (int cell : row) {
				System.out.print(cell);
			}
			System.out.print("\n");
		}
		int[][] nextGen = life(field);
		System.out.println("\nThe Next Generation:\n");
		for (int[] row : nextGen) {
			for (int cell : row) {
				System.out.print(cell);
			}
			System.out.print("\n");
		}
	}
	
	protected static int[][] life(int[][] field) {
		int[][] nextGen = new int[field.length][field[0].length];
		for (int row = 0; row < field.length; row++) {		//each row in the field
			for (int cell = 0; cell < field[row].length; cell++) {	//and each cell in the row
				int liveNeighbors = countLiveNeighbors(field, row, cell);
				nextGen[row][cell] = determineCellFate(liveNeighbors, field[row][cell]);
			}
		}
		return nextGen;
	}
	
	private static int countLiveNeighbors(int[][] field, int row, int cell) {
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
