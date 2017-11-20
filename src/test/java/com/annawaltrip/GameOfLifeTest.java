package com.annawaltrip;

import org.junit.Test;
import org.junit.Assert;

public class GameOfLifeTest {
	
	@Test
	public void one_cell_alone_dies() {
		int[][] field = { { 0, 0, 0 },
						  { 0, 1, 0 },
						  { 0, 0, 0 } };
		
		int[][] expected = { { 0, 0, 0 },
							 { 0, 0, 0 },
							 { 0, 0, 0 } };
		
		Assert.assertArrayEquals(expected, GameOfLife.life(field));
	}
	
	@Test
	public void two_cells_alone_die() {
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

}
