package world;

import java.awt.Graphics;
import java.util.HashSet;

import resources.Resources;
import utils.MathHelper;
import utils.MathHelper.Direction;

public class Room {
	
	private HashSet<MathHelper.Direction> exits;
	private boolean key;
	private int posXInWorld;
	private int posYInWorld;
	private Tile[][] tiles;
	
	public Room (int _posXInWorld, int _posYInWorld, MathHelper.Direction... _exits) {
		posXInWorld = _posXInWorld;
		posYInWorld = _posYInWorld;
		tiles = new Tile[Resources.WIDTH_IN_TILES][Resources.HEIGHT_IN_TILES];
		exits = new HashSet<MathHelper.Direction>();
		for (MathHelper.Direction direction : _exits) {
			this.exits.add(direction);
		}
		setBlankTiles();
		setWallTiles();
	}
	
	public Room() {
		
	}
	
	private void setBlankTiles() {
		for (int y = 1; y < Resources.WIDTH_IN_TILES - 1; y++) {
			for (int x = 1; x < Resources.HEIGHT_IN_TILES - 1; x++) {
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
		for (int y = 0; y < tiles[0].length; y++) {
			for(int x = 0; x < tiles.length; x++) {
				if (tiles[y][x].getId() == Resources.WALL) {
					g.setColor(Resources.WALL_COLOR);
					g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
				}
			}
			
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
}
