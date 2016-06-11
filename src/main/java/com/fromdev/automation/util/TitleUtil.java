package com.fromdev.automation.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TitleUtil {
	public static final String[] AGGREGATOR_TITLES = {
		"List of Useful Sites ",
		"My Favorite Bookmarks ",
		"Interesting Sites I Found ",
		"Sites Worth Bookmarking "};
	private static String [] dateFormats = {
		"MMM dd yyyy",
		"MM/dd/yyyy",
		"dd MMM yyyy",
		"EEE dd MMM yyyy",
		"EEEEEE, dd MMM yyyy"
	}; 
	/**
	 * Load title list from remote location
	 * @return
	 */
	public static String[] getAggeratorTitles() {
		String [] s = StringUtil.readRemoteFileAsStringArray("https://raw.github.com/fromdev/fromdev-static/gh-pages/release/aggregator-titles.txt");
		if(s != null && s.length > 0) {
			return s;
		}
		return AGGREGATOR_TITLES;
	}
	public static String getRandomAggeratorTitle(String[] titles) {
		SimpleDateFormat sdf = new SimpleDateFormat(RandomUtil.pickRandom(dateFormats));	
		return RandomUtil.pickRandom(titles) + " on "+ sdf.format(new Date());
	}
}
