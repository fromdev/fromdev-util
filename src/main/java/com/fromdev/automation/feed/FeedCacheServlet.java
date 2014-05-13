package com.fromdev.automation.feed;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromdev.automation.util.FeedReader;
import com.fromdev.automation.util.StringUtil;

@SuppressWarnings("serial")
public class FeedCacheServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

		String feedUrl = req.getParameter("feed");
		if (StringUtil.notNullOrEmpty(feedUrl)) {
			String[] feedUrls = { feedUrl };
			FeedReader.loadFeeds(feedUrls);
			resp.getWriter().println("ok");
		} else {
			resp.getWriter().println("'feed' parameter required.");
		}
	}
}
