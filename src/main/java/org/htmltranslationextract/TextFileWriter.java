package org.htmltranslationextract;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class TextFileWriter extends BufferedWriter {
	/**
	 * @throws IOException
	 * 
	 */
	public TextFileWriter(String filePath) throws IOException {
		super(new FileWriter(filePath));
	}

	// write map to properties file
	public void writeAll(Map<String, String> keyValues) throws IOException {
		Iterator<Entry<String, String>> it = keyValues.entrySet().iterator();
		while (it.hasNext()) {
			writeEntry(it.next());
		}
	}

	public abstract void writeEntry(Entry<String, String> entry)
			throws IOException;

}
