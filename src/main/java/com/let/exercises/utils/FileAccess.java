package com.let.exercises.utils;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class FileAccess {

	public static String loadFile(ClassLoader classLoader, String day) throws IOException {
		String path = "aoc2018/" + day + ".txt";

		InputStream stream = classLoader.getResourceAsStream(path);

		String var3;
		try {
			var3 = IOUtils.toString(stream, "UTF-8");
		} finally {
			stream.close();
		}

		return var3;
	}

	public static String[] loadFileAsStringArray(ClassLoader classLoader, String day) throws IOException {
		String path = "aoc2018/" + day + ".txt";
		String file = loadFile(classLoader, path);
		return file.split("\r\n");
	}
}