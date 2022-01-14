package gamestates;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class GameState {
	protected GameStateManager gameStateManager;
	
	protected GameState(GameStateManager manager) {
		this.gameStateManager = manager;
	}
	
	protected abstract void loop();
	
	protected abstract void render(Graphics graphics);
	
	protected abstract void keyPressed(int keyCode);
	
	protected abstract void keyReleased(int keyCode);
	
	protected abstract void mouseMoved(MouseEvent e);
	
	protected abstract void mouseClicked(MouseEvent e);
}
