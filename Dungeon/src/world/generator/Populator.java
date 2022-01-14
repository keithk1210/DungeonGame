package world.generator;

import entities.Enemy;
import entities.Player;
import resources.Resources;
import utils.MathHelper;
import world.World;

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
