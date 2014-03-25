package com.tr8n.samples.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.tr8n.core.Tr8n;
import com.tr8n.core.Utils;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.UIManager;

class SamplesCellRenderer extends DefaultListCellRenderer {
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
    private SamplesPanel samples;
    public SamplesListModel(SamplesPanel samples) {
        this.samples = samples;
        for (Sample sample : samples.getSamples()) {
            addElement(sample);
        }
    }
}

class SamplesSelectionListener implements ListSelectionListener {
    private SamplesPanel samples;
    public SamplesSelectionListener(SamplesPanel samples) {
        this.samples = samples;
    }
    public void valueChanged(ListSelectionEvent e) {
    }
}

public class SamplesPanel extends JPanel implements Observer {

	List<Sample> samples;
	
	JLabel lblHeader;
	
	/**
	 * Create the panel.
	 */
	public SamplesPanel() {
		initialize();
        Tr8n.getSession().addObserver(this);
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
		samplesList.addListSelectionListener(new SamplesSelectionListener(this));
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBox.background"));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblHeader = new JLabel("Header");
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
