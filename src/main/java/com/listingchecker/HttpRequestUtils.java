package com.listingchecker;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpRequestUtils {
	
	private static final String CHARACTER_ENCODING = "UTF-8";
	
	public static String mapToQueryString(Map<String, String> map) {
		StringBuilder data = new StringBuilder();
		Iterator<Entry<String, String>> pairs = map.entrySet().iterator();
        while (pairs.hasNext()) {
            Map.Entry<String, String> pair = pairs.next();
            if (pair.getValue() != null) {
                data.append( urlEncode(pair.getKey()) + "=" + urlEncode(pair.getValue()));
            }
            else {
                data.append( urlEncode(pair.getKey()) + "=");
            }

            // Delimit parameters with ampersand (&)
            if (pairs.hasNext()) {
                data.append("&");
            }
        }

        return data.toString();
	}
	
	public static String addParameter(String originalQueryString, String parameterName, String parameterValue) {
		return originalQueryString + "&" + parameterName + "=" + parameterValue;
	}
	
    public static String urlEncode(String rawValue) {
        String value = (rawValue == null) ? "" : rawValue;
        String encoded = null;

        try {
            encoded = URLEncoder.encode(value, CHARACTER_ENCODING)
                .replace("+", "%20")
                .replace("*", "%2A")
                .replace("%7E","~");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unknown encoding: " + CHARACTER_ENCODING);
            e.printStackTrace();
        }

        return encoded;
    }
}
