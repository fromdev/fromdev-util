package com.fromdev.automation.util;

import com.fromdev.automation.feed.model.Feed;
import com.fromdev.automation.feed.model.ShareableItem;
import com.fromdev.automation.util.StringUtil;

public class FeedUtil {

	public static Feed loadFeed(String sourceFile, String catagory) {
		return loadFeed(sourceFile, catagory, "Text2Feed",
				"http://text2feed.appspot.com",
				"http://text2feed.appspot.com/text2feed");
	}

	public static Feed loadFeed(String sourceFile, String catagory,
			String feedTitle, String feedUrl, String feedEndpoint) {
		Feed feed = new Feed(feedTitle, feedUrl, feedUrl);

		if (StringUtil.isNotNull(sourceFile) && StringUtil.isNotNull(catagory)) {
			addTextFromFile(sourceFile, feed,catagory, feedEndpoint);
		} else {
			String msg = "sourceFile and category are required for caching.";
			System.err.println(msg);
			throw new RuntimeException(msg);
		}
		return feed;
	}

	public static void addTextFromFile(String sourceFile,
			Feed feed,  String catagory, String feedEndpoint) {
		String[] textArray = StringUtil.readRemoteFileAsStringArray(sourceFile);

		for (int i = 0; i < textArray.length; i++) {
			ShareableItem item = new ShareableItem();
			item.setUrl(feedEndpoint + "/" + catagory + "/" + i);
			item.setDescription(textArray[i]);
			item.setTags(StringUtil.extractTags(textArray[i]));
			feed.addItem(item);
		}
	}

}
