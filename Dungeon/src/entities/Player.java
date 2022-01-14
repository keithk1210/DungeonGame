package entities;

import java.awt.Color;
import java.awt.Graphics;

import gui.GUI;
import resources.Resources;
import utils.Structs.Coord;
import world.Tile;

public class Player extends Entity {
	
	private static final long serialVersionUID = 1L;
	private int posXInWorld;
	private int posYInWorld;
	
	
	public Player(int worldSize) {
		super(Resources.MIDDLE_X/Tile.SIZE, Resources.MIDDLE_Y/Tile.SIZE);
		speed = 10;
		if (worldSize != Resources.DEBUG_WORLD_SIZE) {
			posXInWorld = (worldSize-1)/2;
			posYInWorld = (worldSize-1)/2;
			
		} else {
			posXInWorld = 0;
			posYInWorld = 0;
		}
		GUI.setDiscovered(posXInWorld, posYInWorld);
		addCoordForGUI();
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Resources.PLAYER_COLOR);
		g.fillRect((int)super.getX(), (int)super.getY(), Tile.SIZE, Tile.SIZE);
		g.setColor(Color.red);
	}
	
	@Override
	public void move() {
		super.move();
		updateWorldAndRoomPosition();
	}
	
	private void updateWorldAndRoomPosition() {
		if (this.x < 0) {
			this.x = Resources.SCREEN_WIDTH - Tile.SIZE;
			posXInWorld--;
			GUI.setDiscovered(posXInWorld, posYInWorld);
			addCoordForGUI();
		} else if (this.x >= Resources.SCREEN_WIDTH) {
			this.x = 0;
			posXInWorld++;
			GUI.setDiscovered(posXInWorld, posYInWorld);
			addCoordForGUI();
		} else if (this.y < 0) {
			this.y = Resources.SCREEN_HEIGHT - Tile.SIZE;
			posYInWorld--;
			GUI.setDiscovered(posXInWorld, posYInWorld);
			addCoordForGUI();
		} else if (this.y >= Resources.SCREEN_HEIGHT) {
			this.y = 0;
			posYInWorld++;
			GUI.setDiscovered(posXInWorld, posYInWorld);
			addCoordForGUI();
		}
	}
	
	
	private void addCoordForGUI() {
		GUI.addNewCoord(new Coord(Resources.SCREEN_WIDTH-Tile.SIZE + ((getWorldX()) * Resources.MAP_UNIT_SIZE) + Resources.MAP_UNIT_SIZE/2, ((getWorldY()) * Resources.MAP_UNIT_SIZE) + Resources.MAP_UNIT_SIZE/2));
	}
	
	public int getWorldX() {
		return posXInWorld;
	}
	public int getWorldY() {
		return posYInWorld;
	}
	

}
