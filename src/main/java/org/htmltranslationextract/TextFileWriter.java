package org.htmltranslationextract;

import java.io.Closeable;
import java.io.Flushable;
import java.util.Map.Entry;

/**
 * 
 *
 */
public interface TextFileWriter extends Closeable, Flushable, Appendable, AutoCloseable {
	
	public void writeAll(TextStorer ts) throws Exception;

	public void writeEntry(Entry<String, String> entry) throws Exception;
}