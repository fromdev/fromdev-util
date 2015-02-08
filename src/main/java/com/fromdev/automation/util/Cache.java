package com.fromdev.automation.util;

import java.util.ArrayList;
import java.util.List;

public class Cache<T> {

	private  List<T> cache = new ArrayList<T>();
	private  final int MAX_SIZE_CACHE = 1000;

	public  void add(T item) {
		if (!cache.contains(item)) {
			if (cache.size() >= MAX_SIZE_CACHE) {
				cache.remove(getRandomInCacheSizeRange());
			}
			cache.add(item);
		}
	}

	public  T get(int i) {
		return cache.get(i);
	}

	public  void clear() {
		cache.clear();
	}

	public  T getSpinnedRandomItem() {
		T item = null;
		if (cache.size() > 1) {
			int random = getRandomInCacheSizeRange();
			item = cache.remove(random);
		}
		return item;
	}

	public  T getRandomItem(boolean remove) {
		T item = null;
		if (cache.size() > 1) {
			int random = getRandomInCacheSizeRange();
			if(remove) {
				item = cache.remove(random);
			} else {
				item = cache.get(random);
			}
		}
		return item;
	}
	public  T getRandomItem() {
		return getRandomItem(true);
	}

	private  int getRandomInCacheSizeRange() {
		return (int) (Math.random() * (cache.size() - 1));
	}

	public  List<T> cache() {
		return cache;
	}

}
