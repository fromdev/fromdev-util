package com.fromdev.automation.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TitleSpinnerTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public TitleSpinnerTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(TitleSpinnerTest.class);
	}

	public void testFind() {
		TitleSpinner t = TitleSpinner.getInstance();

		assertNotNull(t.suggestTitle("test"));
		for (int i = 0; i < 5; i++) {
			System.out.println(t.suggestTitle("http://www.fromdev.com/2013/07/best-drupal-themes.html"));

		}

	}

}
