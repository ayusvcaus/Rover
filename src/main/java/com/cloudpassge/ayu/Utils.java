package com.cloudpassge.ayu;

import java.util.Map;

import com.cloudpassge.ayu.Plateau;

import java.util.HashMap;

public class Utils {
	
	public static Map<Character, Character> turnRight = new HashMap() {{
		put('N', 'E');
		put('E', 'S');
		put('S', 'W');
		put('W', 'N');	
	}};

	public static Map<Character, Character> turnLeft = new HashMap() {{
		put('N', 'W');
		put('E', 'N');
		put('S', 'E');
		put('W', 'S');	
	}};
	
	public static boolean isLocValid(Plateau plt, int x, int y) {
		
		if (plt==null) {
			return false;
		}
		
		int[] upperRight = plt.getUpperRight();
		
		if (x<0 || x>upperRight[0] || y<0 || y>upperRight[1]) {
			return false;
		}
		return true;		
	}
	
	public static boolean isDirValid(char dir) {
		return dir=='N' || dir=='S' || dir=='E' || dir=='W';
	}
	
	public static boolean isCmdsValid(char[] cmds) {
		for (char cmd: cmds) {
			if (cmd!='M' && cmd !='L' && cmd!='R') {
				return false;
			}
		}
		return true;
	}
}
