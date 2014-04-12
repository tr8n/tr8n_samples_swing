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

package com.tr8n.samples.swing.panels;

import java.util.ArrayList;

import com.tr8n.core.Utils;
import com.tr8n.samples.swing.models.AttributedStringSample;
import com.tr8n.samples.swing.models.Sample;

public class CombinedTokensSamplesPanel  extends SamplesPanel {

	private static final long serialVersionUID = -3258052902074140586L;

	public CombinedTokensSamplesPanel() {
		super();
		lblHeader.setText("Combined Tokens Examples");
	}

	protected void buildSamples() {
		samples = new ArrayList<Sample>();
		samples.add(new Sample("Fonts"));
		samples.add(new AttributedStringSample("You have [indent: {count|| message}]", 
				Utils.buildMap(
						"count", 1,
						"indent", Utils.buildMap(
								"font", Utils.buildMap("family", "Helvetica", "style", "bold", "size", 15)
						)
				)
		));
		
		samples.add(new AttributedStringSample("You have [indent: {count|| message}]", 
				Utils.buildMap(
						"count", 2,
						"indent", Utils.buildMap(
								"font", Utils.buildMap("family", "Arial", "style", "bold", "size", 15),
								"strikethrough", true
						)
				)
		));		
		
		samples.add(new AttributedStringSample("You have [indent: {count|| message}]", 
				Utils.buildMap(
						"count", 5,
						"indent", Utils.buildMap(
								"font", Utils.buildMap("family", "Helvetica", "style", "italic", "size", 15),
								"color", "blue"
								
						)
				)
		));		
	}
	
}