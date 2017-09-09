package com.cloudpassge.ayu;

public class Operator {
	
	protected void work() {	
		/*
		 * This method can be overridden in subclasses if needed
		 */
	}
	
	protected void work(Plateau plt, String param1, String param2) {	
		/*
		 * This method can be overridden in subclasses if needed if needed
		 */
		if (plt!=null) {
			int[] upperRight = plt.getUpperRight();
			System.out.println("upperRight="+upperRight[0]+"  "+upperRight[1]);
		} else {
			System.out.println("plt=null");
		}
		System.out.println("param1=" + param1);
		System.out.println("param2=" + param2);
	}
}
