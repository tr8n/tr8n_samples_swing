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

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.tr8n.core.Tr8n;

public class WelcomePanel extends JPanel implements Observer {
	private static final long serialVersionUID = -8261825120743496568L;
	
	JTextPane txtMessage;
	JLabel lblWelcome;

	/**
	 * Create the panel.
	 */
	public WelcomePanel() {
        Tr8n.getSession().addObserver(this);
        initialize();
        translateContent();
	}

	private void initialize() {
		setBackground(Color.WHITE);
		setBorder(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel lblLogo = new JLabel("");
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.insets = new Insets(0, 0, 50, 0);
		gbc_lblLogo.fill = GridBagConstraints.BOTH;
		gbc_lblLogo.gridx = 0;
		gbc_lblLogo.gridy = 0;
		add(lblLogo, gbc_lblLogo);
		lblLogo.setIcon(new ImageIcon(WelcomePanel.class.getResource("/com/tr8n/samples/swing/logo.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblWelcome = new JLabel("Welcome to Tr8n Demo");
		lblWelcome.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.insets = new Insets(10, 0, 15, 0);
		gbc_lblWelcome.fill = GridBagConstraints.BOTH;
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 1;
		add(lblWelcome, gbc_lblWelcome);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtMessage = new JTextPane();
		txtMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtMessage.setContentType("text/html");
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 10, 0);
		gbc_txtMessage.fill = GridBagConstraints.BOTH;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		add(txtMessage, gbc_txtMessage);
		txtMessage.setEditable(false);
		txtMessage.setText(
				"<center>\n" 
				+ "This application demonstrates some of Tr8n's capabilities." 
				+ "<br>" 
				+ "Use the menu on the left to choose a sample."
				+ "</center>"
			);
	}
	
    private void translateContent() {
    	lblWelcome.setText(Tr8n.translate("Welcome to Tr8n Demo"));
		txtMessage.setText(
			"<center>\n" 
			+ Tr8n.translate("This application demonstrates some of Tr8n's capabilities.") 
			+ "<br>" 
			+ Tr8n.translate("Use the menu on the left to choose a sample.") 
			+ "</center>"
		);
    }

    public void update(java.util.Observable observable, java.lang.Object o) {
        translateContent();
    }	
}
