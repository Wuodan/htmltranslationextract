/**
 * 
 */
package org.htmltranslationextract.impl;

import java.io.IOException;
import java.util.Map.Entry;

/**
 *
 * 
 */
public class FileWriterImplSimple extends FileWriterImpl {

	private String separator = " = ";

	public FileWriterImplSimple(String filePath) throws IOException {
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