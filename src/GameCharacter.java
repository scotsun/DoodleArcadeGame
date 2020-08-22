
public abstract class GameCharacter extends GameObject {

	protected double dx;
	protected double flySpeed;
	protected double dy;
	protected String identity;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param gameComponent
	 * 
	 *                      Initialize Game Character Game Object.
	 */
	public GameCharacter(double x, double y, double width, double height, GameComponent gameComponent) {
		super(x, y, width, height, gameComponent);
	}

	public double getXVelocity() {
		return this.dx;
	}

	public double getFlySpeed() {
		return this.flySpeed;
	}

	public double getYVelocity() {
		return this.dy;
	}

	public void setXVelocity(double dx) {
		this.dx = dx;
	}

	public void setFlySpeed(double flySpeed) {
		this.flySpeed = flySpeed;
	}

	public void setYVelocity(double dy) {
		this.dy = dy;
	}

	public String getIdentity() {
		return this.identity;
	}

	public abstract void setImage(String s);

}
