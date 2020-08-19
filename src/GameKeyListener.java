import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
	
	private GameComponent gameComponent;
	private Main main;
	private boolean isMovingLeft = false;
	private boolean isMovingRight = false;
	private boolean isFlying = false;
	
	/**
	 * 
	 * @param gameComponent
	 * @param main
	 * 
	 * Initialize GameKeyListener
	 */
	public GameKeyListener(GameComponent gameComponent, Main main) {
		this.gameComponent = gameComponent;
		this.main = main;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		
		if (keyCode == KeyEvent.VK_LEFT) {
			this.isMovingLeft = true;
		}
		if (this.isMovingLeft) {
			this.gameComponent.heroMoveLeftOrRight(true);
		}
		
		if (keyCode == KeyEvent.VK_RIGHT) {
			this.isMovingRight = true;
		}
		if (this.isMovingRight) {
			this.gameComponent.heroMoveLeftOrRight(false);
		}
		
		if (keyCode == KeyEvent.VK_SPACE) {
			this.isFlying = true;
		}
		if (this.isFlying) {
			this.gameComponent.heroFly();
		}
			
		if (this.isFlying == true && this.isMovingLeft == true) {
			this.gameComponent.heroFly();
			this.gameComponent.heroMoveLeftOrRight(true);
		}
		
		if (this.isFlying == true && this.isMovingRight == true) {
			this.gameComponent.heroFly();
			this.gameComponent.heroMoveLeftOrRight(false);
		}
		
		if (keyCode == KeyEvent.VK_A) {
			this.gameComponent.heroAttack();
		}
	}
	
	// Makes this so you cannot continually scroll through the levels.
	@Override
	public void keyReleased(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		
		if (keyCode == KeyEvent.VK_D) {
			this.gameComponent.turnToPreviousLevel();
		}
		if (keyCode == KeyEvent.VK_U) {
			this.gameComponent.turnToNextLevel();
		}
		
		if (keyCode == KeyEvent.VK_LEFT) {
			this.isMovingLeft = false;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			this.isMovingRight = false;
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			this.isFlying = false;
			this.gameComponent.resetHeroDy();
		}
		if (keyCode == KeyEvent.VK_R) {
			this.main.getGameFrame().dispose(); 
			this.main.getGameFrame().setVisible(false); 
			main = new Main();
		}
		if (keyCode == KeyEvent.VK_A) {
			this.gameComponent.heroAttackClose();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//

	}

}
