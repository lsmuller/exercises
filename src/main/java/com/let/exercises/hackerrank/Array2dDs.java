package com.let.exercises.hackerrank;

public class Array2dDs {

	private static int hourglassSum(int[][] arr) {
		int maxSum = 0;
		int curSum = 0;
		for (int i = 0; i + 2 < arr.length; i++ ) {
			for (int j = 0; j + 2 < arr[i].length; j++) {
				curSum = arr[i][j] + arr[i+1][j] + arr[i+2][j] + arr[i+1][j+1] +arr[i][j+2] + arr[i+1][j+2] + arr[i+2][j+2];
			}
			if (curSum > maxSum) maxSum = curSum;
		}
		return maxSum;
	}

	public static void main(String args[]) {
		int[][] arr = new int[6][7];
		System.out.println(arr.length);
		System.out.println(arr[0].length);
	}

}
