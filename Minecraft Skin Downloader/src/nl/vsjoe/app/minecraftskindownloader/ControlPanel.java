package nl.vsjoe.app.minecraftskindownloader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import nl.vsjoe.app.minecraftskindownloader.listeners.ControlPanelListener;
import nl.vsjoe.app.minecraftskindownloader.utilities.SkinType;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = -3565907206411053692L;

	ControlPanelListener listener;

	JTextField usernameField;
	JButton searchButton;
	
	JSpinner sizeSpinner;
	
	JComboBox<SkinType> typeCombo;
	JButton downloadButton;

	public ControlPanel() {

		usernameField = new JTextField(16);
		searchButton = new JButton("Search...");
		typeCombo = new JComboBox<SkinType>();
		downloadButton = new JButton("Download Skin");

		SpinnerNumberModel model = new SpinnerNumberModel(100, 16, 1920, 5);
		sizeSpinner = new JSpinner(model);
		
		DefaultComboBoxModel<SkinType> typeModel = new DefaultComboBoxModel<SkinType>();
		for (SkinType type : SkinType.values()) {
			typeModel.addElement(type);
		}

		typeCombo.setModel(typeModel);
		typeCombo.setSelectedIndex(3);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String name = usernameField.getText();
				SkinType type = (SkinType) typeCombo.getSelectedItem();

				if(name.length() > 1) {
					if(listener != null) {
						listener.search(name, type, 100);
					}
				}

				System.out.println("name : " + name + " type : " + type);			
			}
		});
		
		downloadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = usernameField.getText();
				int size = (Integer) sizeSpinner.getValue();
				SkinType type = (SkinType) typeCombo.getSelectedItem();
				
				if(name.length() > 1 ) {
					if(listener != null) {
						listener.download(name, type, size);
					}
				}
			}
		});
		
		createLayout();
	}

	public void setControlPanelListener(ControlPanelListener listener) {
		this.listener = listener;
	}
	
	private void createLayout() {
		JPanel nameSizePanel = new JPanel();
		nameSizePanel.add(new JLabel("Minecraft Username "));
		nameSizePanel.add(usernameField);
		add(nameSizePanel);
		
		JPanel sizePanel = new JPanel();
		sizePanel.add(new JLabel("Select a Size"));
		sizePanel.add(sizeSpinner);
		add(sizePanel);
		
		JPanel selectTypePanel = new JPanel();
		selectTypePanel.add(new JLabel("Select a skin type: "));
		selectTypePanel.add(typeCombo);
		add(selectTypePanel);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(searchButton);
		buttonsPanel.add(downloadButton);
		add(buttonsPanel);
	}

}
