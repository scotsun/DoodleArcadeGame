import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameAdvanceListener implements ActionListener{
	
	private GameComponent gameComponent;
	
	/**
	 * 
	 * @param gameComponent
	 * 
	 * Initialize GameAdvanceListener.
	 */
	public GameAdvanceListener(GameComponent gameComponent ) {
		this.gameComponent = gameComponent;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.gameComponent.drawScreen();
		this.gameComponent.fall();
		this.gameComponent.monstersMove();
		try {
			this.gameComponent.bossMove();
		} catch (NullPointerException e) {
		}
		this.gameComponent.updateBullet();
		this.gameComponent.bulletMove();
		this.gameComponent.skillMove();
		this.gameComponent.eggMove();
		this.gameComponent.handleCollisions();
	}
}
