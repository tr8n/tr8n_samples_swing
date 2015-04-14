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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.translationexchange.core.Tml;
import com.translationexchange.samples.swing.models.Sample;

class SamplesCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1995848706016559292L;
	
	private SamplesPanel samples;
    public SamplesCellRenderer(SamplesPanel samples) {
        this.samples = samples;
    }
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

    	Sample sample = samples.getSample(index);
    	if (sample.isSeparator()) {
    		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        label.setFont(new Font("Helvetica", Font.PLAIN, 16));
	        label.setHorizontalAlignment(CENTER);
	        label.setBorder(new EmptyBorder(10, 10, 10, 10));
            label.setBackground(UIManager.getColor("CheckBox.background"));
            label.setForeground(Color.BLACK);
            label.setText(sample.getLabel());
            return label;
    	}
    	
    	SampleListItem sli = new SampleListItem();
    	sli.setSample(sample);
        return sli;
    }
}

class SamplesListModel extends DefaultListModel {
	private static final long serialVersionUID = -5252552440151118199L;

	public SamplesListModel(SamplesPanel samples) {
        for (Sample sample : samples.getSamples()) {
            addElement(sample);
        }
    }
}

class SamplesSelectionListener implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
    }
}

public class SamplesPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -7822097311653319623L;

	List<Sample> samples;
	
	JLabel lblHeader;
	
	/**
	 * Create the panel.
	 */
	public SamplesPanel() {
		initialize();
        Tml.getSession().addObserver(this);
	}

	protected void initialize() {
		setLayout(new BorderLayout(0, 0));
		JScrollPane scrSamples = new JScrollPane();
		scrSamples.setBorder(new LineBorder(Color.LIGHT_GRAY));
		add(scrSamples, BorderLayout.CENTER);
		
		JList samplesList = new JList();
		scrSamples.setViewportView(samplesList);
		samplesList.setModel(new SamplesListModel(this));
		samplesList.setCellRenderer(new SamplesCellRenderer(this));
		samplesList.addListSelectionListener(new SamplesSelectionListener());
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBox.background"));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblHeader = new JLabel();
		lblHeader.setFont(new Font("Helvetica", Font.PLAIN, 22));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblHeader, BorderLayout.NORTH);		
	}
	
	protected Sample getSample(int index) {
		return getSamples().get(index);
	}
	
	protected void buildSamples() {
		samples = new ArrayList<Sample>();
	}
	
	protected List<Sample> getSamples() {
		if (this.samples == null) {
			buildSamples();
		}
		return samples;
	}

	public void update(Observable o, Object arg) {
		buildSamples();
	}
}
