/*
 *  Copyright (c) 2014 Michael Berkovich, http://tr8nhub.com All rights reserved.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.tr8n.samples.swing.models;

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
			translation = Tr8n.translate(getLabel(), getDescription(), getTokens());
			
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
