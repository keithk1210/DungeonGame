package game.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import framework.gamestates.GameState;
import framework.gamestates.GameStateManager;
import framework.gui.GUI;
import framework.resources.Resources;
import game.entities.Enemy;
import game.entities.Player;
import game.entities.items.ItemEntity;
import game.entities.projectiles.Projectile;
import game.inventory.Inventory;
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
		this.player = new Player(world, new Inventory());
		this.populator = new Populator(player,world);
		populator.populate();
	}

	@Override
	protected void loop() {
		this.player.move();
		this.player.decrementInvulTime();
		for (Enemy enemy : this.world.getRoomAt(player.getWorldX(), player.getWorldY()).getEnemies()) {
			enemy.decrementInvulTime();
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
		if (player.getHealth() <= 0) {
			this.gameStateManager.stackState(new GameOverState(this.gameStateManager));
		}
	}
	
	private void collisions() {
		Room currentRoom = this.world.getRoomAt(player.getWorldX(), player.getWorldY());
		
		//tile collisions
		for(int i=0;i<Resources.WIDTH_IN_TILES;i++) {
			for(int j=0;j<Resources.HEIGHT_IN_TILES;j++) {
				this.player.handleCollisionWithTile(currentRoom.getTileAt(i, j));
				if (currentRoom.hasEnemies()) {
					for(Enemy enemy : currentRoom.getEnemies()) {
						enemy.handleCollisionWithTile(currentRoom.getTileAt(i, j));
					}
				if (currentRoom.hasProjectiles()) {
					Iterator<Projectile> iterator = currentRoom.getProjectiles().iterator();
					while(iterator.hasNext()) {
						Projectile currentProjectile = iterator.next();
						if (currentRoom.getTileAt(i, j).contains(currentProjectile.x,currentProjectile.y) && currentRoom.getTileAt(i, j).isWall()) {
							iterator.remove();
						}
					}
				}
				}
			}
		}
		//attacks player and handles projectiles
		Iterator<Projectile> projectileIterator = currentRoom.getProjectiles().iterator();
		for(Enemy enemy : currentRoom.getEnemies()) {
			enemy.handleCollisionWithEntity(this.player);
			while (projectileIterator.hasNext()) {
				Projectile currentProjectile = projectileIterator.next();
				if (enemy.contains(currentProjectile.getCenterX(),currentProjectile.getCenterY())) {
					currentProjectile.attack(enemy);
					projectileIterator.remove();
				}
			}
		}
		//lets player pick up item
		Iterator<ItemEntity> itemEntityIterator = currentRoom.getItemEntities().iterator();
		while (itemEntityIterator.hasNext()) {
			ItemEntity currentItemEntity = itemEntityIterator.next();
			if (this.player.contains(currentItemEntity.getCenterX(),currentItemEntity.getCenterY())) {
				player.pickUpItem(currentItemEntity.getInventorySlot());
				itemEntityIterator.remove();
			}
		}
		
		
	}
	@Override 
	protected void render(Graphics g) {
		Room currentRoom = this.world.getRoomAt(player.getWorldX(),player.getWorldY());
		currentRoom.render(g);
		
		this.player.render(g);
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
	

