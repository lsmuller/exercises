package com.let.exercises.aoc2018;

import static java.util.stream.Collectors.partitioningBy;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.let.exercises.utils.FileAccess;

public class Day03 {

	private void overlaps() throws IOException {
		String[] arr = FileAccess.loadFileAsStringArray(getClass().getClassLoader(), "day03");
		Set<String> overlaps = getOverlaps(arr);
		System.out.println(overlaps.size());
	}

	private Set<String> getOverlaps(String[] fileEntries) {
		Set<String> overlaps = new HashSet<>();
		Map<String, Integer> allPairs = new HashMap<>();
		Map<String, Integer> singleClaimed = new HashMap<>();

		for (String fileEntry : fileEntries) {
			int id = Integer.valueOf(fileEntry.split(" ")[0].replace("#", ""));

			Set<String> positions = getPositions(fileEntry);

			for (String pos : positions) {
				if (allPairs.containsKey(pos)) {
					int qty = allPairs.get(pos) + 1;
					if (qty > 1) {
						overlaps.add(pos);
						singleClaimed.remove(pos);
					}
					allPairs.put(pos, qty);
				}
				else {
					allPairs.put(pos, 1);
					singleClaimed.put(pos, id);
				}
			}
		}

		Collection<String> allKeys = allPairs.keySet();

		Map<Boolean, List<String>> partitionedIds = allKeys.stream()
				.collect(partitioningBy(overlaps::contains));

		List<String> overlappingIds = partitionedIds.get(true);
		List<String> missingIds = partitionedIds.get(false);


		System.out.println(overlappingIds.size());
		System.out.println(missingIds.size());

		return overlaps;
	}

	private Set<String> getPositions(String fileEntry) {
		String arr2[] = fileEntry.split(" ");
		arr2[2] = arr2[2].replace(":", "");

		String[] pads = arr2[2].split(",");
		String[] size = arr2[3].split("x");

		Set<String> pos = new HashSet<>();

		int begX = Integer.valueOf(pads[0]);
		int endX = begX + Integer.valueOf(size[0]);

		int begY = Integer.valueOf(pads[1]);
		int endY = begY + Integer.valueOf(size[1]);

		for (int i = begX; i < endX; i++) {
			for (int j = begY; j < endY; j++) {
				pos.add(i + "," + j);
			}
		}
		return pos;
	}

	public static void main(String[] arga) throws IOException {
		new Day03().overlaps();
	}

}
