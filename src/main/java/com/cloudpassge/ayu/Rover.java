package com.cloudpassge.ayu;

import com.cloudpassge.ayu.Plateau;
import com.cloudpassge.ayu.Utils;

public final class Rover implements Vehicle {
	
	private int[] curLoc;
	private char curDir;
	private Plateau plt;
	
	public static final String INVALID_MOVEMENT = "Invild movement!";
	
	public Rover() {
		this.curLoc = new int[2];
		this.curDir = ' ';
		this.plt = new Plateau();
	}
	
	public Rover(Plateau plt, int x, int y, char dir) {
		this.curLoc = new int[2];
		curLoc[0] = x;
		curLoc[1] = y;
		this.curDir = dir;
		this.plt = plt;
	}
	
	public int[] getCurLoc() {
		return curLoc;
	}
	
	public void setCurDir(char dir) {
		this.curDir = dir;
	}
	
	public char getCurDir() {
		return curDir;
	}
	
	@Override
	public void move() {

		if (curDir=='N') {
		    if (Utils.isLocValid(plt, curLoc[0], curLoc[1]+1)) {		    	
		    	curLoc[1]++;
		    } else {
		    	System.out.println(INVALID_MOVEMENT);
		    }
		} else if (curDir=='S') {
		    if (Utils.isLocValid(plt, curLoc[0], curLoc[1]-1)) {
		    	curLoc[1]--;
		    } else {
		    	System.out.println(INVALID_MOVEMENT);
		    }			
		} else if (curDir=='E') {
		    if (Utils.isLocValid(plt, curLoc[0]+1, curLoc[1])) {
		    	curLoc[0]++;
		    } else {
		    	System.out.println(INVALID_MOVEMENT);
		    }		
		} else if (curDir=='W') {
		    if (Utils.isLocValid(plt, curLoc[0]-1, curLoc[1])) {
		    	curLoc[0]--;
		    } else {
		    	System.out.println(INVALID_MOVEMENT);
		    }				
		}
	}
	
	@Override
	public void turn(char dir) {
		if (dir=='L') {
			curDir =Utils.turnLeft.get(curDir);			
		} else if (dir=='R') {
			curDir =Utils.turnRight.get(curDir);
		} 
	}
}
