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
import game.entities.Entity;
import game.entities.Player;
import game.entities.items.ItemEntity;
import game.entities.projectiles.Projectile;
import game.inventory.Inventory;
import game.world.Room;
import game.world.World;
import game.world.generator.LevelGenerator;
import game.world.generator.Populator;

public class PlayingState extends GameState {
	
	private LevelGenerator generator;
	private World world;
	private Player player;
	private Populator populator;
	
	protected PlayingState(GameStateManager manager) {
		super(manager);
		this.world = new World(Resources.GAME_WORLD_SIZE);
		this.generator = new LevelGenerator(world);
		generator.generate();
		world.setRooms(generator.getRooms());
		this.player = new Player(world, new Inventory());
		this.populator = new Populator(player,world);
		populator.populate();
	}
	
	@Override
	protected void mouseMoved(MouseEvent e) {
		
	}
	
	protected void mouseClicked(MouseEvent e) {}
	
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
	
	private void killEntities() {
		this.world.getRoomAt(player.getWorldX(), player.getWorldY()).killEnemies();
		if (player.getHealth() <= 0) {
			this.gameStateManager.stackState(new GameOverState(this.gameStateManager));
		}
	}
	

	private void collisions() {
		Room currentRoom = this.world.getRoomAt(player.getWorldX(), player.getWorldY());
		Iterator<Entity> entityIterator = currentRoom.getEntities().iterator();
		Iterator<Projectile> projectileIterator = currentRoom.getProjectiles().iterator();
		while (entityIterator.hasNext()) {
			Entity currentEntity = entityIterator.next();
			for(int i=0;i<Resources.WIDTH_IN_TILES;i++) {
				for(int j=0;j<Resources.HEIGHT_IN_TILES;j++) {
					this.player.handleCollisionWithTile(currentRoom.getTileAt(i, j));
						if (!(currentEntity instanceof Projectile)) {
							currentEntity.handleCollisionWithTile(currentRoom.getTileAt(i, j));
						}
						if (currentEntity instanceof Projectile) {
							if (currentRoom.getTileAt(i, j).contains(currentEntity.x,currentEntity.y) && currentRoom.getTileAt(i, j).isWall()) {
								entityIterator.remove();
							}
						}
					}
				}
			
			if (currentEntity instanceof ItemEntity) {
				ItemEntity currentItemEntity = (ItemEntity) currentEntity;
				if (this.player.contains(currentEntity.getCenterX(),currentEntity.getCenterY())) {
					this.player.pickUpItem(currentItemEntity.getInventorySlot());
					System.out.println("inside loop" + currentItemEntity.getInventorySlot().getItem().getItemID());
					entityIterator.remove();
				}
			}
		}
		for (Enemy enemy : currentRoom.getEnemies()) {
			enemy.handleCollisionWithEntity(this.player);
			while (projectileIterator.hasNext()) {
				Projectile currentProjectile = projectileIterator.next();
				if (enemy.contains(currentProjectile.getCenterX(),currentProjectile.getCenterY())) {
					currentProjectile.attack(enemy);
					currentRoom.removeEntity(currentProjectile);
				}
			}
		}
	}
	@Override
	protected void render(Graphics g) {
		this.world.getRoomAt(player.getWorldX(),player.getWorldY()).render(g);
		this.player.render(g);
		GUI.render(g,this.player,this.world);
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
		case KeyEvent.VK_E:
			gameStateManager.stackState(new InventoryState(this.gameStateManager,this.player));
			break;
		case KeyEvent.VK_R:
			this.player.useItem();
			break;
		case KeyEvent.VK_Q:
			System.exit(0);
			break;
		}
		this.player.getGun().keyPressed(keyCode);
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
		this.player.getGun().keyReleased(keyCode);
	}
	
}