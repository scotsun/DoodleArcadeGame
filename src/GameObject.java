
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class GameObject {
	private double x;
	private double y;
	private double width;
	private double height;
	protected GameComponent gameComponent;
	protected GameObjectImage image;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param gameComponent
	 * 
	 * Initialize GameObject.
	 */
	public GameObject(double x, double y, double width, double height, GameComponent gameComponent) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gameComponent = gameComponent;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	public Rectangle2D.Double getBoxObject() {
		return new Rectangle2D.Double(x, y, width, height);
	}

	public boolean isOffScreen() {
		boolean xLow = x + width < 0;
		boolean xHigh = x + width > gameComponent.getWidth();
		boolean yLow = y < 0;
		boolean yHigh = y + height > gameComponent.getHeight();
		return xLow || xHigh || yLow || yHigh;
	}

	public boolean overlaps(GameObject other) {
		try {
			return this.getBoxObject().intersects(other.getBoxObject());
		} catch (NullPointerException e) {
			return false;
		}		
	}

	public abstract void tick();

	public void drawOn(Graphics2D g2) {
		g2.drawImage(this.image.getBufferedImage(), (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(),
				null);
	}

}
