package com.fromdev.automation.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HashBasedReverseIndex {

	private HashMap<String, Set<String>> index = new HashMap<String, Set<String>>();

	public void add(String value) {
		if (StringUtil.isNotNull(value)) {
			String[] values = value.split(" ");
			Set<String> valueSet = new HashSet<String>(Arrays.asList(values));
			for (int i = 0; i < values.length; i++) {
				if (StringUtil.isNotNull(values[i])) {
					Set<String> existing = index.get(values[i]);
					if (existing != null && existing.size() > 0) {
						valueSet.addAll(existing);
					}
					index.put(values[i].toLowerCase(), valueSet);
				}
			}
		}
	}

	/**
	 * find the result and default to same key if nothing found
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> find(String key) {
		Set<String> res = null;
		if (index != null) {
			res = index.get(key);
		}
		if (res == null) {
			String[] tmp = { key };
			res = new HashSet<String>(Arrays.asList(tmp));
		}
		return res;
	}

	public String findOneRandom(String key) {
		String result = null;
		if (StringUtil.isNotNull(key)) {
			Set<String> res = index.get(key.toLowerCase());
			result = RandomUtil.pickRandom(res);
		}
		return result != null ? result : key;
	}
}
