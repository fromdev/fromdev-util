package com.fromdev.automation.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ThesaurusTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ThesaurusTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ThesaurusTest.class);
	}

	public void testFind() {
		Thesaurus t = Thesaurus.getInstance();

		assertNotNull(t.suggestSynonym("best"));
		for (int i = 0; i < 5; i++) {
			System.out.println(t.suggestSynonym("best"));

		}

	}

}
