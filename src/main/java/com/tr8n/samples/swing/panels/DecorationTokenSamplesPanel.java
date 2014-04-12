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