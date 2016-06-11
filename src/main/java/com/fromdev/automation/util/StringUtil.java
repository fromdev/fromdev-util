package com.fromdev.automation.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class StringUtil {
	public static final String SPECIAL_CHAR_PATTERN = "[-+.^:,]";
	public static final String ALPHA_NUM_PATTERN = "[^A-Za-z0-9]";
	private static String[] sw = { "a", "about", "above", "above", "across",
			"after", "afterwards", "again", "against", "all", "almost",
			"alone", "along", "already", "also", "although", "always", "am",
			"among", "amongst", "amoungst", "amount", "an", "and", "another",
			"any", "anyhow", "anyone", "anything", "anyway", "anywhere", "are",
			"around", "as", "at", "back", "be", "became", "because", "become",
			"becomes", "becoming", "been", "before", "beforehand", "behind",
			"being", "below", "beside", "besides", "between", "beyond", "bill",
			"both", "bottom", "but", "by", "call", "can", "cannot", "cant",
			"co", "con", "could", "couldnt", "cry", "de", "describe", "detail",
			"do", "done", "down", "due", "during", "each", "eg", "eight",
			"either", "eleven", "else", "elsewhere", "empty", "enough", "etc",
			"even", "ever", "every", "everyone", "everything", "everywhere",
			"except", "few", "fifteen", "fify", "fill", "find", "fire",
			"first", "five", "for", "former", "formerly", "forty", "found",
			"four", "from", "front", "full", "further", "get", "give", "go",
			"had", "has", "hasnt", "have", "he", "hence", "her", "here",
			"hereafter", "hereby", "herein", "hereupon", "hers", "herself",
			"him", "himself", "his", "how", "however", "hundred", "ie", "if",
			"in", "inc", "indeed", "interest", "into", "is", "it", "its",
			"itself", "keep", "last", "latter", "latterly", "least", "less",
			"ltd", "made", "many", "may", "me", "meanwhile", "might", "mill",
			"mine", "more", "moreover", "most", "mostly", "move", "much",
			"must", "my", "myself", "name", "namely", "neither", "never",
			"nevertheless", "next", "nine", "no", "nobody", "none", "noone",
			"nor", "not", "nothing", "now", "nowhere", "of", "off", "often",
			"on", "once", "one", "only", "onto", "or", "other", "others",
			"otherwise", "our", "ours", "ourselves", "out", "over", "own",
			"part", "per", "perhaps", "please", "put", "rather", "re", "same",
			"see", "seem", "seemed", "seeming", "seems", "serious", "several",
			"she", "should", "show", "side", "since", "sincere", "six",
			"sixty", "so", "some", "somehow", "someone", "something",
			"sometime", "sometimes", "somewhere", "still", "such", "system",
			"take", "ten", "than", "that", "the", "their", "them",
			"themselves", "then", "thence", "there", "thereafter", "thereby",
			"therefore", "therein", "thereupon", "these", "they", "thickv",
			"thin", "third", "this", "those", "though", "three", "through",
			"throughout", "thru", "thus", "to", "together", "too", "top",
			"toward", "towards", "twelve", "twenty", "two", "un", "under",
			"until", "up", "upon", "us", "very", "via", "was", "we", "well",
			"were", "what", "whatever", "when", "whence", "whenever", "where",
			"whereafter", "whereas", "whereby", "wherein", "whereupon",
			"wherever", "whether", "which", "while", "whither", "who",
			"whoever", "whole", "whom", "whose", "why", "will", "with",
			"within", "without", "would", "yet", "you", "your", "yours",
			"yourself", "yourselves", "the" , "forth", "including", "keen", "known"};
	private static Set stopWrods = new HashSet(Arrays.asList(sw));
	private static Gson gson = new Gson();
	
	private static Pattern  regex = Pattern.compile(ALPHA_NUM_PATTERN);

	public static Set extractTags(String text) {
		Set tags = extractWrods(text);
		tags.removeAll(stopWrods);
		return tags;

	}

	public static Set extractWrods(String text) {
		//text = text.replaceAll(SPECIAL_CHAR_PATTERN, "");
		text = text.toLowerCase();
		String[] wordArray = text.split(" ");
		Set<String> words = new HashSet();
		for(int i =0;wordArray!=null && i<wordArray.length;i++) {
			if(StringUtil.notNullOrEmpty(wordArray[i]) && wordArray[i].length() > 2) { 
			Matcher matcher = regex.matcher(wordArray[i] );
				if(!matcher.find()) {
					words.add(wordArray[i]);
				}
			}
		}

		return words;
	}

	public static String getRedirectedUrl(String link) {

		try {
			URLConnection con = new URL(link).openConnection();
			// System.out.println("orignal url: " + con.getURL());
			con.connect();
			// System.out.println("connected url: " + con.getURL());
			InputStream is = con.getInputStream();
			// System.out.println("redirected url: " + con.getURL());
			is.close();
			return removeUrlParams(con.getURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String removeUrlParams(String url) {
		int idx = url.indexOf("?utm_source=feedburner");
		if (idx > 0) {
			return url.substring(0, idx);
		} else {
			return url;
		}

	}

	public static boolean notNullOrEmpty(String s) {
		return !(s == null || "".equals(s));
	}

	public static String ensureNotNullOrEmpty(String s, String defaultValue) {
		return notNullOrEmpty(s) ? s : defaultValue;
	}

	public static String[] splitOrDefault(String s, String[] old) {
		if (notNullOrEmpty(s)) {
			return s.split("\n");
		}
		return old;
	}

	public static String readFile(String fileUrl) {
		if (isNotNull(fileUrl)) {
			File f = new File(fileUrl);
			if (f.exists()) {
				Scanner s = null;
				try {
					s = new Scanner(f);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return s != null ? s.useDelimiter("\\Z").next() : "";
			} else {
				System.out.println("File does not exist or wrong path ! "
						+ fileUrl);
			}
		}
		return "";
	}

	public static String readRemoteFile(String fileUrl) {
		URL url;
		try {
			url = new URL(fileUrl);

			Scanner s = new Scanner(url.openStream());
			return s.useDelimiter("\\Z").next();
		} catch (Exception e) {
			System.out.println("Error reading remote file " + fileUrl + " "
					+ e.getMessage());
		}
		return "";
	}

	public static Map readRemoteJson(String fileUrl) {
		URL url;
		try {
			url = new URL(fileUrl);

			Scanner s = new Scanner(url.openStream());
			String val = s.useDelimiter("\\Z").next();
			Gson gson = new Gson();
			Map map = gson.fromJson(val, HashMap.class);
			return map;
		} catch (Exception e) {
			System.out.println("Error reading remote json " + fileUrl + " "
					+ e.getMessage());
		}
		return new HashMap();
	}

	public static boolean isBufferEmpty(String bufferUrl) {
		Map jsonMap = readRemoteJson(bufferUrl);
		boolean bufferEmpty = false;
		Object total = jsonMap.get("total");
		if (total != null) {
			if (total instanceof Double) {
				bufferEmpty = ((Double) total) == 0f;
			} else if (total instanceof Long) {
				bufferEmpty = ((Long) total) == 0L;
			}
		}
		return bufferEmpty;
	}

	public static String getStackTrace(Throwable t) {

		if (t != null) {

			StringWriter sw = new StringWriter();

			PrintWriter pw = new PrintWriter(sw);

			t.printStackTrace(pw);

			pw.close();

			return sw.toString();

		} else {

			return "";

		}
	}

	public static boolean isNotNull(String s) {
		return (s != null && !s.equals(""));
	}

	public static boolean isNullOrEmpty(String s) {
		return (s == null || s.equals(""));
	}

	public static String trim(Throwable t, int limit) {
		return trim(getStackTrace(t), limit);
	}

	public static String trim(String s, int limit) {
		if (isNotNull(s) && s.length() > limit && limit > 0) {
			return s.substring(0, limit);
		}
		return s;
	}
	public static String trimToChar(String s, String startChar) {
		int idx = 300;
		if(isNotNull(s)) {
			idx = s.indexOf(startChar);
		}
		return trim(s,idx);
	}

	public static String[] readRemoteFileAsStringArray(String fileUrl) {
		String[] lines = null;
		String fileString = readRemoteFile(fileUrl);
		if (StringUtil.isNotNull(fileString)) {
			lines = fileString.split("\n");
		}
		return lines;
	}
	
	public static Map<String,ArrayList<String>> readRemoteFileAsMap(String fileUrl) {
		HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
		String fileString = readRemoteFile(fileUrl);
		
		if (StringUtil.isNotNull(fileString)) {
			map = gson.fromJson(fileString, map.getClass());
		}
		return map;
	}

	public static String toCamelCase(String s) {
		return s != null ? s.substring(0, 1).toUpperCase()
				+ s.substring(1).toLowerCase() : s;
	}

	public static String spin(String text) throws Exception {
		String spinned = text;
		if (notNullOrEmpty(text)) {
			String[] words = text.split(" ");
			for (int i = 0; i < words.length; i++) {
				try {
					String suggested = Thesaurus.getInstance().suggestSynonym(
							words[i]);
					if (notNullOrEmpty(suggested)) {
						spinned = spinned.replace(" " + words[i] + " ",
								" " + toCamelCase(suggested) + " ");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return spinned;
	}
	
	public static String getSpinnedTitleForUrl(String url) throws Exception {
		String spinned = null;
		if (notNullOrEmpty(url)) {
			try {
				String suggested = TitleSpinner.getInstance().suggestTitle(url);
				//System.out.println("Suggested spin for " + url + " is " + suggested);
				if (notNullOrEmpty(suggested)) {
					spinned = suggested;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return spinned;
	}

	public static String removeSpecialChars(String s) {
		return s != null ? s.replaceAll("[^a-zA-Z0-9]+", "") : s;
	}

	public static int getInt(String s, int defaultValue) {
		int retValue = defaultValue;
		if(notNullOrEmpty(s)) {
			try {
				retValue = Integer.parseInt(s);
			} catch(Exception e) {
				//use default value instead
			}
		}
		return retValue;
	}

	public static void main(String[] args) {
		// System.out.println(removeUrlParams("http://net.tutsplus.com/tutorials/tools-and-tips/two-factor-auth-using-authy/"));
		// String s =
		// readRemoteFile("http://www.puzzlers.org/pub/wordlists/pocket.txt");
		String s = readRemoteFile("https://raw.github.com/fromdev/fromdev-static/gh-pages/release/web-dev-feeds.txt");
		// System.out
		// .println(s);
		String[] old = { "" };
		String[] list = splitOrDefault(s, old);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + ", ");
		}
		Map<String,HashSet<String>> hm = new HashMap<String,HashSet<String>>();
		HashSet<String> hs = new HashSet<String>();
		hs.add("1");
		hs.add("2");
		hm.put("test", hs);
		hm.put("test2", hs);
		
		System.out.println();
		System.out.println((new Gson()).toJson(hm));
	}

}
