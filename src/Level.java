import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {

	private int levelNumber;
	private GameComponent gameComponent;

	private int[] heroCoordinate;
	private int[] bossCoordinate;
	private ArrayList<int[]> environmentCoordinates;
	private ArrayList<int[]> monsterCoordinates;

	private Hero hero;
	private Boss boss;
	private ArrayList<Monster> monsters;
	private ArrayList<Environment> environments;
	
	/**
	 * 
	 * @param number
	 * @param gameComponent
	 * 
	 * Initialize Level.
	 */
	public Level(int number, GameComponent gameComponent) {
		this.levelNumber = number;
		this.gameComponent = gameComponent;		
		this.heroCoordinate = new int[2];
		this.bossCoordinate = null;
		this.environmentCoordinates = new ArrayList<int[]>();
		this.monsterCoordinates = new ArrayList<int[]>();
		this.monsters = new ArrayList<Monster>();
		this.environments = new ArrayList<Environment>();
		this.readFile();
		
		/*
		 * adding game characters to the level
		 */
		this.hero = new Hero(heroCoordinate[0] * 25, heroCoordinate[1] * 25, this.gameComponent);
		try {
			this.boss = new Boss(bossCoordinate[0]*25, bossCoordinate[1]*25, this.gameComponent);
		} catch (NullPointerException e) {
		}
		for (int[] monCoord : this.monsterCoordinates) {
			boolean isShooter = Math.random() < 0.5 ? false : true;
			this.monsters.add(new Monster(monCoord[0] * 25, monCoord[1] * 25, isShooter, this.gameComponent));
		}
		
		/*
		 * adding environment block to the level
		 */
		for (int[] environCoord : this.environmentCoordinates) {
			this.environments.add(new Environment(environCoord[0] * 25, environCoord[1] * 25, this.gameComponent));
		}
		
	}
	
	// Loads the level from *.txt files.
	private void readFile() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("level" + Integer.toString(this.levelNumber) + ".txt"));
		} catch (FileNotFoundException e) {
			System.out.println("text file is not found");
			return;
		}
		int j = 0;
		while (scanner.hasNextLine()) {
			String curLine = scanner.nextLine();
			for (int i = 0; i < curLine.length(); i++) {
				if (curLine.charAt(i) == 'B') {
					int[] coord = new int[2];
					coord[0] = i;
					coord[1] = j;
					this.environmentCoordinates.add(coord);
				} else if (curLine.charAt(i) == 'H') {
					this.heroCoordinate[0] = i;
					this.heroCoordinate[1] = j;
				} else if (curLine.charAt(i) == 'M') {
					int[] coord = new int[2];
					coord[0] = i;
					coord[1] = j;
					this.monsterCoordinates.add(coord);
				} else if (curLine.charAt(i) == 'S') {
					this.bossCoordinate = new int[2];
					this.bossCoordinate[0] = i;
					this.bossCoordinate[1] = j;
				}
			}
			j++;
		}
		scanner.close();
	}

	public int getLevelNumber() {
		return this.levelNumber;
	}

	public Hero getHero() {
		return this.hero;
	}

	public int[] getHeroCoords() {
		return this.heroCoordinate;
	}

	public Boss getBoss() {
		return this.boss;
	} 
	
	public int[] getBossCoords() {
		return this.bossCoordinate;
	}
	
	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}

	public ArrayList<Environment> getEnvironments() {
		return this.environments;
	}
}
