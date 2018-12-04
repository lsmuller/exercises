package com.let.exercises.aoc2018;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.let.exercises.utils.FileAccess;

public class Day01 {

	private void calculateFrequency() throws IOException {
		String file = FileAccess.loadFile(getClass().getClassLoader(), "day01");
		String[] arr = file.split("\r\n");

		int sum = 0;
		for (int i = 0; i < arr.length; i++){
			sum += Integer.valueOf(arr[i]);
		}
		System.out.println(sum);
	}

	private void calibrateFrequency() throws IOException {
		String file = FileAccess.loadFile(getClass().getClassLoader(), "day01");
		String[] arr = file.split("\r\n");

		Set<Integer> counter = new HashSet<>();
		counter.add(0);

		int sum = 0;
		do {
			for (int i = 0; i < arr.length; i++){
				sum += Integer.valueOf(arr[i]);

				if (counter.contains(sum)){
					System.out.println(sum);
					return;
				}
				counter.add(sum);
			}
		} while (true);

	}

	public static void main(String[] args) throws IOException {
		new Day01().calculateFrequency();
		new Day01().calibrateFrequency();
	}
}
