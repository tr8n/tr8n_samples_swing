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

package com.translationexchange.samples.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.net.URI;
import java.util.Observer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.translationexchange.swing.Tml;
import com.translationexchange.samples.swing.dialogs.LanguageSelectorDialog;

class MenuListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = -7163313137041127573L;
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (index == 0 || index == 5) {
            label.setFont(new Font("Helvetica", Font.PLAIN, 16));
            label.setBorder(new EmptyBorder(10, 10, 10, 10));
            label.setBackground(new Color(214, 214, 214));
            label.setForeground(Color.BLACK);
            label.setBorder(new EmptyBorder(15, 10, 15, 10));
            return label;
        }

        label.setFont(new Font("Helvetica", Font.PLAIN, 13));
        label.setBorder(new EmptyBorder(10, 10, 10, 10));

        if (isSelected) {
            label.setBackground(new Color(228, 228, 228));
            label.setForeground(Color.gray);
        } else {
            label.setBackground(Color.WHITE);
            label.setForeground(Color.GRAY);
        }

        return label;
    }
}

class MenuListModel extends DefaultListModel implements Observer {
	private static final long serialVersionUID = -8606017570226135306L;
    public MenuListModel() {
        Tml.getSession().addObserver(this);
        translateOptions();
    }

    private void translateOptions() {
        removeAllElements();
        addElement(Tml.translate("Main Menu"));
        addElement(Tml.translate("Welcome"));
        addElement(Tml.translate("Data Tokens Demo"));
        addElement(Tml.translate("Decoration Tokens Demo"));
        addElement(Tml.translate("Combined Tokens Demo"));
        addElement(Tml.translate("Languages"));
        addElement(Tml.translate("Change Language"));
        addElement(Tml.translate("Open Translator"));
    }

    public void update(java.util.Observable observable, java.lang.Object o) {
        translateOptions();
    }
}

class MenuSelectionListener implements ListSelectionListener {
    private Application application;
    private int previousIndex = 1;

    public MenuSelectionListener(Application application) {
        this.application = application;
    }
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == true)
            return;
        JList list = (JList)e.getSource();
        if (list.getSelectedIndex() == 6) {
            LanguageSelectorDialog dialog = new LanguageSelectorDialog();
            dialog.pack();
            dialog.setVisible(true);
            list.setSelectedIndex(previousIndex);
            return;
        }

        if (list.getSelectedIndex() == 7) {
            try {
                Desktop.getDesktop().browse(new URI(Tml.getSession().getApplication().getHost()));
            } catch (Exception ex) {
            }
            list.setSelectedIndex(previousIndex);
            return;
        }

        if (list.getSelectedIndex() == 1) {
            previousIndex = list.getSelectedIndex();
            application.switchPanel("com.translationexchange.samples.swing.panels.WelcomePanel");
            return;
        }

        if (list.getSelectedIndex() == 2) {
            previousIndex = list.getSelectedIndex();
            application.switchPanel("com.translationexchange.samples.swing.panels.DataTokenSamplesPanel");
            return;
        }

        if (list.getSelectedIndex() == 3) {
            previousIndex = list.getSelectedIndex();
            application.switchPanel("com.translationexchange.samples.swing.panels.DecorationTokenSamplesPanel");
            return;
        }
        
        if (list.getSelectedIndex() == 4) {
            previousIndex = list.getSelectedIndex();
            application.switchPanel("com.translationexchange.samples.swing.panels.CombinedTokensSamplesPanel");
            return;
        }
    }
}


public class Application {

	private JFrame frame;
	private JPanel containerPanel;
	private JList menuList;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		// TODO: change to the new init
		Tml.init("19d88c916db183f90", "641281a8c72f37415", "https://sandbox.tr8nhub.com");
        
		initialize();
		
        menuList.setModel(new MenuListModel());
        menuList.setCellRenderer(new MenuListCellRenderer());
        menuList.addListSelectionListener(new MenuSelectionListener(this));
        menuList.setSelectedIndex(1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Tml.translate("Tr8n Sample Application"));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		menuList = new JList();
		scrollPane.setViewportView(menuList);

        containerPanel = new JPanel();
        splitPane.setRightComponent(containerPanel);
        containerPanel.setLayout(new BorderLayout(0, 0));
	}
	
	 public void switchPanel(String className) {
        try {
            JPanel frame = (JPanel) Class.forName(className).getConstructor().newInstance();
            containerPanel.removeAll();
            containerPanel.add(frame, BorderLayout.CENTER);
            containerPanel.updateUI();
        } catch (Exception ex) {
        	Tml.getLogger().logException(ex);
        }
	 }

}
