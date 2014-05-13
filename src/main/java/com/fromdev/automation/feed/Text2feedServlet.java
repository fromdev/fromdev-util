package com.fromdev.automation.feed;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromdev.automation.feed.model.Feed;
import com.fromdev.automation.util.StringUtil;

@SuppressWarnings("serial")
public class Text2feedServlet extends AbstractFeedServlet {
	private static final String APPLICATION_RSS_XML = "application/rss+xml";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Feed feed = getFeed(req);
		resp.setContentType(APPLICATION_RSS_XML);
		resp.getWriter().println(feed.randomItemToRss());
	}

}
