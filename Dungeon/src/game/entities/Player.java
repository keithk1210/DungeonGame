package game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import framework.gui.GUI;
import framework.resources.Resources;
import framework.utils.MathHelper.Direction;
import framework.utils.Structs.Coord;
import game.weapons.Gun;
import game.world.Tile;
import game.world.World;

public class Player extends Entity {
	
	private static final long serialVersionUID = 1L;
	private static final byte[] frames = new byte[] {0,1,0,2};
	private int posXInWorld;
	private int posYInWorld;
	private Gun gun;
	private World world;
	

	
	
	public Player(World _world) {
		super(Resources.CONJ_BLUE_FRONT, Resources.MIDDLE_X, Resources.MIDDLE_Y, Tile.SIZE);
		world = _world;
		gun = new Gun(this);
		speed = 10;
		this.health = 20;
		this.animationFrame = 0;
		this.animationDelay = 0;
		this.facing = Direction.SOUTH;
		if (world.getSize() != Resources.DEBUG_WORLD_SIZE) {
			posXInWorld = (world.getSize()-1)/2;
			posYInWorld = (world.getSize()-1)/2;
			
		} else {
			posXInWorld = 0;
			posYInWorld = 0;
		}
		GUI.setDiscovered(posXInWorld, posYInWorld);
		addCoordForGUI();
	}
	
	@Override
	public void render(Graphics g) {
		if (up || down || left || right) {
			animationDelay++;
			if (animationDelay == 100) {
				animationFrame++;
				animationDelay = 0;
				if (animationFrame > frames.length - 1) {
					animationFrame = 0;
				}
			}
		}
		g.drawImage(Resources.TEXTURES.get(this.entityID + frames[animationFrame]), this.x, this.y, Tile.SIZE, Tile.SIZE, null);
		g.setColor(Color.red);
		g.drawLine((int)this.getBounds().getMinX(), 0, (int)this.getBounds().getMinX(), Resources.SCREEN_HEIGHT);
		g.drawLine((int)this.getBounds().getMaxX(), 0, (int)this.getBounds().getMaxX(), Resources.SCREEN_HEIGHT);
	}
	
	@Override
	public void move() {
		super.move();
		updateWorldAndRoomPosition();
		switch(this.facing) {
			case NORTH:
				this.entityID = Resources.CONJ_BLUE_BACK;
				break;
			case SOUTH:
				this.entityID = Resources.CONJ_BLUE_FRONT;
				break;
			case EAST:
				this.entityID = Resources.CONJ_BLUE_RIGHT;
				break;
			case WEST:
				this.entityID = Resources.CONJ_BLUE_LEFT;
				break;
		}
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		gun.shoot(e);
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
	
	public Gun getGun() {
		return gun;
	}
	
	public World getWorld() {
		return world;
	}
	
}
