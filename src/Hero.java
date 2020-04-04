public class Hero extends GameCharacter {

	private final static String IDENTITY = "hero";
	private final static double HERO_SIZE = 25;
	public final static double STARTING_DX = 7.5;
	public final static double STARTING_FLY_SPEED = 10;
	public final static double STARTING_DY = 4.5;
	public final static int INIT_NUM_LIVES = 5;
	private int numOfLives;
	private boolean powerUp;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param gameComponent
	 * 
	 * Initialize Hero.
	 */
	public Hero(double x, double y, GameComponent gameComponent) {
		super(x, y, HERO_SIZE, HERO_SIZE, gameComponent);
		super.dx = STARTING_DX;
		super.flySpeed = STARTING_FLY_SPEED;
		super.dy = STARTING_DY;
		super.identity = IDENTITY;
		this.numOfLives = INIT_NUM_LIVES;
		this.powerUp = false;
		super.image = new GameObjectImage("hero.png", this);
	}
	
	
	public void HeroPowerUp() {
		if (super.gameComponent.score >= GameComponent.BOSS_FIGHT_SCORE) {
			super.identity = "heroPowerUp";
			this.powerUp = true;
			this.setImage("heroPowerUp.png");
		} else {
			System.out.println("Not yet...");
		}
	}
	
	public boolean isPoweredUp() {
		return this.powerUp;
	}
	
	public void HeroDies() {
		this.numOfLives--;
	}

	public int getNumOfLives() {
		return this.numOfLives;
	}

	public GameObjectImage getImage() {
		return this.image;
	}

	@Override
	public void setImage(String s) {
		this.image = new GameObjectImage(s, this);
	}

	@Override
	public void tick() {

	}

}
