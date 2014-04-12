package com.tr8n.samples.swing.components;

import java.awt.Graphics;
import java.text.AttributedString;

import javax.swing.JLabel;

public class AttributedLabel extends JLabel {

	private static final long serialVersionUID = -444379454541307655L;
	
	AttributedString attributedString;

    public AttributedLabel() {
    	super();
    }
    
    public AttributedLabel(AttributedString as){
        super(as.toString());
        this.attributedString = as;
    }

    @Override
    public void paintComponent(Graphics g){
    	if (attributedString != null) {
    		g.drawString(attributedString.getIterator(), getInsets().left, getInsets().top + 12);
    	} else {
    		super.paintComponent(g);
    	}
    }

}

