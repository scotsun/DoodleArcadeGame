import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class TitleScreenComponent extends JComponent {
	private Image image;
	
	/**
	 * 
	 * @param image
	 * 
	 * Initialize TitleScreenComponent.
	 */
	public TitleScreenComponent(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;
		graphics2.drawImage(image, 0, 0, null);
		repaint();
	}
}