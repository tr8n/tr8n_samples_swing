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