package com.fromdev.automation.util;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import com.fromdev.automation.feed.model.ShareableItem;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * @author Sachin Joshi
 */
public class FeedReader implements Runnable {

	public static String[] feeds = {
			"http://feeds.copyblogger.com/copyblogger&x=1",
			"http://feeds.feedburner.com/Fromdev",
			"http://feeds2.feedburner.com/webdesignerdepot?format=rss",
			"http://rss1.smashingmagazine.com/feed/",
			"http://feeds.feedburner.com/onextrapixel",
			"http://feeds.feedburner.com/ProbloggerHelpingBloggersEarnMoney",
			"http://feeds.feedburner.com/codinghorror",
			// "http://feeds.feedburner.com/Quicksprout",
			"http://feeds.feedburner.com/nettuts-summary",
			"http://feeds2.feedburner.com/24thfloor",
			"http://feeds2.feedburner.com/thenextweb",
			"http://feeds.feedburner.com/SpyreStudios",
			"http://feeds2.feedburner.com/SixRevisions",
			"http://feeds2.feedburner.com/SmashingApps",
			"http://feeds.feedburner.com/makeuseof",
			"http://www.killerstartups.com/feed/",
			"http://digg.com/search/?q=web%20design&format=rss",
			"http://digg.com/tag/curious.rss",
			"http://digg.com/tag/technology.rss" };

	private static String feedsListUrl = "https://raw.github.com/fromdev/fromdev-static/gh-pages/release/web-dev-feeds.txt";

	public static void main(String[] args) throws Exception {
		loadFeeds();
	}

	public static void loadFeeds(String[] feeds) {
		try {
			for (int i = 0; i < feeds.length; i++) {
				System.out.println("Loading... " + feeds[i]);
				loadFeed(feeds[i]);
			}

			// System.out.println(getRedirectedUrl("http://feeds.copyblogger.com/~/43950763/0/copyblogger~Heading-to-SMX-East-Heres-How-to-Save"));
			System.out.println("cached feed items " + FeedCache.cache().size());
		} finally {
		}

	}

	public static void loadFeedsFrom(String seedUrl) {

		feeds = StringUtil.splitOrDefault(StringUtil.readRemoteFile(seedUrl),
				feeds);
		loadFeeds(feeds);
	}

	public static void loadFeeds() {

		feeds = StringUtil.splitOrDefault(
				StringUtil.readRemoteFile(feedsListUrl), feeds);
		loadFeeds(feeds);
	}

	public static void loadFeed(String feedUrl) {

		if (StringUtil.notNullOrEmpty(feedUrl)) {
			XmlReader reader = null;

			try {
				URL url = new URL(feedUrl);

				reader = new XmlReader(url);
				SyndFeed feed = new SyndFeedInput().build(reader);
				System.out.println("Feed Title: " + feed.getAuthor());

				for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
					SyndEntry entry = (SyndEntry) i.next();
					// System.out.println(entry.getTitle() + ":" +
					// entry.getLink()
					// + ", " + entry.getUri());
					ShareableItem item = new ShareableItem();
					item.setUrl(entry.getLink());
					String desc = entry.getDescription().getValue();
					item.setTags(StringUtil.extractTags(entry.getTitle() + " " + desc));
					item.setTitle(entry.getTitle().replaceAll(
							StringUtil.SPECIAL_CHAR_PATTERN, ""));
					item.setDescription(StringUtil.trimToChar(desc, "<"));
					FeedCache.add(item);
//					System.out.println("Caching Progress... "
//							+ FeedCache.cache().size());
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed loading of feed " + feedUrl);
				System.out.println("Ignore and move to next");
			} finally {
				if (reader != null)
					try {
						reader.close();
					} catch (IOException e) {

					}
			}
		}
	}

	@Override
	public void run() {
		FeedReader.loadFeeds();
	}

}