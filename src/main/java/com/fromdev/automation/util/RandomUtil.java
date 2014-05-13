package com.fromdev.automation.util;

import java.util.List;

public class RandomUtil {
	public static String pickRandom(String[] arr) {
		String val = null;
		if (arr != null && arr.length > 0) {
			val = arr[getNumberBetween(0, arr.length)];
		}
		return val;
	}

	public static String pickRandom(List<String> c) {
		String val = null;
		if (c != null && c.size() > 0) {
			val = c.get(getNumberBetween(0, c.size()));
		}
		return val;
	}

	public static int getNumberBetween(int start, int end) {
		return (int) (end * Math.random()) + start;
	}
}
