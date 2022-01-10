package world;

import java.awt.Graphics;
import java.util.HashSet;

import resources.Resources;
import utils.MathHelper;

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
		setDoorTiles();
		
		
	}
	
	public Room() {
		
	}
	
	public void setBlankTiles() {
		for (int x = 1; x < Resources.WIDTH_IN_TILES - 1; x++) {
			for (int y = 1; y < Resources.HEIGHT_IN_TILES - 1; y++) {
				tiles[x][y] = new Tile(Resources.FLOOR,x,y,false);
			}
		}
	}
	
	public void setWallTiles() {
		for (int i = 0; i < Resources.WIDTH_IN_TILES; i++) {
			tiles[0][i] = new Tile(Resources.WALL,0,i,true);
			tiles[Resources.HEIGHT_IN_TILES-1][i] = new Tile(Resources.WALL,Resources.HEIGHT_IN_TILES-1,i,true);
		}
		
		for (int i = 0; i < Resources.HEIGHT_IN_TILES; i++) {
			tiles[i][0] = new Tile(Resources.WALL,i,0,true);
			tiles[i][Resources.WIDTH_IN_TILES-1] = new Tile(Resources.WALL,i,Resources.WIDTH_IN_TILES-1,true);
		}
	}
	public void setDoorTiles() {
		for (MathHelper.Direction direction : exits) {
			switch(direction) {
				case NORTH:
					tiles[0][Resources.MIDDLE_TILE_X] = new Tile(Resources.DOOR,0,Resources.MIDDLE_TILE_X,false);
					break;
				case SOUTH:
					tiles[Resources.HEIGHT_IN_TILES-1][Resources.MIDDLE_TILE_X] = new Tile(Resources.DOOR,Resources.MIDDLE_TILE_X,Resources.HEIGHT_IN_TILES-1,false);
				case WEST:
					tiles[Resources.MIDDLE_TILE_Y][0] = new Tile(Resources.DOOR,0,Resources.MIDDLE_TILE_Y,false);
				case EAST:
					tiles[Resources.MIDDLE_TILE_Y][Resources.WIDTH_IN_TILES-1] = new Tile(Resources.DOOR,Resources.WIDTH_IN_TILES-1,Resources.MIDDLE_TILE_Y,false);
				
				
			}
		}
	}
	
	public Tile getTileAt(int x, int y) {
		return tiles[x][y];
	}
	
	public void render(Graphics g) {
		for (int x = 0; x < tiles[0].length; x++) {
			for(int y = 0; y < tiles.length; y++) {
				if (tiles[x][y].getId() == Resources.WALL) {
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
}
