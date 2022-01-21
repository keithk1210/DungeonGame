package game.entities.projectiles;

import framework.resources.TextureID;
import framework.utils.Structs.Coord;
import game.entities.Entity;
import game.entities.Player;
import game.world.World;

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
	
	public Projectile(int screenX, int screenY, Player p, World world) {
		super(TextureID.MAGIC_BULLET, screenX ,screenY,25, world);
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
		if (this.shooter.getGun().getPressedKeyDirections().length != 0) {
			for (Coord coord: this.shooter.getGun().getPressedKeyDirections()) {
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
