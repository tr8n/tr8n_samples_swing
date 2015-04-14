/**
 * Copyright (c) 2015 Translation Exchange, Inc. All rights reserved.
 *
 *  _______                  _       _   _             ______          _
 * |__   __|                | |     | | (_)           |  ____|        | |
 *    | |_ __ __ _ _ __  ___| | __ _| |_ _  ___  _ __ | |__  __  _____| |__   __ _ _ __   __ _  ___
 *    | | '__/ _` | '_ \/ __| |/ _` | __| |/ _ \| '_ \|  __| \ \/ / __| '_ \ / _` | '_ \ / _` |/ _ \
 *    | | | | (_| | | | \__ \ | (_| | |_| | (_) | | | | |____ >  < (__| | | | (_| | | | | (_| |  __/
 *    |_|_|  \__,_|_| |_|___/_|\__,_|\__|_|\___/|_| |_|______/_/\_\___|_| |_|\__,_|_| |_|\__, |\___|
 *                                                                                        __/ |
 *                                                                                       |___/
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.translationexchange.samples.swing.panels;

import java.util.ArrayList;

import com.translationexchange.swing.Tml;
import com.translationexchange.core.Utils;
import com.translationexchange.samples.swing.models.Sample;

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
				Utils.buildMap("language_name", Tml.getCurrentLanguage().getEnglishName())));
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
