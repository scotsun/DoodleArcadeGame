import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class TitleScreenComponent extends JComponent {
	private BufferedImage image;
	
	/**
	 * 
	 * @param image
	 * 
	 * Initialize TitleScreenComponent.
	 */
	public TitleScreenComponent(BufferedImage image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;
		graphics2.drawImage(image, null, 0, 0);

	}
}