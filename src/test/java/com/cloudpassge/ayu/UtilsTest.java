package com.cloudpassge.ayu;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.cloudpassge.ayu.Utils;
import com.cloudpassge.ayu.Plateau;

public class UtilsTest {

	@Test
	public void testIsLocValid() {
		assertFalse(Utils.isLocValid(null, 3, 8));
		Plateau plt = new Plateau(6, 8);
		assertTrue(Utils.isLocValid(plt, 3, 6));
		assertFalse(Utils.isLocValid(plt, -3, 8));
		assertFalse(Utils.isLocValid(plt, 3, -8));
		assertFalse(Utils.isLocValid(plt, 3, 11));
		assertFalse(Utils.isLocValid(plt, 7, 8));
	}
	
	@Test
	public void testIsDirValid() {
		assertTrue(Utils.isDirValid('N'));
		assertTrue(Utils.isDirValid('W'));
		assertTrue(Utils.isDirValid('S'));
		assertTrue(Utils.isDirValid('E'));
		assertFalse(Utils.isDirValid('X'));
		assertFalse(Utils.isDirValid('n'));
	}
	
	@Test
	public void testIsCmdsValid() {
		char[] cmds = {'M', 'M', 'R', 'M', 'L', 'M', 'R'};
		assertTrue(Utils.isCmdsValid(cmds));
        cmds[2] = 'X';
		assertFalse(Utils.isCmdsValid(cmds));
		cmds[2] = 'r';
		assertFalse(Utils.isCmdsValid(cmds));
	}
}
