package game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import framework.gui.GUI;
import framework.resources.Resources;
import framework.utils.MathHelper;
import framework.utils.MathHelper.Direction;
import framework.utils.Structs.Coord;
import game.weapons.Gun;
import game.world.Tile;
import game.world.World;

public class Player extends Entity {
	
	private static final long serialVersionUID = 1L;
	private int posXInWorld;
	private int posYInWorld;
	private Gun gun;
	private World world;
	private Stack<MathHelper.Direction> pressedKeyDirections;
	private final Set<Integer> pressedKeys = new HashSet<>();
	

	
	
	public Player(World _world) {
		super(Resources.CONJ_BLUE_FRONT, Resources.MIDDLE_X, Resources.MIDDLE_Y, Tile.SIZE);
		world = _world;
		gun = new Gun(this);
		pressedKeyDirections = new Stack<MathHelper.Direction>();
		//arrowKeys = new Key[] {new Key(false,KeyEvent.VK_UP), new Key(false,KeyEvent.VK_RIGHT),
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
	public void move() {
		super.move();
		updateWorldAndRoomPosition();
	}
	
	public void mouseMoved(MouseEvent e) {
		
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
	
	public void setPressedKeyDirections(MathHelper.Direction direction) {
		if(!pressedKeyDirections.empty()) {
			if (!pressedKeyDirections.peek().equals(direction)) {
				this.pressedKeyDirections.push(direction);
			}
		} else {
			this.pressedKeyDirections.push(direction);
		}
	}
	
	
	
	public Coord[] getPressedKeyDirections() {
		if (this.pressedKeys.size() > 1) {
			MathHelper.Direction currentDir = pressedKeyDirections.pop();
			MathHelper.Direction previousDir = pressedKeyDirections.peek();
			if (previousDir.equals(currentDir.opposite) && pressedKeys.size() == 2) {
				pressedKeyDirections.push(currentDir);
				return new Coord [] {new Coord(currentDir.dirX,currentDir.dirY)};
			} else if (previousDir.equals(currentDir.opposite) && pressedKeys.size() > 2) {
				pressedKeyDirections.push(currentDir);
				return new Coord[] {new Coord(currentDir.dirX,currentDir.dirY), new Coord (pressedKeyDirections.get(0).dirX,pressedKeyDirections.get(0).dirY)};
			}
			else {
				pressedKeyDirections.push(currentDir);
				return new Coord [] {new Coord(currentDir.dirX,currentDir.dirY), new Coord(previousDir.dirX,previousDir.dirY)};
			}
		} else {
			return new Coord [] {new Coord(pressedKeyDirections.peek().dirX,pressedKeyDirections.peek().dirY)};
		}
	}
	

	public void keyPressed(int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_UP:
				this.setPressedKeyDirections(Direction.NORTH);
				pressedKeys.add(keyCode);
				break;
			case KeyEvent.VK_DOWN:
				this.setPressedKeyDirections(Direction.SOUTH);
				pressedKeys.add(keyCode);
				break;
			case KeyEvent.VK_LEFT:
				this.setPressedKeyDirections(Direction.WEST);
				pressedKeys.add(keyCode);
				break;
			case KeyEvent.VK_RIGHT:
				this.setPressedKeyDirections(Direction.EAST);
				pressedKeys.add(keyCode);
				break;
			case KeyEvent.VK_SPACE:
				if (!this.pressedKeyDirections.empty()) {
					this.gun.shoot();
				}
				break;
		}
	}
	
	public void keyReleased(int keyCode) {
		removePressedKey(keyCode);
		switch (keyCode) {
		case KeyEvent.VK_UP:
			this.removePressedKeyDirectoinStartingFromTopOfStack(Direction.NORTH);
			break;
		case KeyEvent.VK_DOWN:
			this.removePressedKeyDirectoinStartingFromTopOfStack(Direction.SOUTH);
			break;
		case KeyEvent.VK_LEFT:
			this.removePressedKeyDirectoinStartingFromTopOfStack(Direction.WEST);
			break;
		case KeyEvent.VK_RIGHT:
			this.removePressedKeyDirectoinStartingFromTopOfStack(Direction.EAST);
			break;
	}
	}
	
	private void removePressedKeyDirectoinStartingFromTopOfStack(MathHelper.Direction direction) {
		for (int i = this.pressedKeyDirections.size() - 1; i >= 0; i--) {
			if (pressedKeyDirections.elementAt(i).equals(direction)) {
				pressedKeyDirections.remove(i);
				return;
			}
		}
	}
	
	private void removePressedKey(int keyCode) {
		Iterator<Integer> iterator = pressedKeys.iterator();
		while (iterator.hasNext()) {
			if (Integer.valueOf(iterator.next()) == keyCode) {
				iterator.remove();
			}
		}
	}
	
}


