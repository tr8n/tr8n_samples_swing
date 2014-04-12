package com.tr8n.samples.swing.panels;

import java.util.ArrayList;

import com.tr8n.core.Tr8n;
import com.tr8n.core.Utils;
import com.tr8n.samples.swing.models.Sample;

public class DataTokenSamplesPanel extends SamplesPanel {

	private static final long serialVersionUID = 2384597753567229884L;

	public DataTokenSamplesPanel() {
		super();
		lblHeader.setText("Data Tokens Examples");
	}

	protected void buildSamples() {
		samples = new ArrayList<Sample>();
		samples.add(new Sample("Simple Data Tokens"));
		samples.add(new Sample("Hello {user}", Utils.buildMap("user", "Michael")));
		samples.add(new Sample("You have selected {language_name} languge", 
				Utils.buildMap("language_name", Tr8n.getCurrentLanguage().getEnglishName())));
		samples.add(new Sample("Number of messages: {count}", Utils.buildMap("count", 5)));

		samples.add(new Sample("Method Tokens"));
		samples.add(new Sample("Hello {user.name}, you are a {user.gender}", 
				Utils.buildMap("user", Utils.buildMap("name", "Michael", "gender", "male"))));

		samples.add(new Sample("Piped Tokens"));
//		samples.add(new Sample("You have {count||message}", Utils.buildMap("count", 1)));
		samples.add(new Sample("You have {count||message}", Utils.buildMap("count", 5)));
//		samples.add(new Sample("{user| He, She} likes this movie.", Utils.buildMap("user", Utils.buildMap("gender", "male"))));
//		
//		samples.add(new Sample("{user} uploaded {count|| photo} to {user| his, her} photo album.", 
//				Utils.buildMap(
//						"user", Utils.buildMap(
//									"object", Utils.buildMap("name", "Michael", "gender", "male"),
//									"attribute", "name"
//								),
//						"count", 1
//						)));
	}
	
}
