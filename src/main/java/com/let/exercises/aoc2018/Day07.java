package com.let.exercises.aoc2018;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.let.exercises.utils.FileAccess;

public class Day07 {
	public static void main(String[] args) throws IOException {
		List<String> inputs = FileAccess.loadFileAsList(Day06.class.getClassLoader(), "day07");
		Map<String, List<String>> pendingSteps = assembleStepList(inputs);
		List<String> availableSteps = new ArrayList<>();
		List<String>[] workers = createWorkers();

		int timeInSeconds = 0;
		StringBuilder sb = new StringBuilder();
		while (true) {
			if (pendingSteps.isEmpty() && allWorkersAvailable(workers)) {
				System.out.println("Steps: " + sb.toString());
				System.out.println("Total time: " + timeInSeconds);
				return;
			}
			getAvailableSteps(pendingSteps, availableSteps);
			if (!availableSteps.isEmpty() && hasWorkerAvailable(workers)) {
				distributeSteps(availableSteps, workers);
			}

			for (List<String> worker : workers) {
				if (worker.size() == 1) {
					updateStepList(pendingSteps, worker.get(0));
					sb.append(worker.get(0));
				}
				if (worker.size() > 0)
					worker.remove(0);
			}

			timeInSeconds++;
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

	private static List[] createWorkers(){
		List[] list = new List[5];
		for (int i = 0; i < list.length; i++)
			list[i] = new ArrayList<String>();
		return list;
	}

	private static void getAvailableSteps(Map<String, List<String>> pendingSteps, List<String> availableSteps) {
		for (Map.Entry<String, List<String>> entry : pendingSteps.entrySet()) {
			if (entry.getValue().isEmpty()) {
				availableSteps.add(entry.getKey());
			}
		}
		availableSteps.forEach(pendingSteps::remove);
	}

	private static void updateStepList(Map<String, List<String>> steps, String curStep) {
		for (Map.Entry<String, List<String>> entry : steps.entrySet()) {
			entry.getValue().remove(curStep);
		}
	}

	private static void distributeSteps(List<String> availableSteps, List[] workers) {
		for (int i = 0; i < workers.length; i++) {
			if (workers[i].isEmpty() && !availableSteps.isEmpty()) {
				String step =  Collections.min(availableSteps);
				availableSteps.remove(step);
				workers[i] = new ArrayList<>(Arrays.asList(StringUtils.repeat(step, getPosInAlphabet(step) + 60).split("")));
			}
		}
	}

	private static int getPosInAlphabet(String input) {
		final String alphabet = "abcdefghijklmnopqrstuvwxyz";
		return alphabet.indexOf(input.toLowerCase()) + 1;
	}

	private static boolean hasWorkerAvailable(List[] workers) {
		return workers[0].isEmpty() || workers[1].isEmpty() || workers[2].isEmpty() || workers[3].isEmpty();
	}

	private static boolean allWorkersAvailable(List[] workers) {
		return workers[0].isEmpty() && workers[1].isEmpty() && workers[2].isEmpty() && workers[3].isEmpty();
	}

}
