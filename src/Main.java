
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	private JFrame homeFrame;
	private JFrame gameFrame;

	/**
	 * @param 
	 * Runs the entire game.
	 */
	public static void main(String[] args) {
		new Main();
	}

	public Main() {

		// Initialize Frame and Panel to Display Game Menu
		this.homeFrame = new JFrame("Demon Slayer Menu");
		this.gameFrame = new JFrame("Demon Slayer");
		homeFrame.setSize(860, 500);
		gameFrame.setSize(625, 480);
		JPanel homePanel = new JPanel();
		homeFrame.add(homePanel, BorderLayout.SOUTH);

		// Game Menu Image
		try {
			BufferedImage menuScreenImage = ImageIO.read(new File("demonSlayerTitleScreen.png"));
			TitleScreenComponent screenComponent = new TitleScreenComponent(menuScreenImage);
			homeFrame.add(screenComponent);
		} catch (IOException e) {
			System.out.println("Could not load image file " + "demonSlayerTitleScreen.png");
		}

		// Game Menu Instructions
		String instructions = "<space>: jump; <left> and <right>: move; <u> and <d>: change level; <r> return to menu; <p>power-up";
		JLabel homeLabel = new JLabel();
		homeFrame.add(homeLabel,BorderLayout.NORTH);
		homeLabel.setText(instructions);

		// Game
		JPanel info = new JPanel();
		info.setBackground(Color.BLACK);
		JLabel infoLabel = new JLabel("Score: 0     |     Lives: " + Integer.toString(Hero.INIT_NUM_LIVES));
		infoLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		infoLabel.setForeground(Color.WHITE);
		info.add(infoLabel);

		GameComponent gameComponent = new GameComponent(infoLabel);
		GameAdvanceListener advanceListener = new GameAdvanceListener(gameComponent);
		GameKeyListener gameKeyListener = new GameKeyListener(gameComponent, this);
		gameFrame.addKeyListener(gameKeyListener);
		gameFrame.add(info, BorderLayout.NORTH);

		// Game Menu Start Button
		JButton startButton = new JButton("Start Game");
		homePanel.add(startButton);
		GameMenuListener menuListener = new GameMenuListener(this, gameComponent, advanceListener);
		startButton.addActionListener(menuListener);

		// Game Menu Visibility
		homeFrame.setVisible(true);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void mainVisibility(boolean isVisible) {
		this.homeFrame.setVisible(isVisible);
	}

	public void gameVisibility(boolean isVisible) {
		this.gameFrame.setVisible(isVisible);
	}

	public JFrame getHomeFrame() {
		return this.homeFrame;
	}
	
	public JFrame getGameFrame() {
		return this.gameFrame;
	}
	
}
