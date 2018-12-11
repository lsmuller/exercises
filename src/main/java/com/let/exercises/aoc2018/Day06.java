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
		CoordEntry[][] coordEntries = assembleMatrix(inputs);
		Set<String> infiniteAreas = determineInfiniteAreas(coordEntries);
		Map<String, Integer> finiteAreas = new HashMap<>();
		for (int i = 0; i < coordEntries.length; i++) {
			for (int j = 0; j < coordEntries[i].length; j++) {
				if (infiniteAreas.contains(coordEntries[i][j].coord))
					continue;
				if (finiteAreas.containsKey(coordEntries[i][j].coord)) {
					finiteAreas.put(coordEntries[i][j].coord, finiteAreas.get(coordEntries[i][j].coord) + 1);
				}
				else {
					finiteAreas.put(coordEntries[i][j].coord, 1);
				}
			}
		}

		Collection<Integer> areas =  finiteAreas.values();
		System.out.println(Collections.max(areas));
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

	private static int manhattanDistance(int p1, int p2, int q1, int q2) {
		//|p1-q1|+|p2-q2|
		return Math.abs(p1 - q1) + Math.abs(p2 - q2);
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

}

class CoordEntry {
	String coord;
	int manhattanDistance;
}
