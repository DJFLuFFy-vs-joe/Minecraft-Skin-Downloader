package nl.vsjoe.app.minecraftskindownloader.listeners;

import nl.vsjoe.app.minecraftskindownloader.utilities.SkinType;

public interface ControlPanelListener {
	public void search(String name, SkinType skinType, int size);
	public void download(String name, SkinType skinType, int size);
}
