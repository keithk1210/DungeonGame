package game.entities.projectiles;

import game.entities.Player;
import game.world.World;

public class Bullet extends Projectile {
	
	public Bullet(int screenX, int screenY, Player p,World world) {
		super(screenX,screenY,p,world);
		this.attack = 2;
	}
	
}
