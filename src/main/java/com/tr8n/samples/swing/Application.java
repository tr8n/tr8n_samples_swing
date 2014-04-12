package com.tr8n.samples.swing;

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

import com.tr8n.core.Tr8n;
import com.tr8n.samples.swing.dialogs.LanguageSelectorDialog;

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
        Tr8n.getSession().addObserver(this);
        translateOptions();
    }

    private void translateOptions() {
        removeAllElements();
        addElement(Tr8n.translate("Main Menu"));
        addElement(Tr8n.translate("Welcome"));
        addElement(Tr8n.translate("Data Tokens Demo"));
        addElement(Tr8n.translate("Decoration Tokens Demo"));
        addElement(Tr8n.translate("Combined Tokens Demo"));
        addElement(Tr8n.translate("Languages"));
        addElement(Tr8n.translate("Change Language"));
        addElement(Tr8n.translate("Open Translator"));
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
                Desktop.getDesktop().browse(new URI(Tr8n.getSession().getApplication().getHost()));
            } catch (Exception ex) {
            }
            list.setSelectedIndex(previousIndex);
            return;
        }

        if (list.getSelectedIndex() == 1) {
            previousIndex = list.getSelectedIndex();
            application.switchPanel("com.tr8n.samples.swing.panels.WelcomePanel");
            return;
        }

        if (list.getSelectedIndex() == 2) {
            previousIndex = list.getSelectedIndex();
            application.switchPanel("com.tr8n.samples.swing.panels.DataTokenSamplesPanel");
            return;
        }

        if (list.getSelectedIndex() == 3) {
            previousIndex = list.getSelectedIndex();
            application.switchPanel("com.tr8n.samples.swing.panels.DecorationTokenSamplesPanel");
            return;
        }
        
        if (list.getSelectedIndex() == 4) {
            previousIndex = list.getSelectedIndex();
            application.switchPanel("com.tr8n.samples.swing.panels.CombinedTokensSamplesPanel");
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
        Tr8n.init("37f812fac93a71088", "a9dc95ff798e6e1d1", "https://sandbox.tr8nhub.com");
        Tr8n.getConfig().addTokenizerClass("styled", "com.tr8n.swing.tokenizers.AttributedStringTokenizer");
        
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
		frame.setTitle(Tr8n.translate("Tr8n Sample Application"));

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
        	Tr8n.getLogger().logException(ex);
        }
	 }

}
