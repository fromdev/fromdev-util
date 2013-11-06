package com.fromdev.automation.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class StringUtilTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public StringUtilTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(StringUtilTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testRedirectedUrl() {
		String originalUrl = "http://google.com";
		String result = StringUtil.getRedirectedUrl(originalUrl);
		assertEquals(result, "http://www.google.com/");
	}

	public void testReadRemoteFile() {
		String s = StringUtil
				.readRemoteFile("https://raw.github.com/fromdev/fromdev-static/gh-pages/release/web-dev-feeds.txt");
		assertTrue(s.indexOf("www.fromdev.com") > -1);
	}
}
