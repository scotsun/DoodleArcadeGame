import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameObject {

	private static final double BULLET_SPEED = 10;
	private static final double BULLET_DIAMETER = 10;
	private static final Color BULLET_COLOR = Color.RED;
	private Monster shooter;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param shooter
	 * @param gameComponent
	 * 
	 * Initialize Bullet Game Object.
	 */
	public Bullet(double x, double y, Monster shooter, GameComponent gameComponent) {
		super(x, y, BULLET_DIAMETER / 2, BULLET_DIAMETER / 2, gameComponent);
		this.shooter = shooter;
	}

	@Override
	public void tick() {
		if (this.shooter.isLeft()) {
			this.setX(this.getX() - BULLET_SPEED);
		} else {
			this.setX(this.getX() + BULLET_SPEED);
		}
	}
	
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(BULLET_COLOR);
		g2.fill(this.getBoxObject());
	}

}
