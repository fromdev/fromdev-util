package com.fromdev.automation.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public static String pickRandom(Set<String> s) {
		String val = null;
		if (s != null && s.size() > 0) {
			String tmp[] = {};
			val = pickRandom(s.toArray(tmp));
		}
		return val;
	}
	
	public static String pickRandom(Map<String,ArrayList<String>> col, String key) {
		String result = null;
		if (StringUtil.isNotNull(key)) {
			ArrayList<String> res = col.get(key.toLowerCase());
			result = RandomUtil.pickRandom(res);
		}
		return result != null ? result : key;
	}

	public static int getNumberBetween(int start, int end) {
		return (int) (end * Math.random()) + start;
	}
}
