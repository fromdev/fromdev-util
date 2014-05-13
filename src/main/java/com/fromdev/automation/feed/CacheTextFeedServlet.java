package com.fromdev.automation.feed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromdev.automation.feed.model.Feed;
import com.fromdev.automation.feed.model.ShareableItem;
import com.fromdev.automation.util.FeedUtil;
import com.fromdev.automation.util.StringUtil;

@SuppressWarnings("serial")
public class CacheTextFeedServlet extends AbstractFeedServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String message = "";
		boolean success = false;
		String desc = req.getParameter("description");
		String tags = req.getParameter("tags");
		ShareableItem item = new ShareableItem();
		if (validate(desc, tags)) {
			Feed feed = getFeed(req);
			if (feed != null && StringUtil.isNotNull(feed.getAppFeedUrl())) {
				item.setUrl(feed.getAppFeedUrl() + "/" + feed.size());
				item.setDescription(desc);
				item.setTags(StringUtil.extractTags(tags));

				if (item != null && StringUtil.notNullOrEmpty(item.getUrl())) {
					success = feed.addItem(item);
					req.getSession().getServletContext()
							.setAttribute(Feed.class.getName(), feed);

				}
			}

			resp.setContentType("text/plain");
			message = feed.toRss();
		} else {
			message = "Error validating input fields. desc=[" + desc
					+ "] tags=[" + tags + "] status=" + success;
		}
		resp.setContentType("text/plain");
		resp.getWriter().println(message);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		boolean status = loadFeed(req);
		resp.setContentType("text/plain");
		resp.getWriter().println(status);
	}

	public boolean loadFeed(HttpServletRequest req) {
		boolean status = false;
		try {

			Feed feed = new Feed(feedTitle, feedUrl, feedEndpoint);
			String sourceFile = req.getParameter("sourceFile");
			String catagory = req.getParameter("category");

			feed = FeedUtil.loadFeed(sourceFile, catagory);
			req.getSession().getServletContext()
					.setAttribute(Feed.class.getName(), feed);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public boolean validate(String desc, String tags) {
		boolean success = false;
		if (StringUtil.notNullOrEmpty(desc)) {
			if (desc.length() < 140) {
				success = true;
			}
		}
		if (StringUtil.notNullOrEmpty(tags)) {
			if (tags.length() < 140) {
				success = true;
			}
		}

		return success;
	}
}
