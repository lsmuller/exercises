package com.let.exercises.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class FileAccess {

	public static String loadFile(ClassLoader classLoader, String fileName) throws IOException {
		String path = "aoc2018/" + fileName + ".txt";
		InputStream stream = classLoader.getResourceAsStream(path);
		String var3;
		try {
			var3 = IOUtils.toString(stream, "UTF-8");
		} finally {
			stream.close();
		}

		return var3;
	}

	public static String[] loadFileAsStringArray(ClassLoader classLoader, String fileName) throws IOException {
		String file = loadFile(classLoader, fileName);
		return file.split("\r\n");
	}

	public static List<String> loadFileAsList(ClassLoader classLoader, String fileName) throws IOException {
		String file = loadFile(classLoader, fileName);
		return Arrays.asList(file.split("\r\n"));
	}
}