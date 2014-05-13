package com.fromdev.automation.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Ignore;
@Ignore
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

//	@Ignore
//	public void testEquals() {
//		Thesaurus th = new Thesaurus();
//
//		th.add("a");
//		th.add("b");
//		th.add("c");
//		th.add("e");
//
//		Thesaurus th2 = new Thesaurus();
//		th2.add("A");
//
//		assertTrue(th.equals(th2));
//
//		Thesaurus th3 = new Thesaurus();
//		th3.add("f");
//
//		assertFalse(th.equals(th3));
//
//	}
//	@Ignore
//	public void testContains() {
//		Thesaurus th = new Thesaurus(4);
//
//		th.add("apple");
//		th.add("orange");
//		th.add("banana");
//		th.add("grape");
//
//		Thesaurus th2 = new Thesaurus(5);
//		th2.add("lion");
//		th2.add("mouse");
//		th2.add("cat");
//
//		Thesaurus th3 = new Thesaurus(6);
//		th3.add("sunnyvale");
//		th3.add("san jose");
//		th3.add("cupertino");
//
//		Set<Thesaurus> thSet = new HashSet<Thesaurus>();
//		thSet.add(th);
//		thSet.add(th2);
//		thSet.add(th3);
//
//		Thesaurus th4 = new Thesaurus(7);
//		th4.add("GraPe");
//		Thesaurus th5 = new Thesaurus(8);
//		th5.add("Mouse");
//		Thesaurus th6 = new Thesaurus(9);
//		th6.add("Sunnyvale");
//
//		assertTrue(thSet.contains(th4));
//		assertTrue(thSet.contains(th5));
//		assertTrue(thSet.contains(th6));
//
//	}
//
//	@Ignore
//	public void testSpin() {
//		Thesaurus th = new Thesaurus(1);
//
//		th.add("apple");
//		th.add("orange");
//		th.add("banana");
//		th.add("grape");
//
//		Thesaurus th2 = new Thesaurus(2);
//		th2.add("lion");
//		th2.add("mouse");
//		th2.add("cat");
//
//		Thesaurus th3 = new Thesaurus(3);
//		th3.add("sunnyvale");
//		th3.add("san jose");
//		th3.add("cupertino");
//
//		Set<Thesaurus> thSet = new HashSet<Thesaurus>();
//		thSet.add(th);
//		thSet.add(th2);
//		thSet.add(th3);
//
//		Thesaurus th4 = new Thesaurus(4);
//		th4.add("GraPe");
//		Thesaurus th5 = new Thesaurus(5);
//		th5.add("Mouse");
//		Thesaurus th6 = new Thesaurus(6);
//		th6.add("Sunnyvale");
//
//		assertTrue(thSet.contains(th4));
//		assertTrue(thSet.contains(th5));
//		assertTrue(thSet.contains(th6));
//
//		for (int i = 0; i < 10; i++) {
//
//			String spinnedApple = th.spin("apple");
//
//			Thesaurus th7 = new Thesaurus(7);
//			th7.add(spinnedApple);
//
//			assertTrue(thSet.contains(th7));
//			System.out.println("spinnedApple: " + spinnedApple);
//		}
//
//	}

}
