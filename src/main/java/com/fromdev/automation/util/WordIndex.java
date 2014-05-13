package com.fromdev.automation.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class WordIndex {
	private static final String FIELDNAME = "fieldname";
	private static WordIndex singleton = new WordIndex();
	private Directory directory = null;
	private Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_48);
	private IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48,
			analyzer);
	private IndexWriter iwriter = null;
	private DirectoryReader ireader = null;
	private IndexSearcher isearcher = null;
	QueryParser parser = new QueryParser(Version.LUCENE_48, FIELDNAME,
			analyzer);

	private WordIndex() {
		try {
			directory = new RAMDirectory();
			iwriter = new IndexWriter(directory, config);
			iwriter.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openSearcher() throws IOException {
		ireader = DirectoryReader.open(directory);
		isearcher = new IndexSearcher(ireader);
	}

	public static WordIndex getInstance() {
		return singleton;
	}

	public static void main(String[] args) throws Exception {
		

	}

	public void add(String text) throws Exception {
		Document doc = new Document();
		Field f = new Field("fieldname", text, TextField.TYPE_STORED);
		doc.add(f);
		iwriter.addDocument(doc);
		iwriter.commit();
	}
	
	public List<String> search(String word) throws Exception {
		List<String> results = new ArrayList<String>();
		Query query = parser.parse(word);
		ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = isearcher.doc(hits[i].doc);
			results.add(hitDoc.get(FIELDNAME));
			System.out.println();
		}
		return results;
	}

	public void close() {
		try {
			ireader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			directory.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			iwriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
