package com.fromdev.automation.util;

import java.util.List;

public class Thesaurus {

	private WordIndex idx = null;

	public Thesaurus() {
		WordIndex idx = WordIndex.getInstance();
		String[] wordsArray = StringUtil
				.readRemoteFileAsStringArray("https://raw.githubusercontent.com/fromdev/fromdev-static/gh-pages/release/thesaurus.txt");

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
			List<String> synonyms = idx.search(word);
			suggestion = RandomUtil.pickRandom(synonyms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suggestion;
	}

}
