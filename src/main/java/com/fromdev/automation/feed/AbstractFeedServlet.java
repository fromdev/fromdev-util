package com.fromdev.automation.feed;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.fromdev.automation.feed.model.Feed;
import com.fromdev.automation.util.StringUtil;

public abstract class AbstractFeedServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6619234085814110284L;
	protected String feedTitle = "Text2Feed";
	protected String feedUrl = "http://text2feed.appspot.com";
	protected String feedEndpoint = "http://text2feed.appspot.com/text2feed";

	public AbstractFeedServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.feedTitle = StringUtil.ensureNotNullOrEmpty(
				config.getInitParameter("feedTitle"), this.feedTitle);
		this.feedUrl = StringUtil.ensureNotNullOrEmpty(
				config.getInitParameter("feedUrl"), this.feedUrl);
		this.feedEndpoint = StringUtil.ensureNotNullOrEmpty(
				config.getInitParameter("feedEndpoint"), this.feedEndpoint);
	}
	public Feed getFeed(HttpServletRequest req) {
		Feed feed = (Feed) req.getSession().getServletContext()
				.getAttribute(Feed.class.getName());
		if (feed == null) {
			feed = new Feed(feedTitle, feedUrl, feedEndpoint);
			req.getSession().getServletContext()
					.setAttribute(Feed.class.getName(), feed);
		}
		return feed;
	}
}