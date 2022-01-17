package game.world.generator;

import framework.resources.Resources;
import framework.utils.MathHelper;
import game.entities.Enemy;
import game.entities.Player;
import game.world.World;

public class Populator {
	
	private World world;
	private Player player;
	
	public Populator(Player _player, World _world) {
		world = _world;
		player = _player;
	}
	
	public void populate() {
		for (int y = 0; y < world.getSize(); y++) {
			for (int x = 0; x < world.getSize(); x++) {
				if (!world.getRoomAt(x, y).hasEnemies()) {
					if (MathHelper.randomInt(100) < Resources.PROB_OF_ENEMY) {
						world.getRoomAt(x, y).spawnEnemy(new Enemy(player));
					}
				}
			}
		}
	}

}
