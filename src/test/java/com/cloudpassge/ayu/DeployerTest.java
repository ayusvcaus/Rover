package com.cloudpassge.ayu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;

import com.cloudpassge.ayu.Plateau;
import com.cloudpassge.ayu.Deployer;

public class DeployerTest {
	
	private ByteArrayOutputStream out;
	private Deployer dplr;
	
	@Before
	public void setup() {
		dplr = new Deployer();
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	@Test 
	public void testCreatePlateau() {

		int x = 5;
		int y = 8;
		Plateau plt = dplr.createPlateau(x + " " + y);
		int[] upperRight = plt.getUpperRight();
		assertEquals(x, upperRight[0]);
		assertEquals(y, upperRight[1]);
		
		out.reset();
		plt = dplr.createPlateau(" " + x + " " + y + " ");
		upperRight = plt.getUpperRight();
		assertEquals(x, upperRight[0]);
		assertEquals(y, upperRight[1]);
		
		dplr.createPlateau(null);
		assertEquals(dplr.INVALID_UPPER_RIGHT_PARAMS, out.toString().trim());
		 
		out.reset();
		dplr.createPlateau(x+"");
		assertEquals(dplr.INVALID_UPPER_RIGHT_PARAMS, out.toString().trim());
		
		out.reset();
		dplr.createPlateau("");
		assertEquals(dplr.INVALID_UPPER_RIGHT_PARAMS, out.toString().trim());
		
		out.reset();
		dplr.createPlateau(" ");
		assertEquals(dplr.INVALID_UPPER_RIGHT_PARAMS, out.toString().trim());
		
		out.reset();
		dplr.createPlateau("a b");
		assertEquals(dplr.INVALID_UPPER_RIGHT_PARAMS, out.toString().trim());
		
		out.reset();
		dplr.createPlateau( x + "  " + y);
		assertEquals(dplr.INVALID_UPPER_RIGHT_PARAMS, out.toString().trim());
				
		out.reset();
		x = -5;
		dplr.createPlateau( x + " " + y);
		assertEquals(dplr.INVALID_UPPER_RIGHT_PARAMS + " " + x + ", " + y, out.toString().trim());
		
		out.reset();
		x = 5;
		y = -8;
		dplr.createPlateau( x + " " + y);
		assertEquals(dplr.INVALID_UPPER_RIGHT_PARAMS + " " + x + ", " + y, out.toString().trim());
	}

	@Test
	public void testWork() {
		Plateau plt = dplr.createPlateau("5 5");	
		dplr.work(plt, "1 2 N", "LMLMLMLMM");
		assertEquals("1 3 N", out.toString().trim());
		
		out.reset();
		dplr.work(plt, "3 3 E", "MMRMMRMRRM");
		assertEquals("5 1 E", out.toString().trim());
		
		out.reset();
		dplr.work(plt, " 3 3 E ", "MMRMMRMRRM");
		assertEquals("5 1 E", out.toString().trim());
		
		out.reset();
		dplr.work(null, "3 3 E", "MMRMMRMRRM");
		assertEquals(dplr.NULL_PLATEAU_INSTANCE, out.toString().trim());
		
		out.reset();
		dplr.work(plt, null, "MMRMMRMRRM");
		assertEquals(dplr.NULL_INITIAL_STATUS, out.toString().trim());
		
		out.reset();
		dplr.work(plt, "3 3 E", null);
		assertEquals("3 3 E", out.toString().trim());
		
		out.reset();
		dplr.work(plt, "3 3 E", "");
		assertEquals("3 3 E", out.toString().trim());
		
		out.reset();
		dplr.work(plt, "3 3 E", " ");
		assertEquals("3 3 E", out.toString().trim());
		
		out.reset();
		dplr.work(plt, "3 3 E", "KUCX");
		assertEquals("3 3 E", out.toString().trim());
		
		out.reset();
		dplr.work(plt, "3 3 E", " MMRMMRMRRM ");
		assertEquals("5 1 E", out.toString().trim());
		
		out.reset();
		dplr.work(plt, "3  3 E", "MMRMMRMRRM");
		assertEquals(dplr.INVALID_INITIAL_STATUS, out.toString().trim());
		
		out.reset();
		dplr.work(plt, "3 a E", "MMRMMRMRRM");
		assertEquals(dplr.INVALID_INITIAL_STATUS, out.toString().trim());
		
		out.reset();
		dplr.work(plt, "b E", "MMRMMRMRRM");
		assertEquals(dplr.INVALID_INITIAL_STATUS, out.toString().trim());
		
		out.reset();
		dplr.work(plt, "E", "MMRMMRMRRM");
		assertEquals(dplr.INVALID_INITIAL_STATUS, out.toString().trim());
		
		out.reset();
		dplr.work(plt, "", "MMRMMRMRRM");
		assertEquals(dplr.INVALID_INITIAL_STATUS, out.toString().trim());
		
		out.reset();
		dplr.work(plt, "  ", "MMRMMRMRRM");
		assertEquals(dplr.INVALID_INITIAL_STATUS, out.toString().trim());
		
		out.reset();
		dplr.work(plt, "3 3 T", "MMRMMRMRRM");
		assertEquals(dplr.INVALID_DIRECTION + "T", out.toString().trim());
		
		out.reset();
		dplr.work(plt, "-3 3 E", "MMRMMRMRRM");
		assertEquals(dplr.INVALID_INITIAL_LOCATION + "-3, 3", out.toString().trim());
		
		out.reset();
		dplr.work(plt, "3 -3 E", "MMRMMRMRRM");
		assertEquals(dplr.INVALID_INITIAL_LOCATION + "3, -3", out.toString().trim());
	}
}
