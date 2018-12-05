package com.let.exercises.aoc2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.let.exercises.utils.FileAccess;

public class Day04 {

	private void sleepyMinute() throws Exception {
		List<String> guards = FileAccess.loadFileAsList(getClass().getClassLoader(), "day04");
		List<String> orderedShifts = guards.stream().sorted().collect(Collectors.toList());

		Map<Integer, Map<Integer, Integer>> minutesCounter = new HashMap<>();
		Map<Integer, List<Integer>> minutesSleeping = new HashMap<>();
		Map<Integer, Integer> totalSleepingTime = new HashMap<>();

		int guardId = 0;
		int sleptAt = -1;
		int wokeupAt = -1;

		for (String shift : orderedShifts) {
			String[] shiftData = shift.split(" ");
			String time = shiftData[1].replace("]", "");

			if (isId(shiftData[3])) {
				guardId = Integer.valueOf(shiftData[3].replace("#", ""));
			}
			else if (shiftData[2].equals("falls")) {
				sleptAt = Integer.valueOf(time.split(":")[1]);
			}
			else if (shiftData[2].equals("wakes")) {
				wokeupAt = Integer.valueOf(time.split(":")[1]);
			}

			if (sleptAt >= 0 && wokeupAt >= 0) {
				List<Integer> curSleepintMinutes = getMinutesRange(sleptAt, wokeupAt);

				if (minutesSleeping.containsKey(guardId)) {
					List<Integer> guardSleepingMinutes = minutesSleeping.get(guardId);
					List<Integer> repetedMinutes = guardSleepingMinutes.stream().filter(curSleepintMinutes::contains).collect(Collectors.toList());
					for (int min : repetedMinutes) {
						if (minutesCounter.containsKey(guardId)) {
							Map<Integer, Integer> map = minutesCounter.get(guardId);
							if (map.containsKey(min)) {
								map.put(min, map.get(min) + 1);
							}
							else {
								map.put(min, 2);
							}
							minutesCounter.put(guardId, map);
						}
						else {
							Map<Integer, Integer> map = new HashMap<>();
							map.put(min, 2);
							minutesCounter.put(guardId, map);
						}
					}
					guardSleepingMinutes
							.addAll(curSleepintMinutes.stream().filter(min -> !repetedMinutes.contains(min)).collect(Collectors.toList()));

					minutesSleeping.put(guardId, guardSleepingMinutes);
					totalSleepingTime.put(guardId, totalSleepingTime.get(guardId) + wokeupAt - sleptAt);
				}
				else {
					minutesSleeping.put(guardId, curSleepintMinutes);
					totalSleepingTime.put(guardId, wokeupAt - sleptAt);
				}

				sleptAt = -1;
				wokeupAt = -1;
			}
		}

		Map.Entry<Integer, Integer> maxEntry = getMaxVal(totalSleepingTime);

		int id = 0;
		Map.Entry<Integer, Integer> cur = new Entry<>(0, 0);
		for (Map.Entry<Integer, Map<Integer, Integer>> entry : minutesCounter.entrySet()) {
			if (entry.getKey().equals(maxEntry.getKey())) {
				if (getMaxVal(entry.getValue()).getValue().compareTo(cur.getValue()) > 0) {
					cur = getMaxVal(entry.getValue());
					id = entry.getKey();
				}
			}
		}

		int answer = id * cur.getKey();
		System.out.println("PART 1: key: " + id + ", value: " + cur.getValue() + " = " + answer);

		id = 0;
		cur = new Entry<>(0, 0);
		for (Map.Entry<Integer, Map<Integer, Integer>> entry : minutesCounter.entrySet()) {
			if (getMaxVal(entry.getValue()).getValue().compareTo(cur.getValue()) > 0) {
				cur = getMaxVal(entry.getValue());
				id = entry.getKey();
			}
		}

		answer = id * cur.getKey();
		System.out.println("PART 2: key: " + id + ", value: " + cur.getValue() + " = " + answer);
	}

	private List<Integer> getMinutesRange(int beg, int end) {
		List<Integer> numbers = new ArrayList<>();
		for (int i = beg; i < end; i++) {
			numbers.add(i);
		}
		return numbers;
	}

	private boolean isId(String data) {
		return data.contains("#");
	}

	private Map.Entry<Integer, Integer> getMaxVal(Map<Integer, Integer> map) {
		Map.Entry<Integer, Integer> maxEntry = new Entry<>(0, 0);
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		return maxEntry;
	}

	public static void main(String[] args) throws Exception {
		new Day04().sleepyMinute();
	}

}

class Entry<K, V> implements Map.Entry<K, V> {
	private K key;
	private V value;

	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V old = this.value;
		this.value = value;
		return old;
	}
}
