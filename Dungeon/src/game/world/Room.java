package game.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import framework.resources.Resources;
import framework.resources.TextureID;
import framework.utils.MathHelper;
import framework.utils.MathHelper.Direction;
import game.entities.Enemy;
import game.entities.Entity;
import game.entities.items.ItemEntity;
import game.entities.projectiles.Projectile;

public class Room {
	
	private HashSet<MathHelper.Direction> exits;
	private int posXInWorld;
	private int posYInWorld;
	private Tile[][] tiles;
	private HashSet<Entity> entities;
	private HashSet<Projectile> projectiles;
	
	public Room (int _posXInWorld, int _posYInWorld, MathHelper.Direction... _exits) {
		posXInWorld = _posXInWorld;
		posYInWorld = _posYInWorld;
		tiles = new Tile[Resources.WIDTH_IN_TILES][Resources.HEIGHT_IN_TILES];
		exits = new HashSet<MathHelper.Direction>();
		this.entities = new HashSet<Entity>();
		for (MathHelper.Direction direction : _exits) {
			this.exits.add(direction);
		}
		setFloorTiles();
		setWallTiles();
	}
	
	public Room() {}
	
	private void setFloorTiles() {
		for (int y = 0; y < Resources.WIDTH_IN_TILES; y++) {
			for (int x = 0; x < Resources.HEIGHT_IN_TILES; x++) {
				tiles[y][x] = new Tile(Resources.FLOOR,x,y,false);
			}
		}
	}
	
	private void setWallTiles() {
		for (int i = 0; i < Resources.WIDTH_IN_TILES; i++) {
			tiles[0][i] = new Tile(Resources.WALL,i,0,true);
			tiles[Resources.HEIGHT_IN_TILES-1][i] = new Tile(Resources.WALL,i,Resources.HEIGHT_IN_TILES-1,true);
		}
		
		for (int i = 0; i < Resources.HEIGHT_IN_TILES; i++) {
			tiles[i][0] = new Tile(Resources.WALL,0,i,true);
			tiles[i][Resources.WIDTH_IN_TILES-1] = new Tile(Resources.WALL,Resources.WIDTH_IN_TILES-1,i,true);
		}
	}
	public void setDoorTiles() {
		for (MathHelper.Direction direction : exits) {
			switch(direction) {
				case NORTH:
					tiles[0][Resources.MIDDLE_TILE_X] = new Tile(Resources.DOOR,Resources.MIDDLE_TILE_X,0,false);
					break;
				case SOUTH:
					tiles[Resources.HEIGHT_IN_TILES-1][Resources.MIDDLE_TILE_X] = new Tile(Resources.DOOR,Resources.MIDDLE_TILE_X,Resources.HEIGHT_IN_TILES-1,false);
					break;
				case WEST:
					tiles[Resources.MIDDLE_TILE_Y][0] = new Tile(Resources.DOOR,0,Resources.MIDDLE_TILE_Y,false);
					break;
				case EAST:
					tiles[Resources.MIDDLE_TILE_Y][Resources.WIDTH_IN_TILES-1] = new Tile(Resources.DOOR,Resources.WIDTH_IN_TILES-1,Resources.MIDDLE_TILE_Y,false);
					break;
				
			}
		}
	}
	
	public Tile getTileAt(int x, int y) {
		return tiles[y][x];
	}
	
	public void render(Graphics g) {
		for (int y = 0; y < tiles.length; y++) {
			for(int x = 0; x < tiles[y].length; x++) {
				if (tiles[y][x].getId() == Resources.WALL) {
					if (((x == 0 || x == tiles[y].length - 1) && y == Resources.MIDDLE_TILE_Y - 1) && tiles[y+1][x].getId() == Resources.DOOR) {
						g.drawImage(Resources.TEXTURES.get(TextureID.BROWN_WALL_FRONT_FACE), x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE, null);
						continue;
					} else if  ((x == 0 || x == tiles[y].length - 1) && y != tiles.length - 1) {
						g.drawImage(Resources.TEXTURES.get(TextureID.BROWN_WALL_ABOVE), x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE, null);
						continue;
					} else {
						g.drawImage(Resources.TEXTURES.get(TextureID.BROWN_WALL_FRONT_FACE), x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE, null);
					}
				} else if (tiles[y][x].getId() == Resources.FLOOR || tiles[y][x].getId() == Resources.DOOR) {
					g.drawImage(Resources.TEXTURES.get(TextureID.BROWN_FLOOR), x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE, null);
				}
			}
			
		}
		for (Entity entity : this.entities) {
			entity.render(g);
		}
		
	}
	
	public int getX() {
		return posXInWorld;
	}
	public int getY() {
		return posYInWorld;
	}
	
	public HashSet<MathHelper.Direction> getExits() {
		return exits;
	}
	
	public void addExit(Direction d) {
		exits.add(d);
	}
	public void removeExit(Direction d) {
		exits.remove(d);
	}
	
	public void spawnEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void spawnProjectile(Projectile projectile) {
		this.entities.add(projectile);
		this.projectiles.add(projectile);
	}
	
	public HashSet<Entity> getEntities() {
		return this.entities;
	}
	
	public HashSet<Projectile> getProjectiles() {
		HashSet<Projectile> projectiles = new HashSet<Projectile>();
		for (Entity entity : this.entities) {
			if (entity instanceof Projectile) {
				Projectile projectile = (Projectile) entity;
				projectiles.add(projectile);
			}
		}
		return projectiles;
	}
	
	public HashSet<Enemy> getEnemies() {
		HashSet<Enemy> enemies = new HashSet<Enemy>();
		for (Entity entity : this.entities) {
			if (entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				enemies.add(enemy);
			}
		}
		return enemies;
	}
	public boolean hasEnemies() {
		for (Entity e : this.entities) {
			if (e instanceof Enemy) {
				return true;
			}
		}
		return false;
	}

	public void removeEntity(Entity entity) {
		 Iterator<Entity> iterator = this.entities.iterator();
	        while(iterator.hasNext())
	        {
	            if(iterator.next().equals(entity))
	                iterator.remove();
	        }
	}
	
	public void killEnemies() {
		HashSet<Enemy> toKill = new HashSet<Enemy>();
		Iterator<Entity> iterator = this.entities.iterator();
		while (iterator.hasNext()) {
			Entity entity = iterator.next();
            if(entity instanceof Enemy) {
            	if (entity.getHealth() <= 0) {
	            	Enemy enemy = (Enemy) entity;
	            	iterator.remove();
	            	toKill.add(enemy);
            	}
            }
		}
		for (Enemy enemy : toKill) {
			enemy.dropItems();
		}
	}

	public boolean hasProjectiles() {
		for (Entity e : this.entities) {
			if (e instanceof Projectile) {
				return true;
			}
		}
		return false;
	}
	public boolean hasItemEntites() {
		for (Entity e : this.entities) {
			if (e instanceof ItemEntity) {
				return true;
			}
		}
		return false;
	}
}
