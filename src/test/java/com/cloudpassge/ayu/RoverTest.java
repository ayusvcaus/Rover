package com.cloudpassge.ayu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;


import com.cloudpassge.ayu.Plateau;
import com.cloudpassge.ayu.Rover;


public class RoverTest {
	
	private Plateau plt;
	
	private ByteArrayOutputStream out;
	
	@Before
	public void setup() {
		plt = new Plateau(10,15);
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	@Test
	public void testMove() {
		Rover rover = new Rover(plt, 3, 9, 'N');
		rover.move();
		assertEquals(10, rover.getCurLoc()[1]);
		for (int i=0; i<6; i++)
		   rover.move();
		assertEquals(rover.INVALID_MOVEMENT, out.toString().trim());
		
		rover = new Rover(plt, 3, 9, 'S');
		rover.move();
		assertEquals(8, rover.getCurLoc()[1]);
		out.reset();
		for (int i=0; i<9; i++)
			rover.move();
		assertEquals(rover.INVALID_MOVEMENT, out.toString().trim());
		
		rover = new Rover(plt, 3, 9, 'E');
		rover.move();
		assertEquals(4, rover.getCurLoc()[0]);
		out.reset();
		for (int i=0; i<7; i++)
			rover.move();
		assertEquals(rover.INVALID_MOVEMENT, out.toString().trim());
		
		rover = new Rover(plt, 3, 9, 'W');
		rover.move();
		assertEquals(2, rover.getCurLoc()[0]);
		out.reset();
		for (int i=0; i<3; i++)
			rover.move();
		assertEquals(rover.INVALID_MOVEMENT, out.toString().trim());
	}

	@Test
	public void testTurn() {		
		Rover rover = new Rover(plt, 3, 9, 'N');
		rover.turn('L');
		assertEquals('W', rover.getCurDir());
		rover.turn('R');
		assertEquals('N', rover.getCurDir());
		
		rover.setCurDir('W');
		rover.turn('L');
		assertEquals('S', rover.getCurDir());
		rover.turn('R');
		assertEquals('W', rover.getCurDir());
		
		rover.setCurDir('S');
		rover.turn('L');
		assertEquals('E', rover.getCurDir());
		rover.turn('R');
		assertEquals('S', rover.getCurDir());
		
		rover.setCurDir('E');
		rover.turn('L');
		assertEquals('N', rover.getCurDir());
		rover.turn('R');
		assertEquals('E', rover.getCurDir());
	}
}
