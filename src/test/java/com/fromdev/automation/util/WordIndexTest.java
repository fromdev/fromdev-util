package com.fromdev.automation.util;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class WordIndexTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public WordIndexTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(WordIndexTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testSearch() {
		WordIndex idx = WordIndex.getInstance();
		String[] wordsArray = StringUtil.readRemoteFileAsStringArray("https://raw.githubusercontent.com/fromdev/fromdev-static/gh-pages/release/thesaurus.txt");
		
		try {
			for (int i = 0; i < wordsArray.length; i++) {
				idx.add(wordsArray[i]);
			}
			
			idx.openSearcher();
			search(idx, wordsArray[0], "besT");
			search(idx, wordsArray[1], "coding");
			search(idx, wordsArray[2], "developer");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void search(WordIndex idx, String words, String searchKeyword)
			throws Exception {
		List<String> lst = idx.search(searchKeyword);
		assertNotNull(lst);
		assertTrue(lst.size() > 0);
		String line = lst.get(0);
		assertNotNull(line);
		assertEquals(words, line);
		String[] lineWords = line.split(" ");
		System.out.println("Spinned : " + searchKeyword  + " - "+ words + " : " + RandomUtil.pickRandom(lineWords));
	}

}
