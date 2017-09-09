package com.cloudpassge.ayu;

public class Plateau {
	
	private int[] upperRight;
	
	public Plateau() {
		upperRight = new int[2];
	}
	
	public Plateau(int x, int y) {
		upperRight = new int[2];
	    upperRight[0] = x;
	    upperRight[1] = y;
	}
	
	public int[] getUpperRight() {
		return upperRight;
	}

}
