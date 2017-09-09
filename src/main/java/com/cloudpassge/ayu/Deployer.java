package com.cloudpassge.ayu;

import java.util.Scanner;

import com.cloudpassge.ayu.Operator;
import com.cloudpassge.ayu.Plateau;
import com.cloudpassge.ayu.Rover;
import com.cloudpassge.ayu.Utils;

public class Deployer extends Operator {
	
	public static String NULL_PLATEAU_INSTANCE = "Null Plateau instance!";
	public static String NULL_INITIAL_STATUS = "Null initial status!";
	public static String INVALID_INITIAL_STATUS = "Invalid initial status!";
	public static String INVALID_DIRECTION ="Invalid direction! ";
	public static String INVALID_INITIAL_LOCATION = "Invalid initial location! ";	
	public static String INVALID_UPPER_RIGHT_PARAMS ="Invalid upper right params!";

	@Override
	public void work(Plateau plt, String init, String cmdLine){
		
		if (plt==null) {
			System.out.println(NULL_PLATEAU_INSTANCE);
			return;
		}
		
		if (init==null) {
			System.out.println(NULL_INITIAL_STATUS);
			return;
		}
		
		String[] initLocStr = init.trim().split(" ");
		int[] initLoc = new int[2];
		char initDir = ' ';
		
		try {
			initLoc[0] = Integer.parseInt(initLocStr[0]);
			initLoc[1] = Integer.parseInt(initLocStr[1]);
			initDir = initLocStr[2].charAt(0);
		} catch (Exception e) {
			System.out.println(INVALID_INITIAL_STATUS);
			return;
		}

		
		if (!Utils.isDirValid(initDir)) {
			System.out.println(INVALID_DIRECTION + initDir);
			return;
		}
		
		if (!Utils.isLocValid(plt, initLoc[0], initLoc[1])) {
			System.out.println(INVALID_INITIAL_LOCATION + initLoc[0] + ", " + initLoc[1]);
			return;
		}

		if (cmdLine==null || "".equals(cmdLine)) {
			//Keep rover on init status
			System.out.println(init);
			return;
		}
		
		Rover rover = new Rover(plt, initLoc[0], initLoc[1], initDir);
		
		char[] cmds = cmdLine.trim().toCharArray();
		
		if (!Utils.isCmdsValid(cmds)) {
			//Keep rover on init status
			System.out.println(init);
			return;
		}

		for (char cmd: cmds) {
			if (cmd == 'M') {
				rover.move();
			} else  {
				rover.turn(cmd);			
			} 
		}
		int[] curLoc = rover.getCurLoc();
		System.out.println(curLoc[0] + " " + curLoc[1] + " " + rover.getCurDir() + "\n");	
	}
	
	public Plateau createPlateau(String input) {
		
		int[] upperRight = new int[2];
		try {
			String[] upperRightStr = input.trim().split(" ");
			upperRight[0] = Integer.parseInt(upperRightStr[0]);
			upperRight[1] = Integer.parseInt(upperRightStr[1]);
		} catch (Exception e) {
			System.out.println(INVALID_UPPER_RIGHT_PARAMS);
			return null;
		}
		
		if (upperRight[0]<0 || upperRight[1]<0) {
			System.out.println(INVALID_UPPER_RIGHT_PARAMS + " " + upperRight[0] + ", " + upperRight[1]);
			return null;
		}
		Plateau plt = new Plateau(upperRight[0], upperRight[1]);
		return plt;
	}
	
	public static void main(String[] args) {
		
		Deployer dplr = new Deployer();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Test Input: ");
		String input = scanner.nextLine();
		
		Plateau plt = dplr.createPlateau(input);

		while (true) {
		     String init = scanner.nextLine();
		     String cmdsLine = scanner.nextLine();
		     dplr.work(plt, init, cmdsLine);
		}		
	}
}
