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
	private static final byte[] frames = new byte[] {0,1,0,2};
	private int posXInWorld;
	private int posYInWorld;
	private Gun gun;
	private World world;
	private Stack<MathHelper.Direction> aimDirection;
	private final Set<Integer> pressedKeys = new HashSet<>();
	

	
	
	public Player(World _world) {
		super(Resources.CONJ_BLUE_FRONT, Resources.MIDDLE_X, Resources.MIDDLE_Y, Tile.SIZE);
		world = _world;
		gun = new Gun(this);
		aimDirection = new Stack<MathHelper.Direction>();
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
	
	public void setAimDirection(MathHelper.Direction direction) {
		if(!aimDirection.empty()) {
			if (!aimDirection.peek().equals(direction)) {
				this.aimDirection.push(direction);
			}
		} else {
			this.aimDirection.push(direction);
		}
		System.out.println(aimDirection);
	}
	
	
	
	public Coord[] getAimDirection() {
		if (this.pressedKeys.size() > 1) {
			MathHelper.Direction currentDir = aimDirection.pop();
			MathHelper.Direction previousDir = aimDirection.peek();
			if (previousDir.equals(currentDir.opposite)) {
				aimDirection.push(currentDir);
				return new Coord [] {new Coord(currentDir.dirX,currentDir.dirY)};
			} else {
				aimDirection.push(currentDir);
				return new Coord [] {new Coord(currentDir.dirX,currentDir.dirY), new Coord(previousDir.dirX,previousDir.dirY)};
			}
		} else {
			return new Coord [] {new Coord(aimDirection.peek().dirX,aimDirection.peek().dirY)};
		}
	}
	

	public void keyPressed(int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_UP:
				this.setAimDirection(Direction.NORTH);
				pressedKeys.add(keyCode);
				break;
			case KeyEvent.VK_DOWN:
				this.setAimDirection(Direction.SOUTH);
				pressedKeys.add(keyCode);
				break;
			case KeyEvent.VK_LEFT:
				this.setAimDirection(Direction.WEST);
				pressedKeys.add(keyCode);
				break;
			case KeyEvent.VK_RIGHT:
				this.setAimDirection(Direction.EAST);
				pressedKeys.add(keyCode);
				break;
			case KeyEvent.VK_SPACE:
				this.gun.shoot();
				break;
		}
		System.out.println(pressedKeys);
	}
	
	public void keyReleased(int keyCode) {
		removePressedKey(keyCode);
		switch (keyCode) {
		case KeyEvent.VK_UP:
			this.removeAimDirectionStartingFromTopOfStack(Direction.NORTH);
			break;
		case KeyEvent.VK_DOWN:
			this.removeAimDirectionStartingFromTopOfStack(Direction.SOUTH);
			break;
		case KeyEvent.VK_LEFT:
			this.removeAimDirectionStartingFromTopOfStack(Direction.WEST);
			break;
		case KeyEvent.VK_RIGHT:
			this.removeAimDirectionStartingFromTopOfStack(Direction.EAST);
			break;
		case KeyEvent.VK_SPACE:
			this.gun.shoot();
			break;
	}
	}
	
	private void removeAimDirectionStartingFromTopOfStack(MathHelper.Direction direction) {
		for (int i = this.aimDirection.size() - 1; i >= 0; i--) {
			if (aimDirection.elementAt(i).equals(direction)) {
				aimDirection.remove(i);
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


