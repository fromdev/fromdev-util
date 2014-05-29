package com.fromdev.automation.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LinkChecker {

	public static void main(String[] args) {
	}

	public static Set<String> checkAllLinks(Set<String> uniqueLinks) {
		Set<String> brokenLinks = new HashSet<String>();
		for (Iterator<String> iterator = uniqueLinks.iterator(); iterator
				.hasNext();) {
			String href = iterator.next();
			boolean isLive = isLive(href);
			if (isLive) {
				System.out.println(href + " : " + isLive);
			} else {
				System.out.println(href + " : ***BROKEN*** " + isLive);
				brokenLinks.add(href);
			}
		}
		return brokenLinks;
	}

	public static boolean canSkip(String url) {
		if (url == null || "".equals(url)) {
			return true;
		}
		url = url.trim().toLowerCase();
		if ((url.indexOf("http://www.fromdev.com") > -1)
				|| (url.indexOf("plus.google.com") > -1)
				|| (url.indexOf("buysellads.com") > -1)
				|| (url.indexOf("www.blogger.com") > -1)
				|| (url.indexOf("stats.buysellads.com") > -1)
				|| (url.indexOf("www.facebook.com") > -1)
				|| (url.indexOf("www.linkedin.com") > -1)
				|| (url.indexOf("www.pinterest.com") > -1)
				|| (url.indexOf("feeds.feedburner.com") > -1)
				|| (url.indexOf("javabynataraj.blogspot.in") > -1)
				|| (url.indexOf("www.mkyong.com") > -1)
				|| (url.indexOf("bp.blogspot.com") > -1)
				|| (url.indexOf("feedproxy.google.com") > -1)

		) {
			return true;
		}
		return false;
	}

	public static boolean isLive(String link) {

		HttpURLConnection urlconn = null;
		int res = -1;
		String msg = null;
		try {

			URL url = new URL(link);
			urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setConnectTimeout(10000);
			urlconn.setRequestMethod("GET");
			urlconn.connect();
			String redirlink = urlconn.getHeaderField("Location");
			System.out.println(urlconn.getHeaderFields());
			if (redirlink != null && !url.toExternalForm().equals(redirlink))
				return false;
			else
				return urlconn.getResponseCode() == HttpURLConnection.HTTP_OK;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;

		} finally {

			if (urlconn != null)
				urlconn.disconnect();

		}

	}

}
