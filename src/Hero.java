public class Hero extends GameCharacter {

	private final static String IDENTITY = "hero";
	private final static double HERO_SIZE = 25;
	public final static double STARTING_DX = 7.5;
	public final static double STARTING_FLY_SPEED = 10;
	public final static double STARTING_DY = 4.5;
	public final static int INIT_NUM_LIVES = 100;
	private int numOfLives;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param gameComponent
	 * 
	 *                      Initialize Hero.
	 */
	public Hero(double x, double y, GameComponent gameComponent) {
		super(x, y, HERO_SIZE, HERO_SIZE, gameComponent);
		super.dx = STARTING_DX;
		super.flySpeed = STARTING_FLY_SPEED;
		super.dy = STARTING_DY;
		super.identity = IDENTITY;
		this.numOfLives = INIT_NUM_LIVES;
		super.image = new GameObjectImage("hero.png", this);
	}

	public void heroAttack() {
		switch (this.image.filename) {
		case "hero.png":
			this.setImage("heroAttack.png");
			break;
		case "heroLeft.png":
			this.setImage("heroAttackLeft.png");
			break;
		case "heroRight.png":
			this.setImage("heroAttackRight.png");
			break;
		default:
			System.out.println("no such case");
			break;
		}
	}

	public void heroAttackClose() {
		switch (this.image.filename) {
		case "heroAttack.png":
			this.setImage("hero.png");
			break;
		case "heroAttackLeft.png":
			this.setImage("heroLeft.png");
			break;
		case "heroAttackRight.png":
			this.setImage("heroRight.png");
			break;
		default:
			System.out.println("no such case");
			break;

		}
	}
	
	public boolean isCapable() {
		return this.gameComponent.score > 300;
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
