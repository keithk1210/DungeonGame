package game.entities.projectiles;

import java.awt.event.MouseEvent;

import game.entities.Player;

public class Bullet extends Projectile {
	
	public Bullet(int screenX, int screenY, Player p, MouseEvent e) {
		super(screenX,screenY,p,e);
		this.attack = 2;
	}
	
}
