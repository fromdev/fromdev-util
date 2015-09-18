package com.fromdev.automation.util;
import junit.framework.TestCase;

public class TimeBoundCacheTest extends TestCase {

	public void testSetCache() {
		TimeBoundCache.getCache();
		TimeBoundCache.setCache("test", 100000l);
		assertEquals("test",TimeBoundCache.getCache());
		TimeBoundCache.setCache("test", -1l);
		assertNull(TimeBoundCache.getCache());
		TimeBoundCache.setCacheForOneDay("onedaytest");
		assertEquals("onedaytest",TimeBoundCache.getCache());
	}
	
	
}
