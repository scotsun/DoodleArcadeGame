public class Coin extends GameObject {

	private final double STARTING_DY = 1;
	private final static double COIN_SIZE = 10;
	private long start;
	private final static long FREE_TIME = 4000;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param gameComponent
	 * 
	 * Initialize Egg Game Object.
	 */
	public Coin(double x, double y, GameComponent gameComponent) {
		super(x, y, COIN_SIZE, COIN_SIZE, gameComponent);
		super.image = new GameObjectImage("egg.png", this);
		this.start = System.currentTimeMillis();
	}

	public double getYVelocity() {
		return this.STARTING_DY;
	}
	
	//Check to see if the monster is stuck after 4 seconds.
	public boolean isFree() {
		long check = System.currentTimeMillis(); 
		return (check - this.start) > FREE_TIME;
	}
	
	@Override
	public void tick() {
		this.setY(this.getY() + STARTING_DY);
	}
}
