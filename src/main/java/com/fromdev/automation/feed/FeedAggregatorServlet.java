package com.fromdev.automation.feed;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.fromdev.automation.feed.model.ShareableItem;
import com.fromdev.automation.util.FeedCache;
import com.fromdev.automation.util.StringUtil;
import com.fromdev.automation.util.TitleUtil;

@SuppressWarnings("serial")
public class FeedAggregatorServlet extends FeedRandomizerServlet {
	private int itemCount = 10;
	private String[] titles = TitleUtil.AGGREGATOR_TITLES;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		itemCount = StringUtil.getInt(config.getInitParameter("itemCount"),
				itemCount);
		titles = TitleUtil.getAggeratorTitles();
	}

	protected String findItem() {
		StringBuilder itemRss = new StringBuilder();
		ShareableItem aggregateItem = new ShareableItem();
		aggregateItem.setTitle(TitleUtil.getRandomAggeratorTitle(titles));
		for (int i = 0; i < itemCount; i++) {
			ShareableItem item = FeedCache.getSpinnedRandomItem();
			if (item != null) {
				itemRss.append(item.toHtmlItem());
				aggregateItem.addTags(item.getTags());
				if(StringUtil.isNullOrEmpty(aggregateItem.getUrl())) {
					//set the first url in list as item url
					aggregateItem.setUrl(item.getUrl());
				}
			} else {
				initFeedCache();
			}
		}
		aggregateItem.setDescription(itemRss.toString());
		super.feedTitle = aggregateItem.getTagsString();
		return aggregateItem.toRssItem();
	}
}
