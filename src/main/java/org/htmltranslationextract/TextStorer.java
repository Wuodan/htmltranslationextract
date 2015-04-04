package org.htmltranslationextract;

import java.util.Map;

public interface TextStorer {

	public String store(String s);

	public Map<String, String> getAll();
}
