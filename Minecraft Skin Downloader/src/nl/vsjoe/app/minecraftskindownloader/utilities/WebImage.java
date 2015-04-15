package nl.vsjoe.app.minecraftskindownloader.utilities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class WebImage {

	public void downloadSkin(String name, SkinType type, int size) throws IOException {
		createDirectory("skins");
		String fileName = "skins\\" + name + "_" + type.toString() + "_" + size + ".png";
		
		ImageIcon skinIcon = getWebimage(name, type, size);
		
		Image img = skinIcon.getImage();
		
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
		
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(img, 0, 0, null);
		g2.dispose();
		
		ImageIO.write(bi, "png", new File(fileName));

	}
	
	public ImageIcon getWebimage(String name, SkinType type, int size) {
		
		URL url = getUrl(name, type, size);
		if(url == null) {
			return null;
		}
		
		BufferedImage img = getImage(url);
		if(img == null) {
			return null;
		}
		
		ImageIcon icon = new ImageIcon(img);

		return icon;
	}

	private BufferedImage getImage(URL url) {

		try {
			BufferedImage img = ImageIO.read(url);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private URL getUrl (String name, SkinType skinType, int size ) {
		String urlPath; 
		switch (skinType) {
		case skin:
			urlPath = Ref.BASE_URL + skinType.toString() + Ref.SLASH + name + Ref.PNG ;
			break;

		default:
			urlPath = Ref.BASE_URL + skinType.toString() + Ref.SLASH + name + Ref.SLASH + size + Ref.PNG ;
			break;
		}

		URL url;

		try {
			url = new URL(urlPath);
			return url;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void createDirectory(String directory) {
		File file = new File(directory);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println(directory + " is created!");
			} else {
				System.out.println("Failed to create " + directory);
			}
		}
	}
}
