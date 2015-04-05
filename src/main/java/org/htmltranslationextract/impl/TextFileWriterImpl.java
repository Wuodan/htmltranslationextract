/**
 * 
 */
package org.htmltranslationextract.impl;

import java.io.IOException;
import java.util.Map.Entry;

import org.htmltranslationextract.TextFileWriter;

/**
 *
 * 
 */
public class TextFileWriterImpl extends TextFileWriter{

	private String separator = " = ";

	public TextFileWriterImpl(String filePath) throws IOException {
		super(filePath);
	}

	@Override
	public void writeEntry(Entry<String, String> entry) throws IOException {
		write(entry.getKey());
		write(this.separator);
		write(entry.getValue());
		newLine();
	}

}