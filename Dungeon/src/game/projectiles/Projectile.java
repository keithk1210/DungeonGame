package game.projectiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import entities.Player;

public class Projectile extends Rectangle {
	
	static final int size = 10;
	static final int speed = 6;
	final int initialX;
	final int initialY;
	final int targetX;
	final int targetY;
	final double dx;
	final double dy;
	Player shooter;
	private static final double ERROR = .01;
	private static final double INCREMENT = .03;
	
	public Projectile(int screenX, int screenY, Player p, MouseEvent e) {
		super(screenX,screenY,size,size);
		initialX = (int)p.getCenterX();
		initialY = (int)p.getCenterY();
		targetX = e.getX();
		targetY = e.getY();
		shooter = p;
		dx = calculateDXandDY()[0];
		dy = calculateDXandDY()[1];
		System.out.println(dx + " " + dy);
	}
	
	public void move() {
		this.setCenterX((int)(this.getCenterX()+dx));
		this.setCenterY((int)(this.getCenterY()+dy));
	}
		
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int)this.getCenterX(), (int)this.getCenterY(), size, size);
	}
	
	public int[] calculateDXandDY() {
		int[] dxAndDy = new int[2];
		if (initialX > targetX && initialY > targetY) {
			double angle = Math.atan2((initialY-targetY),(initialX-targetX));
			for (double y = initialY; y > targetY; y -= INCREMENT) {
				for (double x = initialX; x > targetX; x -=INCREMENT) {
					double a = initialX-x;
					double b = initialY-y;
					if (Math.tan(angle) - (b/a) < ERROR) {
						double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
						if (Math.abs(c- this.speed) < ERROR) {
							dxAndDy[0] = (int) a * -1;
							dxAndDy[1] = (int) b * -1;
							return dxAndDy;
						}
					}
				}
			}
	} else if (initialX > targetX && initialY < targetY) {
		double angle = Math.atan2((targetY-initialY),(initialX-targetX));
		for (double y = initialY; y < targetY; y += INCREMENT) {
			for (double x = initialX; x > targetX; x -=INCREMENT) {
				double a = initialX-x;
				double b = y-initialY;
				if (Math.tan(angle) - (b/a) < ERROR) {
					double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
					if (Math.abs(c- this.speed) < ERROR) {
						dxAndDy[0] = (int) a * -1;
						dxAndDy[1] = (int) b;
						return dxAndDy;
					}
				}
			}
		}
		
	} else if (initialX < targetX && initialY < targetY) {
		double angle = Math.atan2(targetY-initialY,(targetX-initialX));
		for (double y = initialY; y < targetY; y += INCREMENT) {
			for (double x = initialX; x < targetX; x +=INCREMENT) {
				double a = x -initialX;
				double b = y - initialY;
				if (Math.tan(angle) - (b/a) < ERROR) {
					double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
					if (Math.abs(c - this.speed) < ERROR) {
						dxAndDy[0] = (int) a;
						dxAndDy[1] = (int) b;
						return dxAndDy;
					}
				}
			}
		}
	} else if (initialX < targetX && initialY > targetY) {
		double angle = Math.atan2((initialY-targetY),(targetX-initialX));
			for (double y = initialY; y > targetY; y -= INCREMENT) {
				for (double x = initialX; x < targetX; x +=INCREMENT) {
					double a = x-initialX;
					double b = initialY-y;
					//System.out.println(a + " " + b);
					if (Math.tan(angle) - (b/a) < ERROR) {
						double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
						if (Math.abs(c-this.speed) < ERROR) {
							dxAndDy[0] = (int) a;
							dxAndDy[1] = (int) b * -1;
							return dxAndDy;
						}
					}
				}
			}
		} else if (initialX == targetX) {
			if (initialY > targetY) {
				dxAndDy[0] = 0;
				dxAndDy[1] = -speed;
				return dxAndDy;
			} else {
				dxAndDy[0] = 0;
				dxAndDy[1] = speed;
				return dxAndDy;
			}
		} else if (initialY == targetY) {
			if (initialX > targetX) {
				dxAndDy[0] = -speed;
				dxAndDy[1] = 0;
				return dxAndDy;
			} else {
				System.out.println("Code reached");
				dxAndDy[0] = speed;
				dxAndDy[1] = 0;
				return dxAndDy;
			}
		}
		return null;
	}
	public void setCenterX(int x) {
		super.x = x - super.width/2;
	}

	public void setCenterY(int y) {
		super.y = y - super.height/2;
	}
}
