package game.weapons;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import framework.utils.MathHelper;
import framework.utils.MathHelper.Direction;
import framework.utils.Structs.Coord;
import game.entities.Player;
import game.entities.projectiles.Bullet;
import game.entities.projectiles.Projectile;

public class Gun extends Weapon {
	
	Projectile ammunition;
	private Stack<MathHelper.Direction> pressedKeyDirections;
	private final Set<Integer> pressedKeys = new HashSet<>();
	int x = 0;
	int y = 0;
	
	public Gun(Player owner) {
		super(owner);
		this.pressedKeyDirections = new Stack<MathHelper.Direction>();
	}
	
	public void shoot() {
		if (this.getPressedKeyDirections().length > 0) {
			owner.getWorld().getRoomAt(owner.getWorldX(),owner.getWorldY()).spawnEntity(new Bullet((int)owner.getCenterX(),(int)owner.getCenterY(),owner,owner.getWorld()));
		}
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
					this.shoot();
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
	
	private void removePressedKey(int keyCode) {
		Iterator<Integer> iterator = pressedKeys.iterator();
		while (iterator.hasNext()) {
			if (Integer.valueOf(iterator.next()) == keyCode) {
				iterator.remove();
			}
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

}
