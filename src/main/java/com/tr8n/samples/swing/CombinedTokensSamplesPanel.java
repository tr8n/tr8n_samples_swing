package com.tr8n.samples.swing;

import java.util.ArrayList;

import com.tr8n.core.Utils;

public class CombinedTokensSamplesPanel  extends SamplesPanel {

	public CombinedTokensSamplesPanel() {
		super();
		lblHeader.setText("Combined Tokens Examples");
	}

	protected void buildSamples() {
		samples = new ArrayList<Sample>();
		samples.add(new Sample("Fonts"));
		samples.add(new AttributedStringSample("You have [bold: {count|| message}]", 
				Utils.buildMap(
						"count", 5,
						"bold", Utils.buildMap(
								"font", Utils.buildMap("family", "Helvetica", "style", "bold", "size", 15)
						)
				)
		));
	}
	
}