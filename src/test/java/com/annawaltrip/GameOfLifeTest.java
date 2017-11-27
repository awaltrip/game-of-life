package com.annawaltrip;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import org.junit.Before;

public class GameOfLifeTest {
	
	private ByteArrayOutputStream output;
	
	@Before
	public void setup() {
		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
	}
	
	@Test
	public void oneCellAloneDies() {
		int[][] field = { { 0, 0, 0 },
				{ 0, 1, 0 },
				{ 0, 0, 0 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 0, 0, 0 },
				{ 0, 0, 0 } };
		
		assertArrayEquals(expected, GameOfLife.life(field));
	}
	
	@Test
	public void twoCellsAloneDie() {
		int[][] field1 = { { 0, 0, 0 },
				{ 1, 1, 0 },
				{ 0, 0, 0 } };
		
		int[][] field2 = { { 1, 0, 0 },
				{ 0, 1, 0 },
				{ 0, 0, 0 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 0, 0, 0 },
				{ 0, 0, 0 } };
		
		assertArrayEquals(expected, GameOfLife.life(field1));
		assertArrayEquals(expected, GameOfLife.life(field2));
	}
	
	@Test
	public void fourLoneCellsDie() {
		int[][] field = { { 1, 0, 1 },
				{ 0, 0, 0 },
				{ 1, 0, 1 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 0, 0, 0 },
				{ 0, 0, 0 } };
		
		assertArrayEquals(expected, GameOfLife.life(field));
	}
	
	@Test
	public void threeDiagonalCellsBecomeOne() {
		int[][] field1 = { { 1, 0, 0 },
				{ 0, 1, 0 },
				{ 0, 0, 1 } };
		
		int[][] field2 = { { 0, 0, 1 },
				{ 0, 1, 0 },
				{ 1, 0, 0 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 0, 1, 0 },
				{ 0, 0, 0 } };
		
		assertArrayEquals(expected, GameOfLife.life(field1));
		assertArrayEquals(expected, GameOfLife.life(field2));
	}
	
	@Test
	public void blockPatternRemainsTheSame() {
		int[][] field1 = { { 1, 1, 0 },
				{ 1, 1, 0 },
				{ 0, 0, 0 } };
		
		int[][] field2 = { { 0, 0, 0 },
				{ 0, 1, 1 },
				{ 0, 1, 1 } };
		
		int[][] expected1 = field1;
		int[][] expected2 = field2;
		
		assertArrayEquals(expected1, GameOfLife.life(field1));
		assertArrayEquals(expected2, GameOfLife.life(field2));
	}
	
	@Test
	public void tubPatternRemainsTheSame() {
		int[][] field = { { 0, 1, 0 },
				{ 1, 0, 1 },
				{ 0, 1, 0 } };
		
		int[][] expected = field;
		
		assertArrayEquals(expected, GameOfLife.life(field));
	}
	
	@Test
	public void horizontalLineOfThreeCellsRotates() {
		int[][] field = { { 0, 0, 0 },
				{ 1, 1, 1 },
				{ 0, 0, 0 } };
		
		int[][] expected = { { 0, 1, 0 },
				{ 0, 1, 0 },
				{ 0, 1, 0 } };
		
		assertArrayEquals(expected, GameOfLife.life(field));
	}
	
	@Test
	public void verticalLineOfThreeCellsRotates() {
		int[][] field = { { 0, 1, 0 },
				{ 0, 1, 0 },
				{ 0, 1, 0 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 1, 1, 1 },
				{ 0, 0, 0 } };
		
		assertArrayEquals(expected, GameOfLife.life(field));
	}
	
	@Test
	public void largerGridWorksWithBlockPattern() {
		int[][] field = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		int[][] expected = field;
		
		assertArrayEquals(expected, GameOfLife.life(field));
	}
	
	@Test
	public void beaconOscillatesPeriod2() {
		int[][] field = { { 0, 0, 0, 0, 0, 0, 0, 0 },
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
		
		int[][] gen2 = field;
		
		assertArrayEquals(gen1, GameOfLife.life(field));
		assertArrayEquals(gen2, GameOfLife.life(gen1));
	}
	
	@Test
	public void gliderGlides() {
		int[][] field = { { 0, 0, 0, 0, 0, 0, 0, 0 },
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
		
		assertArrayEquals(gen1, GameOfLife.life(field));
		assertArrayEquals(gen2, GameOfLife.life(gen1));
		assertArrayEquals(gen3, GameOfLife.life(gen2));
		assertArrayEquals(gen4, GameOfLife.life(gen3));
	}
	
	@Test
	public void printsNextGenFromInputBlockPattern() {
		int[][] field = { { 1, 1, 0 },
				{ 1, 1, 0 },
				{ 0, 0, 0 } };
		
		String expected = "\nYou have created this field:\n\n" +
							"110\n" +
							"110\n" +
							"000\n\n" +
							"The Next Generation:\n\n" +
							"110\n" +
							"110\n" +
							"000\n";
		
		GameOfLife.printNextGenFromInput(field);
		
		assertEquals(expected, output.toString());
	}
	
	@Test
	public void printsNextGenFromInputBeacon() {
		int[][] field = { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		String expected = "\nYou have created this field:\n\n" +
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
		
		GameOfLife.printNextGenFromInput(field);
		
		assertEquals(expected, output.toString());
	}
	
	@Test
	public void printsNextGenFromInputExample() {
		int[][] field = { { 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 0 } };
		
		String expected = "\nYou have created this field:\n\n" +
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
		
		GameOfLife.printNextGenFromInput(field);
		
		assertEquals(expected, output.toString());
	}
	
}