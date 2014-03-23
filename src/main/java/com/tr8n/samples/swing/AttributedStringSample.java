package com.tr8n.samples.swing;

import java.text.AttributedString;
import java.util.Map;

import com.tr8n.core.Tr8n;

public class AttributedStringSample extends Sample {
	
	AttributedString translation;
	
	public AttributedStringSample() {
		super();
	}

	public AttributedStringSample(String label, Map<String, Object> tokens) {
		super(label, tokens);
	}
	
	public AttributedString getAttributedString() {
		if (translation == null)
			translation =  Tr8n.tras(getLabel(), getDescription(), getTokens()); 
		return translation;
	}
	
}
