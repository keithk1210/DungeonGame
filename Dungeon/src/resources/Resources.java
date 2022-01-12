package resources;

import java.awt.Color;

import world.Tile;

public class Resources {
	
	public static final int DELAY = 500;
	public static final int SCREEN_WIDTH = 900;
	public static final int SCREEN_HEIGHT = 900;
	public static final int MIDDLE_X = (SCREEN_WIDTH-Tile.SIZE) / 2;
	public static final int MIDDLE_Y = (SCREEN_HEIGHT-Tile.SIZE) / 2;
	public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / Tile.SIZE;
	public static final int WIDTH_IN_TILES = SCREEN_WIDTH/Tile.SIZE;
	public static final int HEIGHT_IN_TILES = SCREEN_HEIGHT/Tile.SIZE;
	public static final int MIDDLE_TILE_X = (WIDTH_IN_TILES-1)/2;
	public static final int MIDDLE_TILE_Y = (HEIGHT_IN_TILES-1)/2;
	public static final int MAX_DOORS = 5;
	
	public static final int WORLD_SIZE = 5;
	public static final int MIDDLE_WORLD = 2;
	
	//gui
	
	public static final int MAP_ROOM_SIZE = 5;
	public static final int MAP_ROOM_MID_TILE = (MAP_ROOM_SIZE-1)/2;
	public static final int MAP_UNIT_SIZE = Tile.SIZE/WORLD_SIZE;
	public static final int MIDDLE_MAP_UNIT = MAP_UNIT_SIZE/2;
	public static final int TILE_ON_MAP_SIZE_WIDTH = MAP_UNIT_SIZE/MAP_ROOM_SIZE;
	public static final int TILE_ON_MAP_SIZE_HEIGHT = MAP_UNIT_SIZE/MAP_ROOM_SIZE;
	

	
	public static final byte FLOOR = 0;
	public static final byte WALL = 1;
	public static final byte DOOR = 3;
	public static final byte LOCKED = 4;
	
	public static final Color WALL_COLOR = Color.black;
	public static final Color PLAYER_COLOR = Color.blue;

}
