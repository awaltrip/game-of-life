package com.annawaltrip;

import org.junit.Test;
import org.junit.Assert;

public class GameOfLifeTest {
	
	@Test
	public void oneCellAloneDies() {
		int[][] field = { { 0, 0, 0 },
				{ 0, 1, 0 },
				{ 0, 0, 0 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 0, 0, 0 },
				{ 0, 0, 0 } };
		
		Assert.assertArrayEquals(expected, GameOfLife.life(field));
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
		
		Assert.assertArrayEquals(expected, GameOfLife.life(field1));
		Assert.assertArrayEquals(expected, GameOfLife.life(field2));
	}
	
	@Test
	public void fourLoneCellsDie() {
		int[][] field = { { 1, 0, 1 },
				{ 0, 0, 0 },
				{ 1, 0, 1 } };
		
		int[][] expected = { { 0, 0, 0 },
				{ 0, 0, 0 },
				{ 0, 0, 0 } };
		
		Assert.assertArrayEquals(expected, GameOfLife.life(field));
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
		
		Assert.assertArrayEquals(expected, GameOfLife.life(field1));
		Assert.assertArrayEquals(expected, GameOfLife.life(field2));
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
		
		Assert.assertArrayEquals(expected1, GameOfLife.life(field1));
		Assert.assertArrayEquals(expected2, GameOfLife.life(field2));
	}
	
	@Test
	public void tubPatternRemainsTheSame() {
		int[][] field = { { 0, 1, 0 },
				{ 1, 0, 1 },
				{ 0, 1, 0 } };
		
		int[][] expected = field;
		
		Assert.assertArrayEquals(expected, GameOfLife.life(field));
	}
}