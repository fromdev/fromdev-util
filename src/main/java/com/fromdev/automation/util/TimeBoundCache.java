package com.fromdev.automation.util;

public class TimeBoundCache {
	private static String cachedItem;
	private static long cachedTimestamp;
	private static long validityDuration;
	public static final long HOUR = 60*60*1000l;
	public static final long DAY = 24*HOUR;
	public static final long WEEK = DAY*7l;
	
	public static void setCacheForHours(String value,int hours) {
		setCache(value,hours*HOUR);
	}
	public static void setCacheForOneDay(String value) {
		setCache(value,DAY);
	}
	public static void setCacheForOneWeek(String value) {
		setCache(value,WEEK);
	}
	public static void setCache(String value, long duration) {
		cachedItem = value;
		validityDuration = duration;
		cachedTimestamp = System.currentTimeMillis();
	}

	/**
	 * return null if entry is expired and also cleanup entry
	 * @return
	 */
	public static String getCache() {
		if(cachedTimestamp + validityDuration < System.currentTimeMillis()) {
			cachedItem = null;
			validityDuration = 0l;
			cachedTimestamp = 0l;
		}
		return cachedItem;
	}
}
