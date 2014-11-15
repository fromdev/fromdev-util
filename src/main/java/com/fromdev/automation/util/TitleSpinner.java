package com.fromdev.automation.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TitleSpinner {

	private Map<String,ArrayList<String>> idx = new HashMap<String,ArrayList<String>>();

	private String src = "https://raw.githubusercontent.com/fromdev/fromdev-static/gh-pages/release/spin.txt";
	private static TitleSpinner singleton = new TitleSpinner();
	private static long expiryTime = 0;
	public static long duration = 1000*60*60*24;
	
	public static TitleSpinner getInstance() {
		if(System.currentTimeMillis() > expiryTime) {
			singleton.idx.clear();
			singleton.idx = null;
			singleton = new TitleSpinner();
		}
		return singleton;
	}

	public void setExpiryDuration(long d) {
		duration = d;
	}
	private TitleSpinner(String source) {
		this.src = source;
		load();
	}

	private TitleSpinner() {
		expiryTime = System.currentTimeMillis() + duration;
		load();
	}

	public void load() {
		try {
			idx = StringUtil.readRemoteFileAsMap(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String suggestTitle(String word) {
		String suggestion = word;
		try {
			suggestion = RandomUtil.pickRandom(idx, word);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suggestion;
	}

}
