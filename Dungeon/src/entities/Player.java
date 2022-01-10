package entities;

import java.awt.Color;
import java.awt.Graphics;

import resources.Resources;
import world.Tile;

public class Player extends Entity {
	
	private static final long serialVersionUID = 1L;
	private int posXInWorld;
	private int posYInWorld;
	
	public Player() {
		super(Resources.MIDDLE_X/Tile.SIZE, Resources.MIDDLE_Y/Tile.SIZE);
		speed = 10;
		posXInWorld = 0;
		posYInWorld = 0;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Resources.PLAYER_COLOR);
		g.fillRect((int)super.getX(), (int)super.getY(), Tile.SIZE, Tile.SIZE);
	}
	
	public int getWorldX() {
		return posXInWorld;
	}
	public int getWorldY() {
		return posYInWorld;
	}
	
}
