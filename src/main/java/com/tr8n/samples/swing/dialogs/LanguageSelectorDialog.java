package com.tr8n.samples.swing.dialogs;

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
	private static final long serialVersionUID = -2740202087399780686L;
	
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
	private static final long serialVersionUID = 1833026797055469630L;

	public LanguageSelectorListModel(LanguageSelectorDialog languageSelector) {
        for (Language language : Tr8n.getApplication().getLanguages()) {
            addElement(language);
        }
    }
}

class LanguageSelectorSelectionListener implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
    }
}


public class LanguageSelectorDialog extends JDialog {
	private static final long serialVersionUID = -2478492556043913311L;
	
	private final JPanel contentPanel = new JPanel();
	private JList languageList;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LanguageSelectorDialog dialog = new LanguageSelectorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LanguageSelectorDialog() {
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
		        languageList.setCellRenderer(new LanguageSelectorCellRenderer());
		        languageList.addListSelectionListener(new LanguageSelectorSelectionListener());
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
        Tr8n.switchLanguage(language);
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}