package nl.vsjoe.app.minecraftskindownloader;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import nl.vsjoe.app.minecraftskindownloader.listeners.ControlPanelListener;
import nl.vsjoe.app.minecraftskindownloader.utilities.Ref;
import nl.vsjoe.app.minecraftskindownloader.utilities.SkinType;
import nl.vsjoe.app.minecraftskindownloader.utilities.WebImage;

public class MinecraftSkinDownloaderMain extends JFrame {

	private static final long serialVersionUID = -8626565810132508240L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MinecraftSkinDownloaderMain();
			}
		});
	}

	SkinPanel skinPanel;
	ControlPanel controlPanel;

	public MinecraftSkinDownloaderMain() {
		super(Ref.TITLE);

		skinPanel = new SkinPanel();
		controlPanel = new ControlPanel();

		controlPanel.setControlPanelListener(new ControlPanelListener() {
			@Override
			public void search(String name, SkinType skinType, int size) {
				skinPanel.setSkin(name, skinType, size);				
			}

			@Override
			public void download(String name, SkinType skinType, int size) {
				skinPanel.setSkin(name, skinType, 100);
				if(skinPanel.isDownloadAble()) {
					try {
						new WebImage().downloadSkin(name, skinType, size);
					} catch (IOException e) {
						e.printStackTrace();
						error("Unable to Download Skin, Try again later");
					}		
				} else {
					error("This User does not exist!!!");
				}
			}
		});

		setLayout(new BorderLayout());

		add(skinPanel, BorderLayout.WEST);
		add(controlPanel, BorderLayout.CENTER);

		setSize(480, 360);
		setLocation(480, 320);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void error(String error) {
		JOptionPane.showMessageDialog(MinecraftSkinDownloaderMain.this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
