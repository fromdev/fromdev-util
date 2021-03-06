package com.fromdev.automation.feed;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromdev.automation.feed.model.ShareableItem;
import com.fromdev.automation.util.FeedCache;
import com.fromdev.automation.util.FeedReader;
import com.fromdev.automation.util.StringUtil;
import com.fromdev.automation.util.TimeBoundCache;

@SuppressWarnings("serial")
public class FeedRandomizerServlet extends HttpServlet {
	public static final String APPLICATION_RSS_XML = "application/rss+xml";
	String appBaseUrl = "http://feedrandomizer.appspot.com/";
	protected String feedTitle = "Feed Randomizer";
	String appFeedUrl = appBaseUrl + "feedrandomizer";

	protected final String getRssPrefix() { 
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<rss version=\"2.0\"\n    xmlns:content=\"http://purl.org/rss/1.0/modules/content/\"\n    xmlns:wfw=\"http://wellformedweb.org/CommentAPI/\"\n    xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\n    xmlns:atom=\"http://www.w3.org/2005/Atom\"\n>\n\n<channel>\n    <title>" + feedTitle + "</title>\n    <link>"
			+ appBaseUrl
			+ "</link>\n    <atom:link href=\""
			+ appFeedUrl
			+ "\" rel=\"self\" type=\"application/rss+xml\" />\n    <description>Feed Randomizer</description>\n    <language>en</language>";
	}
	protected final String rssSuffix = "</channel>\n</rss>";

	/**
	 * default feed list to load in case cache is empty
	 */
	private String feedList;
	private String feedUrl;
	private int hours2Cache = 8;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		feedList = config.getInitParameter("feedList");
		feedUrl = config.getInitParameter("feedUrl");
		hours2Cache = StringUtil.getInt(config.getInitParameter("hours2Cache"),hours2Cache);
	}

	protected void initFeedCache() {
		try{
			FeedReader.loadFeedsFrom(feedUrl);
		}catch(Exception e) {
			String[] feeds = { "http://feeds.feedburner.com/Fromdev" };
			feeds = StringUtil.splitOrDefault(feedList, feeds);
			FeedReader.loadFeeds(feeds);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String itemRss = findItemInTimedCache();
		resp.setContentType(APPLICATION_RSS_XML);
		resp.getWriter().println(getRssPrefix() + itemRss + rssSuffix);
	}

	protected String findItemInTimedCache() {
		if(StringUtil.isNullOrEmpty(TimeBoundCache.getCache())) {
			TimeBoundCache.setCacheForHours(findItem(), hours2Cache);
		}
		return TimeBoundCache.getCache();
	}
	protected String findItem() {
		
		ShareableItem item = FeedCache.getSpinnedRandomItem();
		String itemRss = "";
		if (item != null) {
			itemRss = item.toRssItem();
		} else {
			initFeedCache();
		}
		return itemRss;
	}
}
