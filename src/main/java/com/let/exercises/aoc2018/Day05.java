package com.let.exercises.aoc2018;

import static com.let.exercises.utils.FileAccess.loadFile;

import java.io.IOException;

public class Day05 {

	public static void main(String[] args) throws IOException {
		String input = loadFile(Day05.class.getClassLoader(), "day05");
		int curPos = 0;

		for (int i = 1; i < input.length(); i++) {
			if (i <= curPos) continue;
			Character cur = input.charAt(curPos);
			Character next = input.charAt(i);
			if (reacts(cur.toString(), next.toString())) {
				input = input.replace(cur.toString() + next.toString(), "");
				curPos = --curPos == -1 ? 0 : curPos;
				i = i - 2 == -1 ? 0 : i - 2;
				continue;
			}
			curPos = i;
		}
		System.out.println(input);
		System.out.println(input.length());
	}

	private static boolean reacts(String cur, String next) {
		return !cur.equals(next) && cur.equalsIgnoreCase(next);
	}

}
