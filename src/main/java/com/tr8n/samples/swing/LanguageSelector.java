package com.tr8n.samples.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.tr8n.core.Language;
import com.tr8n.core.Tr8n;

class LanguageSelectorCellRenderer extends DefaultListCellRenderer {
    private LanguageSelector languageSelector;
    public LanguageSelectorCellRenderer(LanguageSelector languageSelector) {
        this.languageSelector = languageSelector;
    }
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

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

class LanguageSelectorListModel extends DefaultListModel {
    private LanguageSelector languageSelector;
    public LanguageSelectorListModel(LanguageSelector languageSelector) {
        this.languageSelector = languageSelector;
        for (Language language : Tr8n.getInstance().getApplication().getLanguages()) {
            addElement(language);
        }
    }
}

class LanguageSelectorSelectionListener implements ListSelectionListener {
    private LanguageSelector languageSelector;
    public LanguageSelectorSelectionListener(LanguageSelector languageSelector) {
        this.languageSelector = languageSelector;
    }
    public void valueChanged(ListSelectionEvent e) {
    }
}


public class LanguageSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList languageList;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LanguageSelector dialog = new LanguageSelector();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LanguageSelector() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				languageList = new JList();
		        languageList.setModel(new LanguageSelectorListModel(this));
		        languageList.setCellRenderer(new LanguageSelectorCellRenderer(this));
		        languageList.addListSelectionListener(new LanguageSelectorSelectionListener(this));
				scrollPane.setViewportView(languageList);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                onOK();
		            }
		        });				
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                onCancel();
		            }
		        });
			}
		}
	}

	private void onOK() {
        Language language = (Language) languageList.getSelectedValue();
        Tr8n.getInstance().switchLanguage(language);
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
