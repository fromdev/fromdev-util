package com.fromdev.automation.util;

import java.util.ArrayList;
import java.util.List;

import com.fromdev.automation.feed.model.ShareableItem;

public class FeedCache {

	private static List<ShareableItem> cache = new ArrayList<ShareableItem>();
	private static final int MAX_SIZE_CACHE = 1000;

	public static void add(ShareableItem item) {
		if (!cache.contains(item)) {
			if (cache.size() >= MAX_SIZE_CACHE) {
				cache.remove(getRandomInCacheSizeRange());
			}
			cache.add(item);
		}
	}

	public static ShareableItem get(int i) {
		return cache.get(i);
	}

	public static void clear() {
		cache.clear();
	}

	public static ShareableItem getRandomItem() {
		if (cache.size() > 1) {
			int random = getRandomInCacheSizeRange();
			return cache.remove(random);
		}
		return null;
	}

	private static int getRandomInCacheSizeRange() {
		return (int) (Math.random() * (cache.size() - 1));
	}

	public static List cache() {
		return cache;
	}

}
