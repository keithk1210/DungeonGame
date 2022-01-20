package game.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;

import framework.gamestates.GameState;
import framework.gamestates.GameStateManager;
import framework.gui.GUI;
import framework.resources.Resources;
import framework.utils.MathHelper.Direction;
import game.entities.Enemy;
import game.entities.Player;
import game.entities.projectiles.Projectile;
import game.world.Room;
import game.world.World;
import game.world.generator.LevelGenerator;
import game.world.generator.Populator;

public class DebugState extends GameState {
	
	private LevelGenerator generator;
	private Populator populator;
	private World world;
	private Player player;
	
	
	public DebugState(GameStateManager manager) {
		super(manager);
		this.world = new World(Resources.DEBUG_WORLD_SIZE);
		this.generator = new LevelGenerator(world);
		generator.generate();
		world.setRooms(generator.getRooms());
		this.player = new Player(world);
		this.populator = new Populator(player,world);
		populator.populate();
	}

	@Override
	protected void loop() {
		this.player.move();
		for (Enemy enemy : this.world.getRoomAt(player.getWorldX(), player.getWorldY()).getEnemies()) {
			enemy.updateFacing();
			enemy.move();
		}
		for (Projectile projectile:this.world.getRoomAt(this.player.getWorldX(), this.player.getWorldY()).getProjectiles()) {
			projectile.move();
		}
		this.collisions();
		this.killEntities();
	}
	
	@Override
	protected void mouseMoved(MouseEvent e) {
		this.player.mouseMoved(e);
	}
	
	@Override
	protected void mouseClicked(MouseEvent e) {
	}
	
	private void killEntities() {
		this.world.getRoomAt(player.getWorldX(), player.getWorldY()).killEnemies();
	}
	
	private void collisions() {
		Room currentRoom = this.world.getRoomAt(player.getWorldX(), player.getWorldY());
		for(int i=0;i<Resources.WIDTH_IN_TILES;i++) {
			for(int j=0;j<Resources.HEIGHT_IN_TILES;j++) {
				this.player.handleCollisionWithTile(currentRoom.getTileAt(i, j));
				if (currentRoom.hasEnemies()) {
					for(Enemy enemy : currentRoom.getEnemies()) {
						enemy.handleCollisionWithTile(currentRoom.getTileAt(i, j));
					}
				if (currentRoom.hasProjectiles()) {
					for(Projectile projectile : currentRoom.getProjectiles()) {
						if (currentRoom.getTileAt(i, j).contains(projectile.x,projectile.y) && currentRoom.getTileAt(i, j).isWall()) {
							currentRoom.removeProjectile(projectile);
						}
					}
				}
				}
			}
		}
		Iterator<Projectile> iterator = currentRoom.getProjectiles().iterator();
		for(Enemy enemy : currentRoom.getEnemies()) {
			enemy.handleCollisionWithEntity(this.player);
			while (iterator.hasNext()) {
				Projectile currentProjectile = iterator.next();
				if (enemy.contains(currentProjectile.getCenterX(),currentProjectile.getCenterY())) {
					currentProjectile.attack(enemy);
					iterator.remove();
				}
			}
		}
	}
	@Override 
	protected void render(Graphics g) {
		this.world.getRoomAt(player.getWorldX(),player.getWorldY()).render(g);
		this.player.render(g);
		this.player.getGun().render(g);
		for (Projectile projectile:this.world.getRoomAt(player.getWorldX(),player.getWorldY()).getProjectiles()) {
			projectile.render(g);
		}
		for (Enemy enemy: this.world.getRoomAt(player.getWorldX(), player.getWorldY()).getEnemies()) {
			enemy.render(g);
		}
		GUI.render(g, this.player, this.world);
	}
	@Override
	protected void keyPressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_W:
			this.player.setMovingUp(true);
			break;
		case KeyEvent.VK_A:
			this.player.setMovingLeft(true);
			break;
		case KeyEvent.VK_S:
			this.player.setMovingDown(true);
			break;
		case KeyEvent.VK_D:
			this.player.setMovingRight(true);
			break;
		case KeyEvent.VK_Q:
			System.exit(0);
			break;
		}
		player.keyPressed(keyCode);
	}

	@Override
	protected void keyReleased(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_W:
			this.player.setMovingUp(false);
			break;
		case KeyEvent.VK_A:
			this.player.setMovingLeft(false);
			break;
		case KeyEvent.VK_S:
			this.player.setMovingDown(false);
			break;
		case KeyEvent.VK_D:
			this.player.setMovingRight(false);
			break;
		}
		player.keyReleased(keyCode);
	}
		
}
	

