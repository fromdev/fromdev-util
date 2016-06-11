package com.fromdev.automation.feed.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.fromdev.automation.util.RandomUtil;
import com.fromdev.automation.util.StringUtil;

public class ShareableItem {

	public static final int MAX_TAGS = 5;

	private String url;
	private Set tags;
	private String title;
	private String description;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getTags() {
		return tags;
	}
	public void addTags(Set s) {
		if(tags == null) {
			tags = new HashSet<String>();
		}
		if(s!=null && s.size() > 0) {
			tags.addAll(s);
		}
	}

	public String getTagsString() {
		String tagStr = "";
		if(tags!=null) {
			Set<String> randomTags = new HashSet<String>();
			for (int i = 0;i< MAX_TAGS;i++) {
				randomTags.add(RandomUtil.pickRandom(tags));
			}
			tagStr = randomTags.toString();
			if(StringUtil.notNullOrEmpty(tagStr)) {
				tagStr = tagStr.substring(1, tagStr.length()-1);
			}
		}
		return tagStr;
	}

	public void setTags(Set tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toRssItem() {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss Z");
		String redirectedUrl = StringUtil.getRedirectedUrl(url);
		if (!StringUtil.notNullOrEmpty(redirectedUrl)) {
			redirectedUrl = url;
		}
		return "<item>\n\t    <title>" + title
				+ "</title>\n\t <guid isPermaLink=\"false\">"
				+ UUID.randomUUID().toString() + "</guid>\n\t    <link>"
				+ redirectedUrl + "</link>\n\t    <pubDate>"
				+ sdf.format(new Date())
				+ "</pubDate>\n\t    <description><![CDATA[ <p>" + description
				+ "</p> ]]></description> \n " 
				+ getCategoryForRss() 
				+ "</item>";
	}
	
	private String getCategoryForRss() {
		String tags = getTagsString();
		if(StringUtil.notNullOrEmpty(tags)) {
			return "<category>" + tags + "</category>\n" ;
		} 
		return "";
	}
	
	public String toHtmlItem() {
		String redirectedUrl = StringUtil.getRedirectedUrl(url);
		if (!StringUtil.notNullOrEmpty(redirectedUrl)) {
			redirectedUrl = url;
		}
		return "<a href='" + redirectedUrl +  "'><h3>" + title + "</h3></a>" + "<p>" + description + "</p><br><br>";
	}

	@Override
	public String toString() {
		return "ShareableItem [url=" + url;
		// // + ", tags=" + tags
		// + ", description=" + description + ", tagsString="
		// + getTagsString() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ShareableItem && StringUtil.notNullOrEmpty(url)) {
			return url.equals(((ShareableItem) obj).getUrl());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return url.hashCode();
	}

}
