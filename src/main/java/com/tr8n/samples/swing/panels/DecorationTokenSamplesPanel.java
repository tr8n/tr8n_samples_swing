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

public class DecorationTokenSamplesPanel extends SamplesPanel {

	private static final long serialVersionUID = 366079335585670887L;

	public DecorationTokenSamplesPanel() {
		super();
		lblHeader.setText("Decoration Tokens Examples");
	}

	protected void buildSamples() {
		samples = new ArrayList<Sample>();
		samples.add(new Sample("Fonts"));
		samples.add(new AttributedStringSample("[bold: Adjust fonts] using an attribute dictionary.", 
				Utils.buildMap(
						"bold", Utils.buildMap(
								"font", Utils.buildMap("family", "Helvetica", "style", "bold", "size", 15)
						)
				)
		));

		samples.add(new AttributedStringSample("System [bold: bold font] followed by [italic: italic font].", 
				Utils.buildMap(
						"bold", Utils.buildMap(
								"font", Utils.buildMap("family", "Helvetica", "style", "bold", "size", 15)
						),
						"italic", Utils.buildMap(
								"font", Utils.buildMap("family", "Helvetica", "style", "italic", "size", 15)
						)
				)
		));
		
		samples.add(new Sample("HTML Decorations"));
		samples.add(new Sample("[bold: Adjust fonts] using HTML.", Utils.buildMap("bold", "<strong>{$0}</strong>")));
	}
	
}