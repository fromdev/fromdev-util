package com.fromdev.automation.util;

public class Thesaurus {

	private HashBasedReverseIndex idx = new HashBasedReverseIndex();

	private String src = "https://raw.githubusercontent.com/fromdev/fromdev-static/gh-pages/release/thesaurus.txt";
	private static Thesaurus singleton = new Thesaurus();

	public static Thesaurus getInstance() {
		return singleton;
	}

	private Thesaurus(String source) {
		this.src = source;
		load();
	}

	private Thesaurus() {
		load();
	}

	public void load() {
		String[] wordsArray = StringUtil.readRemoteFileAsStringArray(src);
		try {
			for (int i = 0; i < wordsArray.length; i++) {
				idx.add(wordsArray[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String suggestSynonym(String word) {
		String suggestion = word;
		try {
			suggestion = idx.findOneRandom(word);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suggestion;
	}

}
