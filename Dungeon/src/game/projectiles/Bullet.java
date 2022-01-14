package game.projectiles;

import java.awt.event.MouseEvent;

import entities.Player;

public class Bullet extends Projectile {
	
	public Bullet(int screenX, int screenY, Player p, MouseEvent e) {
		super(screenX,screenY,p,e);
	}
	
}
