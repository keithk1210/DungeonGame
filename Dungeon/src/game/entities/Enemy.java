package game.entities;

import java.awt.Color;
import java.awt.Graphics;

import framework.resources.Resources;
import framework.utils.MathHelper;
import game.world.Tile;

public class Enemy extends Entity {
	
	static final long serialVersionUID = 1L;
	private Player target;
	private static final double ERROR = .5;

	
	public Enemy(Player _target) {
		super(Resources.ENEMY, MathHelper.randomInt(1, Resources.WIDTH_IN_TILES-1)*Tile.SIZE,(MathHelper.randomInt(1, Resources.HEIGHT_IN_TILES-1))*Tile.SIZE,Tile.SIZE);
		target = _target;
		this.health = 6;
		this.attack = 5;
	}
	
	@Override
	public void move() {
		if (this.getCenterX() == target.getCenterX()) {
			if (this.getCenterY() > target.getCenterY()) {
				this.up = true;
				this.down = false;
				this.left = false;
				this.right = false;
				super.move();
			} else {
				this.up = false;
				this.down = true;
				this.left = false;
				this.right = false;
				super.move();
			}
		} else if (this.getCenterY() == target.getCenterY()) {
			if (this.getCenterX() > target.getCenterX()) {
				this.up = false;
				this.down = false;
				this.left = true;
				this.right = false;
				super.move();
			} else {
				this.up = false;
				this.down = false;
				this.left = false;
				this.right = true;
				super.move();
			}
		} else {
			moveEnemyDiagonally();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)this.getX(),(int)this.getY(), Tile.SIZE, Tile.SIZE);
		g.setColor(debugLines(target));
		g.drawLine((int)this.getBounds().getMinX(), 0, (int)this.getBounds().getMinX(), Resources.SCREEN_HEIGHT);
		g.drawLine((int)this.getBounds().getMaxX(), 0, (int)this.getBounds().getMaxX(), Resources.SCREEN_HEIGHT);
		g.drawLine(0, (int)this.getBounds().getMinY(), Resources.SCREEN_WIDTH ,(int)this.getBounds().getMinY());
		g.drawLine(0, (int)this.getBounds().getMaxY(), Resources.SCREEN_WIDTH ,(int)this.getBounds().getMaxY());
	}
	
	

	
	public void moveEnemyDiagonally() {
		if (this.getCenterX() > target.getCenterX() && this.getCenterY() > target.getCenterY()) {
			double angle = Math.atan2((this.getCenterY()-target.getCenterY()),(this.getCenterX()-target.getCenterX()));
			for (double y = this.getCenterY(); y > target.getCenterY(); y -= 0.1) {
				for (double x = this.getCenterX(); x > target.getCenterX(); x -=0.1) {
					double a = this.getCenterX()-x;
					double b = this.getCenterY()-y;
					if (Math.tan(angle) - (b/a) < ERROR) {
						double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
						if (Math.abs(c- this.speed) < ERROR) {
							super.setCenterX((int)(this.getCenterX()-a));
							super.setCenterY((int)(this.getCenterY() - b));
							return;
						}
					}
				}
			}
	} else if (this.getCenterX() > target.getCenterX() && this.getCenterY() < target.getCenterY()) {
		double angle = Math.atan2((target.getCenterY()-this.getCenterY()),(this.getCenterX()-target.getCenterX()));
		for (double y = this.getCenterY(); y < target.getCenterY(); y += 0.1) {
			for (double x = this.getCenterX(); x > target.getCenterX(); x -=0.1) {
				double a = this.getCenterX()-x;
				double b = y-this.getCenterY();
				if (Math.tan(angle) - (b/a) < ERROR) {
					double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
					if (Math.abs(c- this.speed) < ERROR) {
						super.setCenterX((int)(this.getCenterX() - a));
						super.setCenterY((int)(this.getCenterY() + b));
						return;
					}
				}
			}
		}
		
	} else if (this.getCenterX() < target.getCenterX() && this.getCenterY() < target.getCenterY()) {
		double angle = Math.atan2(target.getCenterY()-this.getCenterY(),(target.getCenterX()-this.getCenterX()));
		for (double y = this.getCenterY(); y < target.getCenterY(); y += 0.1) {
			for (double x = this.getCenterX(); x < target.getCenterX(); x +=0.1) {
				double a = x -this.getCenterX();
				double b = y - this.getCenterY();
				if (Math.tan(angle) - (b/a) < ERROR) {
					double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
					if (Math.abs(c - this.speed) < ERROR) {
						super.setCenterX((int)(this.getCenterX() + a));
						super.setCenterY((int)(this.getCenterY() + b));
						return;
					}
				}
			}
		}
	} else if (this.getCenterX() < target.getCenterX() && this.getCenterY() > target.getCenterY()) {
		double angle = Math.atan2((this.getCenterY()-target.getCenterY()),(target.getCenterX()-this.getCenterX()));
			for (double y = this.getCenterY(); y > target.getCenterY(); y -= 0.1) {
				for (double x = this.getCenterX(); x < target.getCenterX(); x +=0.1) {
					double a = x-this.getCenterX();
					double b = this.getCenterY()-y;
					//System.out.println(a + " " + b);
					if (Math.tan(angle) - (b/a) < ERROR) {
						double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
						if (Math.abs(c-this.speed) < ERROR) {
							super.setCenterX((int)(this.getCenterX() + a));
							super.setCenterY((int)(this.getCenterY() - b));
							return;
						}
					}
				}
			}
		} 
	}
}
