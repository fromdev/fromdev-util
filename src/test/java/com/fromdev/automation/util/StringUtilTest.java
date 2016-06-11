package com.fromdev.automation.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.fromdev.automation.feed.model.ShareableItem;
import com.google.gson.Gson;

/**
 * Unit test for simple App.
 */
public class StringUtilTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public StringUtilTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(StringUtilTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testRedirectedUrl() {
		String originalUrl = "http://google.com";
		String result = StringUtil.getRedirectedUrl(originalUrl);
		assertEquals(result, "http://www.google.com/");
	}

	public void testReadRemoteFile() {
		String s = StringUtil
				.readRemoteFile("https://raw.github.com/fromdev/fromdev-static/gh-pages/release/web-dev-feeds.txt");
		assertTrue(s.indexOf("www.fromdev.com") > -1);
	}
	public void testSpin() throws Exception {
		String title = "How to create a scrollable splash screen with CSS3 and jQuery";
		for (int i = 0; i < 10; i++) {
			System.out.println(StringUtil
					.spin(title));
		}
		String[] words = title.split(" ");
		for (int i = 0; i < words.length; i++) {
			System.out.println(StringUtil.toCamelCase(words[i]) + " === " + title.replace(words[i],
					StringUtil.toCamelCase(words[i])) );
		}
		
	}
	public void testAggergatorTitle() {
		System.out.println(TitleUtil.getRandomAggeratorTitle(TitleUtil.getAggeratorTitles()));
	}

	public void testReadFile() {
//		String s = StringUtil
//				.readFile("/Users/sjoshi/Downloads/www-fromdev-com_20140527T174917Z_CrawlErrors.csv");
//		//assertTrue(s.indexOf("www.fromdev.com") > -1);
	}
	
	public void testReadRemoteFileAsMap() {
		@SuppressWarnings("unchecked")
		Map<String,ArrayList<String>> s = StringUtil
				.readRemoteFileAsMap("https://raw.githubusercontent.com/fromdev/fromdev-static/gh-pages/release/spin.txt");
		assertTrue(s.containsKey("test"));
		System.out.println(s.get("test"));
		
	}
	
	public void testGetSpinnedTitleForUrl() throws Exception {
		for (int i = 0; i < 10; i++) {
			String t = StringUtil.getSpinnedTitleForUrl("http://www.fromdev.com/2013/07/best-drupal-themes.html");
			assertNotNull(t);
			System.out.println(t);
		}
	}
	
	public void testExtractTags() {
		ShareableItem item = new ShareableItem();
		item.setTags(StringUtil.extractTags("<p><a href='http://www.fastcompany.com/3050797/blinded-by-the-light-why-wearables-are-outperforming-tablets-and-laptops-outdoors'><h3>Why You Can See Your Smartwatch Screen Outside But Not Your Laptop</h3></a><p>As smartwatches begin to use outdoor-friendly displays, owners of other devices are still stuck squinting.</p><br><br><a href='http://sixrevisions.com/user-experience-ux/ux-podcasts/'><h3>10 Excellent Ux Podcasts</h3></a><p><p>UX consultant/engineer Vinay Raghu talks about his favorite user experience (UX) design podcasts, as well his own podcast called IncrementalUX.</p>\r\n<p>The post <a rel=\"nofollow\" href=\"http://sixrevisions.com/user-experience-ux/ux-podcasts/\">10 Awesome UX Podcasts</a> appeared first on <a rel=\"nofollow\" href=\"http://sixrevisions.com\">Six Revisions</a>.</p>\r\n</p><br><br><a href='http://www.problogger.net/archives/2015/08/21/grow-traffic-to-your-blog-through-guest-posting-and-creating-content-for-other-blogs-forums-media-and-events/'><h3>Grow Traffic To Your Blog Through Guest Posting And Creating Content For Other Blogs Forums Media And Events</h3></a><p><p>Today&#8217;s episode is the fifth in this series we&#8217;ve been exploring on the ProBlogger Podcast about finding readers. To get you up to speed, you can find the first four here: Two Questions to Ask to Help You Find Readers for Your Blog Two Types of Content to Help You Find Readers for Your Blog [...]\r\n<p>Originally at: <a href=\"http://www.problogger.net\">Blog Tips at ProBlogger</a></p>\r\n<p><a href=\"http://www.problogger.net/31dbbb-workbook/\"><img src=\"\" width=\"468\" height=\"60\" alt=\"Build a Better Blog in 31 Days\"/></a><br/><br/><a href=\"http://www.problogger.net/archives/2015/08/21/grow-traffic-to-your-blog-through-guest-posting-and-creating-content-for-other-blogs-forums-media-and-events/\">Grow Traffic to Your Blog Through Guest Posting and Creating Content for Other Blogs, Forums, Media and Events</a></p>\r\n<p>The post <a rel=\"nofollow\" href=\"http://www.problogger.net/archives/2015/08/21/grow-traffic-to-your-blog-through-guest-posting-and-creating-content-for-other-blogs-forums-media-and-events/\">Grow Traffic to Your Blog Through Guest Posting and Creating Content for Other Blogs, Forums, Media and Events</a> appeared first on <a rel=\"nofollow\" href=\"http://www.problogger.net\">@ProBlogger</a>.</p>\r\n</p><br><br><a href='http://www.smashingmagazine.com/2015/09/mobile-navigation-for-smashing-magazine/'><h3>Mobile Navigation For Smashing Magazine A Case Study</h3></a><p><table width=\"650\"><tr><td width=\"650\"><div style=\"width:650px;\"><img src=\"http://statisches.auslieferung.commindo-media-ressourcen.de/advertisement.gif\" alt=\"\" border=\"0\" /><br /><a href=\"http://auslieferung.commindo-media-ressourcen.de/random.php?mode=target&amp;collection=smashing-rss&amp;position=1\" target=\"_blank\"><img src=\"http://auslieferung.commindo-media-ressourcen.de/random.php?mode=image&amp;collection=smashing-rss&amp;position=1\" border=\"0\" alt=\"\" /></a>&#160;<a href=\"http://auslieferung.commindo-media-ressourcen.de/random.php?mode=target&amp;collection=smashing-rss&amp;position=2\" target=\"_blank\"><img src=\"http://auslieferung.commindo-media-ressourcen.de/random.php?mode=image&amp;collection=smashing-rss&amp;position=2\" border=\"0\" alt=\"\" /></a>&#160;<a href=\"http://auslieferung.commindo-media-ressourcen.de/random.php?mode=target&amp;collection=smashing-rss&amp;position=3\" target=\"_blank\"><img src=\"http://auslieferung.commindo-media-ressourcen.de/random.php?mode=image&amp;collection=smashing-rss&amp;position=3\" border=\"0\" alt=\"\" /></a></div></td></tr></table><p>Since we started plodding around on this rock in space, human beings have always been dissatisfied with their environment ? which is (mostly) a good thing. Otherwise we might still live in caves, fearful of the weather and worshipping the sun. It's <strong>dissatisfaction and curiosity</strong> which drive us to fix things that ain't broken.</p>\r\n\r\n<figure><a href=\"http://www.smashingmagazine.com/2015/09/mobile-navigation-for-smashing-magazine/\"><img src=\"http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2015/07/mobile-nav-2015.png\" alt=\"Mobile Navigation For Smashing Magazine: A Case Study\" title=\"Mobile Navigation For Smashing Magazine: A Case Study\" /></a></figure>\r\n\r\n<p>Back in spring 2013, Smashing Magazine sported a <strong><code>&#60;select&#62;</code> menu</strong> as its mobile navigation. It wasn't considered an anti-pattern back then and I still think it's a viable solution to the complex problem of how to build <a href=\"http://webaim.org/techniques/forms/controls#select\">accessible</a> and functional cross-device navigation. Brad Frost wrote a few words about <a href=\"http://bradfrost.com/blog/web/responsive-nav-patterns/#select\">the pros and cons of this pattern</a> on his blog and I couldn't agree more.</p><p>The post <a rel=\"nofollow\" href=\"http://www.smashingmagazine.com/2015/09/mobile-navigation-for-smashing-magazine/\">Mobile Navigation For Smashing Magazine: A Case Study</a> appeared first on <a rel=\"nofollow\" href=\"http://www.smashingmagazine.com\">Smashing Magazine</a>.</p>\r\n</p><br><br><a href='http://www.wired.com/2015/09/amazon-tablet-casio-calculator/'><h3>The Parable Of The Tablet And The Calculator</h3></a><p>Casio released a calculator and Amazon released a tablet within 24 hours of each other this week. That alone is unremarkable. One costs $220, the other costs $50. That, too, wouldn?t raise many eyebrows, until you realize which is which.</p><br><br><a href='http://www.makeuseof.com/tag/learn-new-hobby-today-10-popular-udemy-courses/'><h3>Learn A New Hobby Today With 10 Popular Udemy Courses</h3></a><p><div><img width=\"297\" height=\"141\" src=\"http://cdn.makeuseof.com/wp-content/uploads/2015/09/udemy-hobby-courses-297x141.jpg?e9577d\" class=\"attachment-featured-thumb-15 wp-post-image\" alt=\"udemy-hobby-courses\" /></div><p>According to a Nielsen data, the average American household watches 155 hours of television a month. The rest of the world watches significantly less, but the number is growing each year. This trend is troubling because that time has to come from somewhere and being that we&#8217;re all limited in the amount of time we can spend on any one activity, it&#8217;s usually our hobbies that are taking a back seat. That&#8217;s where we come in. As a service to the world, we at Make Use Of think it&#8217;s about time you take on a new hobby over a weekend. To aid...</p><p>Read the full article: <a href='http://www.makeuseof.com/tag/learn-new-hobby-today-10-popular-udemy-courses/'>Learn a New Hobby Today with 10 Popular Udemy Courses</a></p><div class=\"feedflare\">\r\n<a href=\"http://feeds.feedburner.com/~ff/Makeuseof?a=kogtzorUzu4:Aul0t7G8iT0:qj6IDK7rITs\"><img src=\"http://feeds.feedburner.com/~ff/Makeuseof?d=qj6IDK7rITs\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Makeuseof?a=kogtzorUzu4:Aul0t7G8iT0:gIN9vFwOqvQ\"><img src=\"http://feeds.feedburner.com/~ff/Makeuseof?i=kogtzorUzu4:Aul0t7G8iT0:gIN9vFwOqvQ\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Makeuseof?a=kogtzorUzu4:Aul0t7G8iT0:ZC7T4KBF6Nw\"><img src=\"http://feeds.feedburner.com/~ff/Makeuseof?d=ZC7T4KBF6Nw\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Makeuseof?a=kogtzorUzu4:Aul0t7G8iT0:yIl2AUoC8zA\"><img src=\"http://feeds.feedburner.com/~ff/Makeuseof?d=yIl2AUoC8zA\" border=\"0\"></img></a>\r\n</div></p><br><br><a href='http://www.smashingapps.com/2015/09/15/10-useful-free-css-codes-for-web-developers.html'><h3>10 Useful Free Css Codes For Web Developers</h3></a><p>With this collection, we are showcasing a fresh assortment of some free and extremely useful free codes for web developers so that they can download them and simplify their work process. All the codes presented in this collection are hand-picked and will be extremely useful for the&#46;&#46;&#46;</p><br><br><a href='http://www.fromdev.com/2015/09/responsive-website-design-companies.html'><h3>Top 15 Responsive Website Design Companies 2015</h3></a><p><div dir=\"ltr\" style=\"text-align: left;\" trbidi=\"on\"><p><div class=\"separator\" style=\"clear: both; text-align: center;\"><img border=\"0\" src=\"http://1.bp.blogspot.com/-knMlYgnaepQ/VfSaCLRve6I/AAAAAAAAFII/ff2JZZ3IYv4/s640/responsive-design-companies.png\" alt=\"List of Top 15 Responsive Website Design Companies 2015 to create highly responsive websites for your business\" title=\"List of Top 15 Responsive Website Design Companies 2015 to create highly responsive websites for your business\"></div>?Being Responsive? is the new trend to ensure a great web presence and as a responsible entrepreneur, you must follow the latest conventions of the new age web designing. <br><br>What does Responsive website mean? Specifically , a single website comprising multi-browser and multiple device compatibility is termed as a responsive website. In simple words, it is a new way of creating the interactive web pages to connect with the users. The responsive designs consist of the flexible layouts and framework which are receptive to different internet-enabled devices. It automatically configures its responsive feature to fit in the browsers irrespective of the accessed device such as smart phone, ipad, tablet, laptop or even desktop. <br><br>The best thing about responsive design is that it doesn?t compromise on its resolution and content abilities with changing the user access. Before naming few of the best responsive website designing companies, let us know some of the reasons why responsive website are in huge demand.</p></div><a href=\"http://www.fromdev.com/2015/09/responsive-website-design-companies.html#more\">Continue Reading</a><div class=\"feedflare\">\r\n<a href=\"http://feeds.feedburner.com/~ff/Fromdev?a=rlgBBKBGwhk:rPb42U4nKAU:yIl2AUoC8zA\"><img src=\"http://feeds.feedburner.com/~ff/Fromdev?d=yIl2AUoC8zA\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Fromdev?a=rlgBBKBGwhk:rPb42U4nKAU:gIN9vFwOqvQ\"><img src=\"http://feeds.feedburner.com/~ff/Fromdev?i=rlgBBKBGwhk:rPb42U4nKAU:gIN9vFwOqvQ\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Fromdev?a=rlgBBKBGwhk:rPb42U4nKAU:TzevzKxY174\"><img src=\"http://feeds.feedburner.com/~ff/Fromdev?d=TzevzKxY174\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Fromdev?a=rlgBBKBGwhk:rPb42U4nKAU:V_sGLiPBpWU\"><img src=\"http://feeds.feedburner.com/~ff/Fromdev?i=rlgBBKBGwhk:rPb42U4nKAU:V_sGLiPBpWU\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Fromdev?a=rlgBBKBGwhk:rPb42U4nKAU:qj6IDK7rITs\"><img src=\"http://feeds.feedburner.com/~ff/Fromdev?d=qj6IDK7rITs\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Fromdev?a=rlgBBKBGwhk:rPb42U4nKAU:F7zBnMyn0Lo\"><img src=\"http://feeds.feedburner.com/~ff/Fromdev?i=rlgBBKBGwhk:rPb42U4nKAU:F7zBnMyn0Lo\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Fromdev?a=rlgBBKBGwhk:rPb42U4nKAU:-BTjWOF_DHI\"><img src=\"http://feeds.feedburner.com/~ff/Fromdev?i=rlgBBKBGwhk:rPb42U4nKAU:-BTjWOF_DHI\" border=\"0\"></img></a>\r\n</div><img src=\"http://feeds.feedburner.com/~r/Fromdev/~4/rlgBBKBGwhk\" height=\"1\" width=\"1\" alt=\"\"/></p><br><br><a href='http://spyrestudios.com/live-chat-ui-design-and-use/'><h3>Customer Care Chat Design Tricks Which Will Increase User Engagement</h3></a><p><p>Attracting customers on your website is not only a matter of good rankings and relevant traffic. It&#8217;s about finding the right way to speak to your customers and educate them on why your service is the most appropriate choice they can make to satisfy their needs. From brightly colored call-to-action designs to flashy newsletter subscription [&#8230;]</p>\r\n<p>The post <a rel=\"nofollow\" href=\"http://spyrestudios.com/live-chat-ui-design-and-use/\">Customer Care Chat Design Tips Which Will Increase User Engagement</a> appeared first on <a rel=\"nofollow\" href=\"http://spyrestudios.com\">SpyreStudios</a>.</p></p><br><br></p>"));
		System.out.println("tags : " + item.getTagsString());
	}
	
	public void testMatcher() {
		Pattern regex = Pattern.compile("[^A-Za-z0-9]");
		Matcher matcher = regex.matcher("simple" );
		System.out.println("matches : " + matcher.find());
	}
}
