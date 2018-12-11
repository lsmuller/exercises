package com.let.exercises.aoc2018;

import static com.let.exercises.utils.FileAccess.loadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Day05 {

	public static void main(String[] args) throws IOException {
		String input = loadFile(Day05.class.getClassLoader(), "day05");
		List<Integer> sizes = new ArrayList<>();

		String[] characters = removeDuplicates(input);

		for (String c : characters) {
			String replaced = input.replace(c.toLowerCase(), "").replace(c.toUpperCase(), "");
			sizes.add(polymerReactionSize(replaced));
		}
		System.out.println(Collections.min(sizes));
	}

	private static boolean reacts(String cur, String next) {
		return !cur.equals(next) && cur.equalsIgnoreCase(next);
	}

	private static int polymerReactionSize(String input) {
		int curPos = 0;
		for (int i = 1; i < input.length(); i++) {
			if (i <= curPos)
				continue;
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
		return input.length();
	}

	private static String[] removeDuplicates(String input) {
		char[] chars = input.toCharArray();
		Set<Character> charSet = new LinkedHashSet<>();
		for (char c : chars) {
			charSet.add(c);
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : charSet) {
			sb.append(character);
		}
		return sb.toString().split("");
	}

}
