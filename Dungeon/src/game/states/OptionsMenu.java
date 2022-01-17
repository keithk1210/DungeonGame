package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import framework.gamestates.GameState;
import framework.gamestates.GameStateManager;
import framework.resources.Resources;

public class OptionsMenu extends GameState {
	
	private String[] optionsMenu;
	private String[] characters;
	private static final String CHARACTER = "Character:";
	private static final String QUIT_GAME = "Quit Game";
	private static final String CONJUROR_BLUE = "Blue Conjuror";
	private int selected;
	private int characterSelected;
	
	
	public OptionsMenu(GameStateManager manager) {
		super(manager);
		this.optionsMenu = new String[] {CHARACTER};
		this.characters = new String[] {CONJUROR_BLUE};
		characterSelected = 0;
		
	}

	@Override
	protected void loop() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void render(Graphics graphics) {
		Font font = new Font("Araial", Font.PLAIN, 25);
		graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Resources.SCREEN_WIDTH, Resources.SCREEN_HEIGHT);
		graphics.setFont(font);
		for(int i=0;i<this.optionsMenu.length;i++) {
			if(i==this.selected) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.optionsMenu[i], 20, 50 + i * 40);
			if (this.selected == 0 ) {
				graphics.setColor(null);
				graphics.drawString(this.characters[characterSelected], graphics.getFontMetrics(font).stringWidth(optionsMenu[selected] + 20), 50 + i * 40);
			}
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
		case KeyEvent.VK_LEFT:
			if (this.selected == 0) {
				characterSelected--;
				if(characterSelected < 0) {
					characterSelected = characters.length - 1;
				}
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (this.selected == 0) {
				characterSelected++;
				if (characterSelected > characters.length - 1) {
					characterSelected = 0;
				}
			}
			break;
		case KeyEvent.VK_ENTER:
			switch(this.optionsMenu[selected]) {
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
