package com.tr8n.samples.swing;

import java.util.ArrayList;

import com.tr8n.core.Tr8n;
import com.tr8n.core.Utils;

public class DecorationTokenSamplesPanel extends SamplesPanel {

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