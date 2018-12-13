package com.let.exercises.aoc2018;

import static com.let.exercises.utils.FileAccess.loadFileAsList;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Day06 {

	public static void main(String args[]) throws IOException {
		List<String> inputs = loadFileAsList(Day05.class.getClassLoader(), "day06");

		//Part 1
		CoordEntry[][] coordEntries = assembleMatrix(inputs);
		System.out.println("Part 1: " + calculateMaxFiniteArea(coordEntries, determineInfiniteAreas(coordEntries)));

		//Part 2
		System.out.println("Part 2: " + totalManhattanDistance(inputs));
	}

	private static CoordEntry[][] assembleMatrix(List<String> inputs) {
		int maxI = 359;
		int maxJ = 359;

		CoordEntry[][] coordEntries = new CoordEntry[maxI][maxJ];
		for (String input : inputs) {
			input = input.replace(" ", "");
			String[] xY = input.split(",");
			for (int i = 0; i < maxI; i++) {
				for (int j = 0; j < maxJ; j++) {
					CoordEntry coordinate = new CoordEntry();
					coordinate.coord = input;
					coordinate.manhattanDistance = manhattanDistance(Integer.valueOf(xY[0]), Integer.valueOf(xY[1]), i, j);
					if (Objects.isNull(coordEntries[i][j])) {
						coordEntries[i][j] = coordinate;
					}
					else {
						if (coordEntries[i][j].manhattanDistance > coordinate.manhattanDistance) {
							coordEntries[i][j] = coordinate;
						}
						else if (coordEntries[i][j].manhattanDistance == coordinate.manhattanDistance) {
							coordEntries[i][j].coord = "none";
						}
					}
				}
			}
		}
		return coordEntries;
	}

	private static int calculateMaxFiniteArea(CoordEntry[][] coordEntries, Set<String> infiniteAreas) {
		Map<String, Integer> finiteAreas = new HashMap<>();
		for (CoordEntry[] coordEntry : coordEntries) {
			for (int j = 0; j < coordEntry.length; j++) {
				if (infiniteAreas.contains(coordEntry[j].coord))
					continue;
				if (finiteAreas.containsKey(coordEntry[j].coord)) {
					finiteAreas.put(coordEntry[j].coord, finiteAreas.get(coordEntry[j].coord) + 1);
				}
				else {
					finiteAreas.put(coordEntry[j].coord, 1);
				}
			}
		}

		Collection<Integer> areas = finiteAreas.values();
		return Collections.max(areas);
	}

	private static Set<String> determineInfiniteAreas(CoordEntry[][] coordEntries) {
		Set<String> areas = new HashSet<>();

		//line 0
		for (CoordEntry[] coordEntry : coordEntries) {
			areas.add(coordEntry[0].coord);
		}

		//column 0
		for (int i = 0; i < coordEntries[0].length; i++) {
			areas.add(coordEntries[0][i].coord);
		}

		//last line
		for (CoordEntry[] coordEntry : coordEntries) {
			areas.add(coordEntry[coordEntries[0].length - 1].coord);
		}

		//last column
		for (int i = 0; i < coordEntries[0].length; i++) {
			areas.add(coordEntries[coordEntries.length - 1][i].coord);
		}
		return areas;
	}

	private static int totalManhattanDistance(List<String> inputs) {
		Set<String> coordinates = new HashSet<>();
		int maxI = 359;
		int maxJ = 359;
		for (int i = 0; i < maxI; i++) {
			for (int j = 0; j < maxJ; j++) {
				int totalManhattanDistance = 0;
				for (String input : inputs) {
					String[] xY = input.split(", ");
					totalManhattanDistance += manhattanDistance(i, j, Integer.valueOf(xY[0]), Integer.valueOf(xY[1]));
				}
				if (totalManhattanDistance < 10000) {
					coordinates.add(i + "," + j);
				}
			}
		}
		return coordinates.size();
	}

	private static int manhattanDistance(int p1, int p2, int q1, int q2) {
		//|p1-q1|+|p2-q2|
		return Math.abs(p1 - q1) + Math.abs(p2 - q2);
	}

}

class CoordEntry {
	String coord;
	int manhattanDistance;
}
