package org.htmltranslationextract.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmltranslationextract.TextStorer;

public class TextStorerImpl extends LinkedHashMap<String, String> implements
		TextStorer {

	private String replaceStart, replaceEnd;
	private Pattern allSpace = Pattern.compile("^[\\s\u00a0]*$");
	private Pattern leadSpace = Pattern.compile("^[\\s\u00a0]+");
	private Pattern trailSpace = Pattern.compile("[\\s\u00a0]+$");
	private Pattern replacedValue;

	private Map<String, String> existing;
	/**
	 * 
	 */
	private static final long serialVersionUID = 153160067155181672L;

	public TextStorerImpl(String replaceStart, String replaceEnd) {
		this(replaceStart, replaceEnd, null);
	}

	public TextStorerImpl(String replaceStart, String replaceEnd,
			Map<String, String> existing) {
		this.replaceStart = replaceStart;
		this.replaceEnd = replaceEnd;
		this.existing = existing;
		this.replacedValue = Pattern.compile("^" + Pattern.quote(replaceStart)
				+ "(.*)" + Pattern.quote(replaceEnd) + "$");
	}

	@Override
	public String store(String value) {
		String lead = "";
		String trail = "";
		String key = UUID.randomUUID().toString();

		// check if string is only whitespace
		Matcher matcher = allSpace.matcher(value);
		if (matcher.find()) {
			return value;
		}
		// preserve leading/trailing space
		matcher = leadSpace.matcher(value);
		if (matcher.find()) {
			lead = value.substring(matcher.start(), matcher.end());
			value = value.substring(matcher.end());
		}
		matcher = trailSpace.matcher(value);
		if (matcher.find()) {
			trail = value.substring(matcher.start());
			value = value.substring(0, matcher.start());
		}

		// check if this string was already replaced
		matcher = replacedValue.matcher(value);
		if (matcher.matches()) {
			key = matcher.group(1);
			// check if we have the value
			if (!(this.containsKey(key) || (this.existing != null && this.existing
					.containsKey(key)))) {
				// should really not happen
				throw new Error("No value for existing key '" + key + "'!");
			}
		} else if (this.containsValue(value)) {
			for (Map.Entry<String, String> entry : this.entrySet()) {
				if (value.equals(entry.getValue())) {
					key = entry.getKey();
					break;
				}
			}
		} else if (this.existing != null && this.existing.containsValue(value)) {
			// check optional existing values
			for (Map.Entry<String, String> entry : this.existing.entrySet()) {
				if (value.equals(entry.getValue())) {
					key = entry.getKey();
					break;
				}
			}
		} else {
			this.put(key, value);
		}
		return lead + getReplacement(key) + trail;
	}

	private String getReplacement(String uuid) {
		return new StringBuilder().append(this.replaceStart).append(uuid)
				.append(this.replaceEnd).toString();
	}

	@Override
	public Map<String, String> getAll() {
		return new LinkedHashMap<String, String>(this);
	}

}