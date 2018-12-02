package com.let.exercises.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BalancedBrackets {

	private static String isBalanced(String s) {
		String[] input = s.split("");
		Stack<String> brackets = new Stack<>();

		for (String bracket : input) {
			if (isOpeningBracket(bracket)) {
				brackets.push(bracket);
			}
			else if (isClosingBracket(bracket)) {
				if (brackets.empty())
					return "NO";
				String openingBracket = brackets.pop();
				if (!isBalanced(openingBracket, bracket)){
					return "NO";
				}
			}
		}
		return brackets.empty() ? "YES" : "NO";
	}

	private static boolean isOpeningBracket(String bracket){
		return "{".equals(bracket) || "[".equals(bracket) || "(".equals(bracket);
	}

	private static boolean isClosingBracket(String bracket){
		return "}".equals(bracket) || "]".equals(bracket) || ")".equals(bracket);
	}

	private static boolean isBalanced(String openingBracket, String closingBracket) {
		return ("{".equals(openingBracket) && "}".equals(closingBracket)) ||
				("[".equals(openingBracket) && "]".equals(closingBracket)) ||
				("(".equals(openingBracket) && ")".equals(closingBracket));
	}

	public static void main(String[] args) {

		List<String> samples = new ArrayList<>();
		samples.add("{[()]}");
		samples.add("{[(])}");
		samples.add("{{[[(())]]}}");

		for (String sample : samples) {
			System.out.println(isBalanced(sample));
		}
	}
}
