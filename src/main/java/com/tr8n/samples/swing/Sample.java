package com.tr8n.samples.swing;

import java.util.Map;

import com.tr8n.core.Tr8n;

public class Sample {
	private String label;
	private String description;
	private Map<String, Object> tokens;
	private boolean separator;
	private String source;
	private String translation;

	public Sample() {
		this("");
	}
	
	public Sample(String title) {
		setSeparator(true);
		setLabel(title);
	}

	public Sample(String label, String description, Map<String, Object> tokens) {
		setLabel(label);
		setDescription(description);
		setTokens(tokens);
	}
	
	public Sample(String label, Map<String, Object> tokens) {
		setLabel(label);
		setTokens(tokens);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Map<String, Object> getTokens() {
		return tokens;
	}

	public void setTokens(Map<String, Object> tokens) {
		this.tokens = tokens;
	}
	
	public String getTranslation() {
		if (translation == null)
			translation = Tr8n.tr(getLabel(), getDescription(), getTokens());
			
		return translation;
	}
	
	public boolean isSeparator() {
		return separator;
	}

	public void setSeparator(boolean separator) {
		this.separator = separator;
	}

	public String toString() {
		return getLabel();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public boolean hasSource() {
		return getSource() != null;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
