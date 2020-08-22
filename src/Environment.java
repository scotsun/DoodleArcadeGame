public class Environment extends GameObject {

	private static final int BLOCK_SIZE = 25;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param gameComponent
	 * 
	 *                      Initialize Environment Game Object.
	 */
	public Environment(double x, double y, GameComponent gameComponent) {
		super(x, y, BLOCK_SIZE, BLOCK_SIZE, gameComponent);
		super.image = new GameObjectImage("environment.png", this);
	}

	@Override
	public void tick() {

	}
}
