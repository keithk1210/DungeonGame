package framework.resources;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.world.Tile;

public class Resources {
	
	public static final int DELAY = 500;
	public static final int SCREEN_WIDTH = 900;
	public static final int SCREEN_HEIGHT = 900;
	public static final int MIDDLE_SCREEN_X = SCREEN_WIDTH/2;
	public static final int MIDDLE_SCREEN_Y = SCREEN_HEIGHT/2;
	public static final int MIDDLE_X = (SCREEN_WIDTH-Tile.SIZE) / 2;
	public static final int MIDDLE_Y = (SCREEN_HEIGHT-Tile.SIZE) / 2;
	public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / Tile.SIZE;
	public static final int WIDTH_IN_TILES = SCREEN_WIDTH/Tile.SIZE;
	public static final int HEIGHT_IN_TILES = SCREEN_HEIGHT/Tile.SIZE;
	public static final int MIDDLE_TILE_X = (WIDTH_IN_TILES-1)/2;
	public static final int MIDDLE_TILE_Y = (HEIGHT_IN_TILES-1)/2;
	public static final int MAX_DOORS = 5;
	public static final int PROB_OF_ENEMY = 100;
	
	public static final int GAME_WORLD_SIZE = 
			5;
	public static final int DEBUG_WORLD_SIZE = 1;
	public static final int MIDDLE_WORLD = 2;
	
	//gui
	
	public static final int MAP_ROOM_SIZE = 5;
	public static final int MAP_ROOM_MID_TILE = (MAP_ROOM_SIZE-1)/2;
	public static final int MAP_UNIT_SIZE = Tile.SIZE/GAME_WORLD_SIZE;
	public static final int MIDDLE_MAP_UNIT = MAP_UNIT_SIZE/2;
	public static final int TILE_ON_MAP_SIZE_WIDTH = MAP_UNIT_SIZE/MAP_ROOM_SIZE;
	public static final int TILE_ON_MAP_SIZE_HEIGHT = MAP_UNIT_SIZE/MAP_ROOM_SIZE;
	

	//tile ids
	public static final byte FLOOR = 0;
	public static final byte WALL = 1;
	public static final byte DOOR = 3;
	public static final byte LOCKED = 4;
	public static final byte PLAYER = 5;
	
	//texture ids
	
	public static final byte BROWN_WALL_FRONT_FACE = 0;
	public static final byte BROWN_WALL_ABOVE = 1;
	public static final byte BROWN_FLOOR = 2;
	public static final byte CONJ_BLUE_FRONT = 3;
	public static final byte CONJ_BLUE_FRONT_LEFT = 4;
	public static final byte CONJ_BLUE_FRONT_RIGHT = 5;
	public static final byte CONJ_BLUE_LEFT = 6;
	public static final byte CONJ_BLUE_LEFT_LEFT = 7;
	public static final byte CONJ_BLUE_LEFT_RIGHT = 8;
	public static final byte CONJ_BLUE_RIGHT = 9;
	public static final byte CONJ_BLUE_RIGHT_LEFT = 10;
	public static final byte CONJ_BLUE_RIGHT_RIGHT = 11;
	public static final byte CONJ_BLUE_BACK = 12;
	public static final byte CONJ_BLUE_BACK_LEFT = 13;
	public static final byte CONJ_BLUE_BACK_RIGHT = 14;
	public static final byte ENEMY = 15;
	public static final byte BULLET = 16;
	public static final Color WALL_COLOR = Color.black;
	public static final Color PLAYER_COLOR = Color.blue;
	
	public static final ArrayList<BufferedImage> TEXTURES = new ArrayList<BufferedImage>();

}
