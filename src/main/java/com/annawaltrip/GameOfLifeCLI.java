package com.annawaltrip;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameOfLifeCLI {
	
	public static void main(String[] args) {
		printHeader();
		int[][] grid; int x = 0, y = 0; 
		boolean validSize = false;  
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
		System.out.println("Now you will enter your "+x+" rows. Remember to enter "+y+" numbers in each row, and only enter '0's or '1's.\n");
		grid = new int[x][y];
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
				grid[i] = row;
			}
		} 
		printNextGenFromInput(grid);
	}
	
	protected static void printNextGenFromInput(int[][] grid) {
		System.out.println("\nYou have created this grid:\n");
		for (int[] row : grid) {
			for (int cell : row) {
				System.out.print(cell);
			}
			System.out.print("\n");
		}
		int[][] nextGen = GameOfLife.play(grid);
		System.out.println("\nThe next generation:\n");
		for (int[] row : nextGen) {
			for (int cell : row) {
				System.out.print(cell);
			}
			System.out.print("\n");
		}
	}
	
	private static int[] stringToIntArray(String row) {
		int[] intArr = new int[row.length()];
		for (int i = 0; i < row.length(); i++) {
			intArr[i] = Character.getNumericValue(row.charAt(i));
		}
		return intArr;
	}
	
	private static void printHeader() {
		System.out.println();
		System.out.println("~*~*~*~*~ CONWAY'S GAME OF LIFE ~*~*~*~*~");
		System.out.println("For this game, you will create a grid of 'cells'.");
		System.out.println("Dead cells are represented by a '0', and live cells by a '1'.");
		System.out.println("The game will then calculate the next generation of the grid, and display it for you.");
		System.out.println("You'll create the grid by entering lines of '0's and '1's. Let's get started.");
		System.out.println();
	}

}
