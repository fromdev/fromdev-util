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
	public void testSpin() throws Exception {
		String title = "How to create a scrollable splash screen with CSS3 and jQuery";
		for (int i = 0; i < 10; i++) {
			System.out.println(StringUtil
					.spin(title));
		}
		String[] words = title.split(" ");
		for (int i = 0; i < words.length; i++) {
			System.out.println(StringUtil.toCamelCase(words[i]) + " === " + title.replace(words[i],
					StringUtil.toCamelCase(words[i])) );
		}
		
	}

	public void testReadFile() {
		String s = StringUtil
				.readFile("/Users/sjoshi/Downloads/www-fromdev-com_20140527T174917Z_CrawlErrors.csv");
		assertTrue(s.indexOf("www.fromdev.com") > -1);
	}
}
