package game.weapons;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import framework.utils.MathHelper.Direction;
import game.entities.Player;
import game.entities.projectiles.Bullet;
import game.entities.projectiles.Projectile;

public class Gun extends Weapon {
	
	Projectile ammunition;
	int x = 0;
	int y = 0;
	
	public Gun(Player owner) {
		super(owner);
	}
	
	public void shoot() {
		if (owner.getPressedKeyDirections().length > 0) {
			owner.getWorld().getRoomAt(owner.getWorldX(),owner.getWorldY()).addProjectile(new Bullet((int)owner.getCenterX(),(int)owner.getCenterY(),owner));
		}
	}
}
