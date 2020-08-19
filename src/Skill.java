
public class Skill extends GameObject {
	
	private static final double SKILL_SPEED = 5;
	private static final double SKILL_SIZE = 15;
	private Hero hero;
	private boolean isLeft;
	
	public Skill(double x, double y, Hero hero, GameComponent gameComponent) {
		super(x, y, SKILL_SIZE, SKILL_SIZE, gameComponent);
		this.hero = hero;
		
		//determine direction
		String heroImageName = hero.image.filename;
		if (heroImageName.equals("hero.png") || heroImageName.equals("heroLeft.png")) {
			this.isLeft = true;
			super.image = new GameObjectImage("skillLeft.png", this);
		} else if (heroImageName.equals("heroRight.png")) {
			this.isLeft = false;
			super.image = new GameObjectImage("skillRight.png", this);
		} else {
			System.out.println("no image");
		}
	}
	
	@Override
	public void tick() {
		if (this.isLeft) {
			this.setX(this.getX() - SKILL_SPEED);
		} else {
			this.setX(this.getX() + SKILL_SPEED);
		}
	}

}
