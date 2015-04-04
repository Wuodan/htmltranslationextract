package org.htmltranslationextract.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.htmltranslationextract.TextFileWriter;
import org.htmltranslationextract.TextStorer;

public abstract class FileWriterImpl extends BufferedWriter implements TextFileWriter{
	/**
	 * @throws IOException 
	 * 
	 */
	public FileWriterImpl(String filePath) throws IOException {
		super(new FileWriter(filePath));
	}
	
	// write map to properties file
	public void writeAll(TextStorer ts) throws IOException{
		Iterator<Entry<String, String>> it = ts.getAll().entrySet().iterator();
		while(it.hasNext()){
			writeEntry(it.next());
		}
	}
	
	public abstract void writeEntry(Entry<String, String> entry) throws IOException;

}
