import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GameComponent extends JComponent {

	private JLabel infoLabel;
	protected int score;
	protected final static int BOSS_FIGHT_SCORE = 300;

	private Image background;

	private int curLevelNumber;
	private Level curLevel;

	private Hero hero;
	private Boss boss;
	private ArrayList<Monster> monsters;
	private ArrayList<Coin> coins;
	private ArrayList<GameCharacter> allCharacters;
	private ArrayList<Environment> environments;
	private ArrayList<Bullet> bullets;
	private ArrayList<Skill> skills;

	/**
	 * 
	 * @param infoLabel
	 * 
	 *                  Initialize GameComponent.
	 */
	public GameComponent(JLabel infoLabel) {
		this.infoLabel = infoLabel;
		this.score = 0;

		try {
			this.background = ResourceLoader.getImage("background.png");
		} catch (NullPointerException e) {
			System.out.println("file background.png cannot be found");
		}

		this.curLevelNumber = 0;
		this.curLevel = new Level(this.curLevelNumber, this);
		this.hero = this.curLevel.getHero();
		this.boss = this.curLevel.getBoss();
		this.monsters = this.curLevel.getMonsters();
		this.allCharacters = new ArrayList<GameCharacter>();
		this.allCharacters.add(hero);
		this.allCharacters.add(boss);
		this.allCharacters.addAll(monsters);
		this.environments = this.curLevel.getEnvironments();
		this.coins = new ArrayList<Coin>();
		this.bullets = new ArrayList<Bullet>();
		this.skills = new ArrayList<Skill>();
	}

	// Update Score and Lives on top of screen.
	public void updateInfoLabel() {
		String changeTo = "Score: " + Integer.toString(score) + "     |     Lives: "
				+ Integer.toString(this.hero.getNumOfLives());
		this.infoLabel.setText(changeTo);
	}

	// Reset Lives
	public void updateInfoLabelResetNumLives() {
		String changeTo = "Score: " + Integer.toString(score) + "     |     Lives: "
				+ Integer.toString(Hero.INIT_HEALTH);
		this.infoLabel.setText(changeTo);
	}

	// Helper function for updating Level.
	public void updateLevelHelper() {
		this.curLevel = new Level(this.curLevelNumber, this);
		this.hero = this.curLevel.getHero();
		this.boss = this.curLevel.getBoss();
		this.monsters = this.curLevel.getMonsters();
		this.allCharacters = new ArrayList<GameCharacter>();
		this.allCharacters.add(hero);
		this.allCharacters.add(boss);
		this.allCharacters.addAll(monsters);
		this.environments = this.curLevel.getEnvironments();
		this.coins = new ArrayList<Coin>();
		this.bullets = new ArrayList<Bullet>();
		this.skills = new ArrayList<Skill>();
	}

	public void turnToPreviousLevel() {
		System.out.println("turn to prev");
		if (this.curLevelNumber == 0) {
			System.out.println("There is no previous level.");
			return;
		}
		this.updateInfoLabelResetNumLives();
		this.curLevelNumber += -1;
		this.updateLevelHelper();
	}

	public void turnToNextLevel() { // TODO:
		System.out.println("turn to next");
		if (this.curLevelNumber == 3) {
			System.out.println("There is no next level.");
			return;
		}
		this.updateInfoLabelResetNumLives();
		this.curLevelNumber += 1;
		this.updateLevelHelper();
	}

	public void turnToEndLevel() {
		this.curLevelNumber = 99;
		this.curLevel = new Level(this.curLevelNumber, this);
		this.hero = null;
		this.boss = null;
		this.monsters = new ArrayList<>();
		this.allCharacters = new ArrayList<>();
		this.environments = new ArrayList<>();
		this.coins = new ArrayList<>();
		this.bullets = new ArrayList<>();
		this.skills = new ArrayList<>();
	}

	public boolean isTouchingEnvironment(GameObject object) {
		ArrayList<Environment> environments = this.environments;
		for (Environment e : environments) {
			if (e.overlaps(object)) {
				return true;
			}
		}
		return false;
	}

	public void moveLeftOrRight(GameCharacter character, boolean isLeft) { // TODO:
		double dx = character.getXVelocity();
		if (isLeft) {
			character.setX(character.getX() - dx);
			character.setImage(character.getIdentity() + "Left.png");
			if (this.isTouchingEnvironment(character)) {
				character.setX(character.getX() + dx);
			}
		} else {
			character.setX(character.getX() + dx);
			character.setImage(character.getIdentity() + "Right.png");
			if (this.isTouchingEnvironment(character)) {
				character.setX(character.getX() - dx);
			}
		}
		// This wraps the characters around the map when they leave the frame.
		double origY = character.getY();
		if (character.getX() < 0) {
			character.setX(this.getWidth());
			character.setY(origY);
		} else if (character.getX() > this.getWidth()) {
			character.setX(0);
			character.setY(origY);
		}
	}

	public void heroMoveLeftOrRight(boolean isLeft) {
		try {
			moveLeftOrRight(this.hero, isLeft);
		} catch (NullPointerException e) {
		}
	}

	public void fly(GameCharacter character) {
		double flySpeed = character.getFlySpeed();
		character.setYVelocity(1.5);
		character.setY(character.getY() - flySpeed);
		// Sets boundary around the environment blocks.
		if (this.isTouchingEnvironment(character)) {
			character.setY(character.getY() + flySpeed);
		}
		if (character.getY() < 0) {
			character.setY(0);
		}
	}

	public void heroAttack() {
		this.updateSkill();
		hero.heroAttack();
	}

	public void updateSkill() {
		this.skills.add(new Skill(hero.getX() + hero.getWidth() / 2, hero.getY() + hero.getHeight() / 2, hero, this));
	}

	public void heroAttackClose() {
		hero.heroAttackClose();
	}

	public void heroFly() {
		try {
			fly(this.hero);
		} catch (NullPointerException e) {
		}
	}

	// Helper function for fall and flying to not change fly speed.
	public void resetHeroDy() {
		try {
			this.hero.setYVelocity(Hero.STARTING_DY);
		} catch (NullPointerException e) {
		}

	}

	public void heroReset() {
		int[] initCoord = this.curLevel.getHeroCoords();
		this.hero.setX(initCoord[0] * 25);
		this.hero.setY(initCoord[1] * 25);
		this.hero.HeroDies();
		this.updateInfoLabel();
		System.out.println("You have " + hero.getNumOfLives() + " lives left");
		if (hero.getNumOfLives() == 0) {
			System.out.println("The END");
			this.hero = null;
		}
	}

	public void fall() {
		try {
			for (GameCharacter character : this.allCharacters) {
				double dy = character.getYVelocity();
				character.setY(character.getY() + dy);
				if (character.getY() > this.getHeight()) {
					character.setY(0);
				}
				if (this.isTouchingEnvironment(character)) {
					character.setY(character.getY() - dy);
				}
			}
		} catch (NullPointerException e) {
			return;
		}
	}

	public void bossMove() {
		boss.setX(boss.getX() - boss.getXVelocity());
		if (boss.getX() < 50) {
			boss.setXVelocity(-boss.getXVelocity());
			boss.setImage("bossMoveRight.png");
		}
		if (boss.getX() > 500) {
			boss.setXVelocity(-boss.getXVelocity());
			boss.setImage("bossMoveLeft.png");
		}
		if (boss.getX() == 270) {
			this.bossMoveHelper();
		}

	}

	// Random chance at throwing monster when in the middle of the map.
	public void bossMoveHelper() {
		double dx = boss.getXVelocity();
		this.boss.setXVelocity(0);
		this.fly(boss);
		if (Math.random() < 0.7) {
			this.monsters.add(new Monster(this.getWidth() / 2, this.getHeight() / 2, Math.random() < 0.5, this));
		}
		if (dx > 0) {
			boss.setImage("bossStandLeft.png");
		} else {
			boss.setImage("bossStandRight.png");
		}
		this.boss.setXVelocity(dx);
	}

	public void monstersMove() {
		try {
			for (int i = 0; i < this.monsters.size(); i++) {
				Monster monster = (Monster) this.monsters.get(i);
				double flySpeed = monster.getFlySpeed();

				this.moveLeftOrRight(monster, monster.isLeft());
				monster.setY(monster.getY() - flySpeed);

				if (this.isTouchingEnvironment(monster)) {
					monster.setFlySpeed(-flySpeed);
				}
				if (monster.getY() < 0) {
					monster.setFlySpeed(-flySpeed);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}

	public void updateBullet() {
		try {
			if (Math.random() < 0.01) {
				for (Monster monster : this.monsters) {
					if (monster.isShooter()) {
						this.bullets.add(new Bullet(monster.getX() + monster.getWidth() / 2,
								monster.getY() + monster.getHeight() / 2, monster, this));
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public void bulletMove() {
		for (Bullet bullet : bullets) {
			bullet.tick();
		}
	}

	public void skillMove() {
		for (Skill skill : skills) {
			skill.tick();
		}
	}

	public void updateEgg(Monster monster) {
		this.coins.add(new Coin(monster.getX(), monster.getY(), this));
	}

	public void eggMove() {
		for (Coin coin : this.coins) {
			coin.tick();
			if (this.isTouchingEnvironment(coin)) {
				coin.setY(coin.getY() - coin.getYVelocity());
			}
		}
	}

	public void handleCollisions() {
		List<Bullet> bulletsToRemove = new ArrayList<Bullet>();
		List<Skill> skillsToRemove = new ArrayList<Skill>();
		List<Coin> eggsToRemove = new ArrayList<Coin>();
		List<GameCharacter> monstersToRemove = new ArrayList<GameCharacter>();

		// joust: character with lower position would be killed
		for (Monster monster : this.monsters) {
			if (monster.overlaps(hero)) {
				if (monster.getY() > hero.getY()) {
					monstersToRemove.add(monster);
					this.score += 10;
					this.updateInfoLabel();
					this.updateEgg(monster);

				} else {
					heroReset();
				}
			}
		}

		// TODO:
		try {
			if (boss.overlaps(hero)) {
				if (!hero.isCapable()) {
					this.heroReset();
				} else {
					this.score += 300;
					this.updateInfoLabel();
					this.boss = null;
				}
			}
		} catch (NullPointerException e) {
		}

		// Switch level when all enemies are dead.
		if (monsters.isEmpty() && coins.isEmpty() && curLevelNumber < 4) {
			if (curLevelNumber == 3) {
				this.turnToEndLevel();
			} else {
				this.turnToNextLevel();
			}
		}

		// egg: check for collision between egg and hero AND free monster from egg
		for (Coin coin : this.coins) {
			if (coin.overlaps(hero)) {
				eggsToRemove.add(coin);
				this.score += 20;
				this.updateInfoLabel();
			} else if (coin.isFree()) {
				eggsToRemove.add(coin);
				this.monsters.add(new Monster(coin.getX(), coin.getY() - 20, true, this));
			}
		}

		// skill: skills that hit on environment and are off boundary will disappear
		for (Skill tempSkill : this.skills) {

			// if skill is off screen, then remove
			boolean shouldRemove = tempSkill.isOffScreen();
			if (shouldRemove) {
				skillsToRemove.add(tempSkill);
			}
			// if skill hits environment, then remove
			for (Environment env : this.environments) {
				boolean overlapEnvironment = tempSkill.overlaps(env);
				if (overlapEnvironment) {
					skillsToRemove.add(tempSkill);
				}
			}
			// if skill hits monster, then both remove
			for (Monster mon : this.monsters) {
				boolean hitsMonster = tempSkill.overlaps(mon);
				if (hitsMonster) {
					skillsToRemove.add(tempSkill);
					monstersToRemove.add(mon);
					updateEgg(mon);
				}
			}
		}

		// bullet: bullets that hit on environment and are off boundary will disappear
		for (Bullet tempBullet : this.bullets) {

			// if bullet is off screen, then remove
			boolean shouldRemove = tempBullet.isOffScreen();
			if (shouldRemove) {
				bulletsToRemove.add(tempBullet);
			}
			// if bullet hits environment, then remove
			for (int i = 0; i < this.environments.size(); i++) {
				boolean overlapEnvironment = tempBullet.overlaps(this.environments.get(i));
				if (overlapEnvironment) {
					bulletsToRemove.add(tempBullet);
				}
			}
			// if bullet hits hero, then remove/reset hero
			boolean overlapHero = tempBullet.overlaps(this.hero);
			if (overlapHero) {
				bulletsToRemove.add(tempBullet);
				heroReset();
			}

		}
		this.monsters.removeAll(monstersToRemove);
		this.bullets.removeAll(bulletsToRemove);
		this.coins.removeAll(eggsToRemove);
		this.skills.removeAll(skillsToRemove);

	}

	public void drawScreen() {
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(background, 0, 0, null);
		for (Environment e : this.environments) {
			e.drawOn(g2);
		}
		for (Monster gc : this.monsters) {
			gc.drawOn(g2);
		}
		for (Coin eg : this.coins) {
			eg.drawOn(g2);
		}
		for (Bullet bullet : this.bullets) {
			bullet.drawOn(g2);
		}
		for (Skill skill : this.skills) {
			skill.drawOn(g2);
		}
		try {
			boss.drawOn(g2);
		} catch (NullPointerException e) {
		}
		try {
			hero.drawOn(g2);
		} catch (NullPointerException e0) {
			this.turnToEndLevel();
			Image gameover = null;
			try {
				gameover = ResourceLoader.getImage("gameover.png");
			} catch (NullPointerException e) {
				System.out.println("!");
			}
			g2.drawImage(gameover, this.getWidth() / 2 - 315 / 2, this.getHeight() / 2 - 160 / 2, null);
		}
	}

}