package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import framework.gamestates.GameState;
import framework.gamestates.GameStateManager;
import framework.resources.Resources;

public class MainMenu extends GameState {
	
	private String[] optionsMenu;
	private static final String START_GAME = "Start Game!";
	private static final String DEBUG = "Debug";
	private static final String OPTIONS = "Options";
	private static final String QUIT_GAME = "Quit game";
	private int selected;
		
	public MainMenu(GameStateManager manager) {
		super(manager);
		this.optionsMenu = new String[] {START_GAME, DEBUG, OPTIONS, QUIT_GAME};
		this.selected = 0;
	}

	@Override
	protected void loop() {
	}
	
	@Override
	protected void mouseMoved(MouseEvent e) {
		
	}
	
	protected void mouseClicked(MouseEvent e) {}

	@Override
	protected void render(Graphics graphics) {
		graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Resources.SCREEN_WIDTH, Resources.SCREEN_HEIGHT);
		graphics.setFont(new Font("Araial", Font.PLAIN, 25));
		for(int i=0;i<this.optionsMenu.length;i++) {
			if(i==this.selected) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.optionsMenu[i], 20, 50 + i * 40);
		}
	}

	@Override
	protected void keyPressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_UP:
			if(this.selected > 0) this.selected--;
			break;
		case KeyEvent.VK_DOWN:
			if(this.selected < this.optionsMenu.length-1) this.selected++;
			break;
		case KeyEvent.VK_ENTER:
			switch(this.optionsMenu[selected]) {
			case START_GAME:
				super.gameStateManager.stackState(new PlayingState(gameStateManager));
				break;
			case DEBUG:
				super.gameStateManager.stackState(new DebugState(gameStateManager));
				break;
			case OPTIONS:
					super.gameStateManager.stackState(new OptionsMenu(gameStateManager));
					break;
			case QUIT_GAME:
				System.exit(0);
				break;
			}
			break;
		}
	}

	@Override
	protected void keyReleased(int keyCode) {}

}
