package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import framework.gamestates.GameState;
import framework.gamestates.GameStateManager;
import framework.resources.Resources;

public class GameOverState extends GameState {
	
	private String[] gameOverMenu;
	private static final String SPLASH_TEXT = "GAME OVER!";
	private static final String QUIT_GAME = "Quit Game";
	private static final String NEW_GAME = "New Game!";
	private int selected;
	public GameOverState(GameStateManager manager) {
		super(manager);
		this.gameOverMenu = new String[] {QUIT_GAME,NEW_GAME};
		this.selected = 0;
	}

	@Override
	protected void loop() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void render(Graphics graphics) {
		graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Resources.SCREEN_WIDTH, Resources.SCREEN_HEIGHT);
		Font araial = new Font("Araial", Font.PLAIN, 25);
		graphics.setFont(araial);
		graphics.setColor(Color.red);
		graphics.drawString(SPLASH_TEXT, Resources.MIDDLE_SCREEN_X - graphics.getFontMetrics().stringWidth(SPLASH_TEXT)/2, Resources.MIDDLE_SCREEN_Y-50);
		for(int i=0;i<this.gameOverMenu.length;i++) {
			if(i==this.selected) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.gameOverMenu[i], Resources.MIDDLE_SCREEN_X - graphics.getFontMetrics().stringWidth((this.gameOverMenu[i]))/2, Resources.MIDDLE_SCREEN_Y + i * 40);
		}

	}

	@Override
	protected void keyPressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_UP:
			if(this.selected > 0) this.selected--;
			break;
		case KeyEvent.VK_DOWN:
			if(this.selected < this.gameOverMenu.length-1) this.selected++;
			break;
		case KeyEvent.VK_ENTER:
			switch(this.gameOverMenu[selected]) {
			case NEW_GAME:
				super.gameStateManager.stackState(new PlayingState(gameStateManager));
				break;
			case QUIT_GAME:
				System.exit(0);
				break;
			}
			break;
		}

	}

	@Override
	protected void keyReleased(int keyCode) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
