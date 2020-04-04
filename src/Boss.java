import java.awt.Graphics2D;

public class Boss extends GameCharacter {

	private final static String IDENTITY = "boss";
	private final static double BOSS_SIZE = 75;
	private final static double STARTING_DX = 2.5;
	private final static double STARTING_FLYSPEED = 30;
	private final static double STARTING_DY = 4;
	private GameObjectImage image;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param gameComponent
	 * 
	 * Initializes the Boss GameCharacter.
	 */
	public Boss(double x, double y, GameComponent gameComponent) {
		super(x, y, BOSS_SIZE, BOSS_SIZE, gameComponent);
		super.dx = STARTING_DX;
		super.flySpeed = STARTING_FLYSPEED;
		super.dy = STARTING_DY;
		super.identity = IDENTITY;
		this.image = new GameObjectImage("bossMoveLeft.png", this);
	}

	@Override
	public void setImage(String s) {
		this.image = new GameObjectImage(s, this);
	}

	@Override
	public void tick() {
		// Do Nothing
	}

	@Override
	public void drawOn(Graphics2D g2) {
		g2.drawImage(this.image.getBufferedImage(), (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(),
				null);
	}

}
