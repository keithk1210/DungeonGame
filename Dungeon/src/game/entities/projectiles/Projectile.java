package game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import framework.resources.Resources;
import framework.utils.Structs.Coord;
import game.entities.Entity;
import game.entities.Player;

public class Projectile extends Entity {
	
	static final int size = 10;
	static final int speed = 6 * 2;
	final int initialX;
	final int initialY;
	final int targetX =0;
	final int targetY=0;
	private double dx;
	private double dy;
	Player shooter;
	private static final double ERROR = .01;
	private static final double INCREMENT = .03;
	
	public Projectile(int screenX, int screenY, Player p) {
		super(Resources.MAGIC_BULLET, screenX ,screenY,25);
		initialX = (int)p.getCenterX();
		initialY = (int)p.getCenterY();
		//targetX = e.getX();
		//targetY = e.getY();
		shooter = p;
		this.calculateDXandDY2();
	}
	
	public void move() {
		this.setCenterX((int)(this.getCenterX()+dx));
		this.setCenterY((int)(this.getCenterY()+dy));
	}
	
	
	private void calculateDXandDY2() {
		dx = 0;
		dy = 0;
		if (shooter.getPressedKeyDirections().length != 0) {
			for (Coord coord: shooter.getPressedKeyDirections()) {
				this.dx += coord.getX() * speed;
				this.dy += coord.getY() * speed;
			}
		}
	}
	
	public void setCenterX(int x) {
		super.x = x - super.width/2;
	}

	public void setCenterY(int y) {
		super.y = y - super.height/2;
	}

}
