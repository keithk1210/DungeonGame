package game.entities.projectiles;

import java.awt.event.MouseEvent;

import game.entities.Player;

public class Bullet extends Projectile {
	
	public Bullet(int screenX, int screenY, Player p) {
		super(screenX,screenY,p);
		this.attack = 2;
	}
	
}
