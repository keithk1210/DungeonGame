package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import framework.gamestates.GameState;
import framework.gamestates.GameStateManager;
import framework.resources.Resources;
import framework.utils.Structs.Coord;
import game.entities.Player;
import game.inventory.InventorySlot;
import game.world.Tile;

public class InventoryState extends GameState {
	
	private int[][] slots;
	private Player player;
	private static final String INVENTORY = "Inventory";
	
	public InventoryState(GameStateManager manager, Player player) {
		super(manager);
		slots = new int[Resources.HEIGHT_IN_TILES-1][Resources.WIDTH_IN_TILES];
		this.player = player;
	}

	@Override
	protected void loop() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void render(Graphics graphics) {
		graphics.setColor(new Color(30, 30, 70));
		graphics.fillRect(0, 0, Resources.SCREEN_WIDTH, Resources.SCREEN_HEIGHT);
		graphics.setFont(new Font("Araial", Font.PLAIN, 25));
		graphics.setColor(Color.white);
		graphics.drawString(INVENTORY, Tile.SIZE/2, Tile.SIZE/2);
		for (int y = 0; y < slots.length; y++) {
			for (int x = 0; x < slots[y].length; x++) {
				graphics.setColor(Color.black);
				graphics.drawRect(x * Tile.SIZE, Tile.SIZE + y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
				if (x == this.player.getInventory().getSelectedSlot().getX() && y == this.player.getInventory().getSelectedSlot().getY()) {
					graphics.setColor(Color.white);
				} else {
					graphics.setColor(Color.gray);
				}
				graphics.fillRect(x * Tile.SIZE, Tile.SIZE + y * Tile.SIZE,  Tile.SIZE,  Tile.SIZE);
			}
		}
		for (int y = 0; y < slots.length; y++) {
			for (int x = 0; x < slots[y].length; x++) {
				if (this.player.getInventory().getContents()[y][x] != null) {
					InventorySlot currentSlot = this.player.getInventory().getContents()[y][x];
					graphics.drawImage(Resources.TEXTURES.get(currentSlot.getItem().getTextureID()), x * Tile.SIZE, Tile.SIZE + y * Tile.SIZE, Tile.SIZE, Tile.SIZE, null);
					graphics.setColor(Color.black);
					graphics.drawString(String.valueOf(currentSlot.getAmount()), x * Tile.SIZE, Tile.SIZE + Tile.SIZE/4 + y * Tile.SIZE);
				}
			}
		}
	}

	@Override
	protected void keyPressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_UP:
			if(this.player.getInventory().getSelectedSlot().getY() > 0) this.player.getInventory().setSelectedSlotY(this.player.getInventory().getSelectedSlot().getY()-1);
			break;
		case KeyEvent.VK_DOWN:
			if(this.player.getInventory().getSelectedSlot().getY() < slots.length-1) this.player.getInventory().setSelectedSlotY(this.player.getInventory().getSelectedSlot().getY()+1);
			break;
		case KeyEvent.VK_LEFT:
			if(this.player.getInventory().getSelectedSlot().getX() > 0) this.player.getInventory().setSelectedSlotX(this.player.getInventory().getSelectedSlot().getX()-1);
			break;
		case KeyEvent.VK_RIGHT:
			if(this.player.getInventory().getSelectedSlot().getX() < slots[this.player.getInventory().getSelectedSlot().getY()].length-1) this.player.getInventory().setSelectedSlotX(this.player.getInventory().getSelectedSlot().getX()+1);
			break;
		case KeyEvent.VK_E:
			this.gameStateManager.backToPreviousState();
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
