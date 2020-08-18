import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameObjectImage {
	
	private Image image;
	private String filename;
	private GameCharacter object;
	
	/**
	 * 
	 * @param filename
	 * @param gameObject
	 * 
	 * Initialize GameObjectImage.
	 */
	public GameObjectImage(String filename, GameObject gameObject) {
		this.filename = filename;
		try {
			this.image = ResourceLoader.getImage(filename);
		} catch (NullPointerException e) {
			System.out.println("Could not load image file " + this.filename);
		}
	}
	
	public double getX() {
		return this.object.getX();
	}
	
	public double getY() {
		return this.object.getY();
	}
	
	public double getWidth() {
		return this.object.getWidth();
	}
	
	public double getHeight() {
		return this.object.getHeight();
	}
	
	public Image getImage() {
		return this.image;
	}
	
}
