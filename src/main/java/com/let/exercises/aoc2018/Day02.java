package com.let.exercises.aoc2018;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.let.exercises.utils.FileAccess;

public class Day02 {

	public void checksum() throws IOException {
		String file = FileAccess.loadFile(getClass().getClassLoader(), "day02");
		String[] arr = file.split("\r\n");

		int count2 = 0;
		int count3 = 0;

		for (int i = 0; i < arr.length; i++) {
			String[] arr2 = arr[i].split("");
			String temp = arr[i];
			int temp2 = 0;
			int temp3 = 0;

			for (int j = 0; j < arr2.length; j++) {

				int countOccur = StringUtils.countOccurrencesOf(temp, arr2[j]);

				if (countOccur == 0) continue;

				if (countOccur % 3 == 0 && temp3 == 0) {
					count3++;
					temp3 = 1;
				}
				else if (countOccur % 2 == 0  && temp2 == 0) {
					count2++;
					temp2 = 1;
				}

				if (temp2 == 1 && temp3 == 1) break;

				temp = temp.replaceAll(arr2[j], "");

			}
			System.out.println(count2 + ", " + count3);
		}
		System.out.println(count2 * count3);
	}

	private void findBox() throws IOException {
		String file = FileAccess.loadFile(getClass().getClassLoader(), "aoc2018/day02.txt");
		String[] arr = file.split("\r\n");

		String id = "";
		int diffPos = -1;

		outerloop:
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				diffPos = getDiffPos(arr[i], arr[j]);
				if (diffPos >= 0) {
					id = arr[i];
					break outerloop;
				}
			}
		}
		System.out.println("id: " + id + ", pos: " + diffPos);
		System.out.println(id.replace(String.valueOf(id.charAt(diffPos)), ""));
	}

	private int getDiffPos(String str1, String str2){
		int countdiff = 0;
		int diffPos = -1;
		for (int i = 0; i < str1.length(); i++){
			if (str1.charAt(i) != str2.charAt(i)){
				countdiff++;
				diffPos = i;
			}
		}
		return countdiff == 1 ? diffPos : -1;
	}

	public static void main(String[] args) throws IOException {
		new Day02().checksum();
		new Day02().findBox();
	}

}

class Tuple {
	public Integer count2;
	public Integer count3;

	public Tuple(Integer count2, Integer count3) {
		this.count2 = count2;
		this.count3 = count3;
	}
}
