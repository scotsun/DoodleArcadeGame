import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameMenuListener implements ActionListener {

	private Main main;
	private JFrame homeFrame;
	private JFrame gameFrame;
	private GameComponent gameComponent;
	private GameAdvanceListener advanceListener;
	public static final int DELAY = 15;

	/**
	 * 
	 * @param main
	 * @param gameComponent
	 * @param advanceListener
	 * 
	 *                        Initialize GameMenu Listener.
	 */
	public GameMenuListener(Main main, GameComponent gameComponent, GameAdvanceListener advanceListener) {
		this.main = main;
		this.homeFrame = main.getHomeFrame();
		this.gameFrame = main.getGameFrame();
		this.gameComponent = gameComponent;
		this.advanceListener = advanceListener;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Disposes title screen
		this.homeFrame.dispose();
		this.homeFrame.setVisible(false);

		// Clock
		Timer timer = new Timer(DELAY, advanceListener);
		timer.start();

		// Runs Game
		gameFrame.add(gameComponent);

		// Game Visibility
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
