package org.htmltranslationextract;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.htmltranslationextract.impl.FileWriterImplSimple;
import org.htmltranslationextract.impl.NodeFilter;
import org.htmltranslationextract.impl.TextStorerImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			String replacementStart = args[0];
			String replacementEnd = args[1];
			String pathInput = args[2];
			String pathOutputHtml = args[3];
			String pathOutputMap = args[4];

			TextStorer ts = new TextStorerImpl(replacementStart, replacementEnd);

			File input = new File(pathInput);
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			doc.traverse(new NodeFilter(ts));

			// write new html
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					pathOutputHtml));
			bw.write(doc.toString());
			bw.close();

			// write map to properties file
			TextFileWriter tfw = new FileWriterImplSimple(pathOutputMap);
			tfw.writeAll(ts);
			tfw.close();

			System.out.println(pathOutputHtml);
			System.out.println(pathOutputMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
