package com.let.exercises.aoc2018;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.let.exercises.utils.FileAccess;

public class Day07 {
	public static void main(String[] args) throws IOException {
		List<String> inputs = FileAccess.loadFileAsList(Day06.class.getClassLoader(), "day07");
		Map<String, List<String>> allSteps = assembleStepList(inputs);

		StringBuilder sb = new StringBuilder();
		while (true) {
			if (allSteps.isEmpty()) {
				System.out.println(sb.toString());
				return;
			}
			List<String> availableSteps = getAvailableSteps(allSteps);
			String curStep = Collections.min(availableSteps);
			sb.append(curStep);
			updateStepList(allSteps, curStep);
		}
	}

	private static Map<String, List<String>> assembleStepList(List<String> inputs) {
		Map<String, List<String>> nodes = new HashMap<>();
		for (String input : inputs) {
			String[] steps = input.split(" ");

			if (!nodes.containsKey(steps[1])) {
				nodes.put(steps[1], new ArrayList<>());
			}

			List<String> prev = new ArrayList<>();
			if (nodes.containsKey(steps[7])) {
				prev = nodes.get(steps[7]);
			}

			prev.add(steps[1]);
			nodes.put(steps[7], prev);
		}

		return nodes;
	}

	private static List<String> getAvailableSteps(Map<String, List<String>> steps) {
		List<String> result = new ArrayList<>();
		for (Map.Entry<String, List<String>> entry : steps.entrySet()) {
			if (entry.getValue().isEmpty())
				result.add(entry.getKey());
		}
		return result;
	}

	private static void updateStepList(Map<String, List<String>> steps, String curStep) {
		for (Map.Entry<String, List<String>> entry : steps.entrySet()) {
			//steps.put(entry.getKey(), entry.getValue());
			entry.getValue().remove(curStep);
		}
		steps.remove(curStep);
	}

}
