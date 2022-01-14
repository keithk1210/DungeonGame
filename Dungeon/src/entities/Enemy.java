package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import resources.Resources;
import utils.MathHelper;
import utils.Structs.Coord;
import world.Tile;

public class Enemy extends Entity {
	
	static final long serialVersionUID = 1L;
	private Player target;
	private static final double ERROR = .5;
	
	public Enemy(Player _target) {
		super(MathHelper.randomInt(1, Resources.WIDTH_IN_TILES-1),(MathHelper.randomInt(1, Resources.HEIGHT_IN_TILES-1)));
		target = _target;
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
