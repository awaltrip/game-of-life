package com.annawaltrip;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

public class GameOfLifeTest {
	
	private ByteArrayOutputStream output;
	
	@Before
	public void setup() {
		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
	}
	
	@After
	public void cleanUp() {
	    System.setOut(null);
	}
	
	@Test
	public void oneCellAloneDies() {
		int[][] grid = { { 0, 0, 0 },
				{ 0, 1, 0 },
				{ 0, 0, 0 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 0, 0, 0 },
				{ 0, 0, 0 } };
		
		assertArrayEquals(expected, GameOfLife.play(grid));
	}
	
	@Test
	public void twoCellsAloneDie() {
		int[][] grid1 = { { 0, 0, 0 },
				{ 1, 1, 0 },
				{ 0, 0, 0 } };
		
		int[][] grid2 = { { 1, 0, 0 },
				{ 0, 1, 0 },
				{ 0, 0, 0 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 0, 0, 0 },
				{ 0, 0, 0 } };
		
		assertArrayEquals(expected, GameOfLife.play(grid1));
		assertArrayEquals(expected, GameOfLife.play(grid2));
	}
	
	@Test
	public void fourLoneCellsDie() {
		int[][] grid = { { 1, 0, 1 },
				{ 0, 0, 0 },
				{ 1, 0, 1 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 0, 0, 0 },
				{ 0, 0, 0 } };
		
		assertArrayEquals(expected, GameOfLife.play(grid));
	}
	
	@Test
	public void threeDiagonalCellsBecomeOne() {
		int[][] grid1 = { { 1, 0, 0 },
				{ 0, 1, 0 },
				{ 0, 0, 1 } };
		
		int[][] grid2 = { { 0, 0, 1 },
				{ 0, 1, 0 },
				{ 1, 0, 0 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 0, 1, 0 },
				{ 0, 0, 0 } };
		
		assertArrayEquals(expected, GameOfLife.play(grid1));
		assertArrayEquals(expected, GameOfLife.play(grid2));
	}
	
	@Test
	public void blockPatternRemainsTheSame() {
		int[][] grid1 = { { 1, 1, 0 },
				{ 1, 1, 0 },
				{ 0, 0, 0 } };
		
		int[][] grid2 = { { 0, 0, 0 },
				{ 0, 1, 1 },
				{ 0, 1, 1 } };
		
		int[][] expected1 = grid1;
		int[][] expected2 = grid2;
		
		assertArrayEquals(expected1, GameOfLife.play(grid1));
		assertArrayEquals(expected2, GameOfLife.play(grid2));
	}
	
	@Test
	public void tubPatternRemainsTheSame() {
		int[][] grid = { { 0, 1, 0 },
				{ 1, 0, 1 },
				{ 0, 1, 0 } };
		
		int[][] expected = grid;
		
		assertArrayEquals(expected, GameOfLife.play(grid));
	}
	
	@Test
	public void horizontalLineOfThreeCellsRotates() {
		int[][] grid = { { 0, 0, 0 },
				{ 1, 1, 1 },
				{ 0, 0, 0 } };
		
		int[][] expected = { { 0, 1, 0 },
				{ 0, 1, 0 },
				{ 0, 1, 0 } };
		
		assertArrayEquals(expected, GameOfLife.play(grid));
	}
	
	@Test
	public void verticalLineOfThreeCellsRotates() {
		int[][] grid = { { 0, 1, 0 },
				{ 0, 1, 0 },
				{ 0, 1, 0 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 1, 1, 1 },
				{ 0, 0, 0 } };
		
		assertArrayEquals(expected, GameOfLife.play(grid));
	}
	
	@Test
	public void largerGridWorksWithBlockPattern() {
		int[][] grid = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		int[][] expected = grid;
		
		assertArrayEquals(expected, GameOfLife.play(grid));
	}
	
	@Test
	public void beaconOscillatesPeriod2() {
		int[][] grid = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		int[][] gen1 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },};
		
		int[][] gen2 = grid;
		
		assertArrayEquals(gen1, GameOfLife.play(grid));
		assertArrayEquals(gen2, GameOfLife.play(gen1));
	}
	
	@Test
	public void gliderGlides() {
		int[][] grid = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0 },
				{ 0, 0, 1, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		int[][] gen1 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		int[][] gen2 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0 },
				{ 0, 0, 1, 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		int[][] gen3 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		int[][] gen4 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		assertArrayEquals(gen1, GameOfLife.play(grid));
		assertArrayEquals(gen2, GameOfLife.play(gen1));
		assertArrayEquals(gen3, GameOfLife.play(gen2));
		assertArrayEquals(gen4, GameOfLife.play(gen3));
	}
	
	@Test
	public void printsNextGenFromInputBlockPattern() {
		int[][] grid = { { 1, 1, 0 },
				{ 1, 1, 0 }, 
				{ 0, 0, 0 } };
		
		String expected = "\nYou have created this grid:\n\n" +
							"110\n" +
							"110\n" +
							"000\n\n" +
							"The Next Generation:\n\n" +
							"110\n" +
							"110\n" +
							"000\n";
		
		GameOfLifeCLI.printNextGenFromInput(grid);
		
		assertEquals(expected, output.toString());
	}
	
	@Test
	public void printsNextGenFromInputBeacon() {
		int[][] grid = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		String expected = "\nYou have created this grid:\n\n" +
							"00000000\n" +
							"00110000\n" +
							"00100000\n" +
							"00000100\n" +
							"00001100\n" +
							"00000000\n\n" +
							"The Next Generation:\n\n" +
							"00000000\n" +
							"00110000\n" +
							"00110000\n" +
							"00001100\n" +
							"00001100\n" +
							"00000000\n";
		
		GameOfLifeCLI.printNextGenFromInput(grid);
		
		assertEquals(expected, output.toString());
	}
	
	@Test
	public void printsNextGenFromInputExample() {
		int[][] grid = { { 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 } };
		
		String expected = "\nYou have created this grid:\n\n" +
							"00000010\n" +
							"11100010\n" +
							"00000010\n" +
							"00000000\n" +
							"00011000\n" +
							"00011000\n\n" +
							"The Next Generation:\n\n" +
							"01000000\n" +
							"01000111\n" +
							"01000000\n" +
							"00000000\n" +
							"00011000\n" +
							"00011000\n";
		
		GameOfLifeCLI.printNextGenFromInput(grid);
		
		assertEquals(expected, output.toString());
	}
	
}