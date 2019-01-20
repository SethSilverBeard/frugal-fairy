package com.shoptasticle.pricefinder.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
	
	public static String readFileFromClasspath(final String fileName) throws IOException, URISyntaxException {
	    return new String(Files.readAllBytes(
	                Paths.get(FileUtil.class.getClassLoader()
	                        .getResource(fileName)
	                        .toURI())));
	}
}
