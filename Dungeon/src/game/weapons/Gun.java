package game.weapons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import entities.Player;
import game.projectiles.Bullet;
import game.projectiles.Projectile;
import world.World;

public class Gun extends Weapon {
	
	Projectile ammunition;
	int x = 0;
	int y = 0;
	
	public Gun(Player owner) {
		super(owner);
	}
	
	public void shoot(MouseEvent e) {
		owner.getWorld().addProjectile(new Bullet((int)owner.getCenterX(),(int)owner.getCenterY(),owner,e));
		x = e.getX();
		y = e.getY();
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.drawLine((int)owner.getCenterX(),(int) owner.getCenterY(), x, y);
	}
}
