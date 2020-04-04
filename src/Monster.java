public class Monster extends GameCharacter {

	private final static String IDENTITY = "monster";
	private final static double MONSTER_SIZE = 25;
	private double STARTING_DX = 2;
	private double STARTING_FLY_SPEED = 3;
	private double STARTING_DY = 0;
	private boolean isShooter;
	private boolean isLeft;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param shooter
	 * @param gameComponent
	 * 
	 * Initialize Monster.
	 */
	public Monster(double x, double y, boolean shooter, GameComponent gameComponent) {
		super(x, y, MONSTER_SIZE, MONSTER_SIZE, gameComponent);
		super.dx = STARTING_DX;
		super.flySpeed = STARTING_FLY_SPEED;
		super.dy = STARTING_DY;
		super.identity = IDENTITY;
		this.isShooter = shooter;
		this.isLeft = (Math.random() < 0.5);
		super.image = new GameObjectImage("monster.png", this);

	}

	public boolean isLeft() {
		return this.isLeft;
	}

	public boolean isShooter() {
		return this.isShooter;
	}

	public void setShooter() {
		this.isShooter = true;
	}
	
	@Override
	public void setImage(String s) {
		// TODO Auto-generated method stub
		this.image = new GameObjectImage(s, this);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
