package nl.vsjoe.app.minecraftskindownloader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import nl.vsjoe.app.minecraftskindownloader.utilities.SkinType;
import nl.vsjoe.app.minecraftskindownloader.utilities.WebImage;

public class SkinPanel extends JPanel{
	
	String steve = "images\\steve.png";

	private static final long serialVersionUID = 3334541378674751222L;
	
	JLabel skinLabel;
	JLabel nameLabel, typeLabel;
	Border innerBorder;
	
	public SkinPanel() {

		skinLabel = new JLabel(new ImageIcon(getSkin(steve)));
		nameLabel = new JLabel("Steve");
		typeLabel = new JLabel(SkinType.body.toString());
		
		int space = 5;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		innerBorder = BorderFactory.createTitledBorder("Skin Viewer");
		setBorder(BorderFactory.createCompoundBorder(spaceBorder, innerBorder));
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		createLayout();
			
	}
	
	public void setSkin(String name, SkinType type, int size) {
		
		ImageIcon skin = new WebImage().getWebimage(name, type, size);
		
		if(skin != null) {
			skinLabel.setIcon(skin);
			nameLabel.setText(name);
			typeLabel.setText("Skintype : " + type.toString());
		} else {
			getSkin(steve);
			nameLabel.setText("Unable to Find" + name);
			typeLabel.setText("error");
		}
	}
	
	private BufferedImage getSkin(String url) {
		BufferedImage skinImage = null;
		try {
			skinImage = ImageIO.read(new File(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return skinImage;
	}
	
	public boolean isDownloadAble() {
		if(typeLabel.getText() == "error") {
			return false;
		}
		return true;
	}
	
	private void createLayout() {
		JPanel imagePanel = new JPanel();
		imagePanel.add(skinLabel);
		add(imagePanel);
		
		JPanel namePanel = new JPanel();
		namePanel.add(nameLabel);
		add(namePanel);
		
		JPanel typePanel = new JPanel();
		typePanel.add(typeLabel);
		add(typePanel);
	}
}
