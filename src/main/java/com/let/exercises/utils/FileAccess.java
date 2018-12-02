package com.let.exercises.utils;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class FileAccess {

	public static String loadFile(ClassLoader classLoader, String file) throws IOException {
		InputStream stream = classLoader.getResourceAsStream(file);

		String var3;
		try {
			var3 = IOUtils.toString(stream, "UTF-8");
		} finally {
			stream.close();
		}

		return var3;
	}
}