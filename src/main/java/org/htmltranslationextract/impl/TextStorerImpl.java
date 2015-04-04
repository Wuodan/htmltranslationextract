package org.htmltranslationextract.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.htmltranslationextract.TextStorer;

public class TextStorerImpl extends LinkedHashMap<String, String> implements TextStorer {

	private String replaceStart, replaceEnd;
	private Pattern allSpace = Pattern.compile("^[\\s\u00a0]*$");
	private Pattern leadSpace = Pattern.compile("^[\\s\u00a0]+");
	private Pattern trailSpace = Pattern.compile("[\\s\u00a0]+$");

	/**
	 * 
	 */
	private static final long serialVersionUID = 153160067155181672L;

	public TextStorerImpl(String replaceStart, String replaceEnd) {
		this.replaceStart = replaceStart;
		this.replaceEnd = replaceEnd;
	}

	@Override
	public String store(String value) {
		String lead = "";
		String trail = "";
		// check if string is only whitespace
		Matcher m = allSpace.matcher(value);
		if(m.find()){
			return value;
		}
		// preserve leading/trailing space
		m = leadSpace.matcher(value);
		if(m.find()){
			lead = value.substring(m.start(), m.end());
			value = value.substring(m.end());
		}
		m = trailSpace.matcher(value);
		if(m.find()){
			trail = value.substring(m.start());
			value = value.substring(0, m.start());
		}
		
		String key = UUID.randomUUID().toString();
		
		if (this.containsValue(value)) {
			for (Map.Entry<String, String> entry : this.entrySet()) {
				if(value.equals(entry.getValue())){
					key = entry.getKey();
				}
			}
		} else {;
			this.put(key, value);
		}
		return lead + getReplacement(key) + trail;
	}
	
	private String getReplacement(String uuid){
		return new StringBuilder()
		.append(this.replaceStart)
		.append(uuid)
		.append(this.replaceEnd)
		.toString();
	}

	@Override
	public Map<String, String> getAll() {
		return new LinkedHashMap<String, String>(this);
	}

}