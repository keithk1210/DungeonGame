package framework.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import framework.resources.Resources;
import framework.utils.MathHelper.Direction;
import framework.utils.Structs.Coord;
import framework.utils.Structs.Line;
import game.entities.Player;
import game.world.Tile;
import game.world.World;

public class GUI {
	
	static private boolean discovered[][];
	static private Queue<Coord> currentCoords = new LinkedList<Coord>();
	static private List<Line> lines = new ArrayList<Line>();
	
	public static void render(Graphics g, Player player, World world) {
		g.setColor(Color.red);
		g.setFont(new Font("Araial", Font.PLAIN, 50));
		g.drawString("(" + player.getWorldX() + "," + player.getWorldY() + ")", Tile.SIZE/2, Tile.SIZE/2);
		drawMap(g,player,world);
		drawPlayerInfo(g,player);
	}
	
	private static void drawPlayerInfo(Graphics g, Player player) {
		int x = g.getFontMetrics(new Font("Araial", Font.PLAIN, 50)).stringWidth("(" + player.getWorldX() + "," + player.getWorldY() + ")");
		g.setColor(Color.red);
		g.drawString("HP : " + player.getHealth(), x + Tile.SIZE, Tile.SIZE/2);
	}
	
	private static void drawMap(Graphics g, Player player, World world) {
		for (int y = 0; y < Tile.SIZE; y += Resources.MAP_UNIT_SIZE) {
			
			for (int x = 0; x < Tile.SIZE; x += Resources.MAP_UNIT_SIZE) {
				
				if (discovered[y/Resources.MAP_UNIT_SIZE][x/Resources.MAP_UNIT_SIZE]) {
					g.setColor(Color.white);
					g.fillRect(x + (Resources.SCREEN_WIDTH-Tile.SIZE), y, Resources.MAP_UNIT_SIZE, Resources.MAP_UNIT_SIZE);
					g.setColor(Resources.WALL_COLOR);
					drawRoomOnMap(g, world, x, y);
					
				} else {
					g.setColor(Color.black);
					g.fillRect(x + (Resources.SCREEN_WIDTH-Tile.SIZE), y, Resources.MAP_UNIT_SIZE, Resources.MAP_UNIT_SIZE);
				}
				
			}
		}
		drawLines(g);
		g.setColor(Resources.PLAYER_COLOR);
		int playerMapX = Resources.SCREEN_WIDTH-Tile.SIZE + ((player.getWorldX()) * Resources.MAP_UNIT_SIZE) + Resources.MAP_UNIT_SIZE/4;
		int playerMapY = ((player.getWorldY()) * Resources.MAP_UNIT_SIZE) + Resources.MAP_UNIT_SIZE/4;
		if (currentCoords.size() >= 2) {
			lines.add(new Line(currentCoords.remove(),currentCoords.peek()));
		}
		g.fillRect(playerMapX, playerMapY, Resources.MAP_UNIT_SIZE/2,Resources.MAP_UNIT_SIZE/2);
	}
	
	public static void initlaizeDiscovered() {
		discovered = new boolean[Resources.GAME_WORLD_SIZE][Resources.GAME_WORLD_SIZE];
		for (int y = 0; y < Resources.GAME_WORLD_SIZE; y++) {
			for (int x = 0; x < Resources.GAME_WORLD_SIZE; x++) {
				discovered[y][x] = false;
			}
		}
	}
	
	public static void setDiscovered(int x, int y) {
		discovered[y][x] = true;
	}
	
	public static void addNewCoord(Coord coord) {
		//if(!currentCoords.contains(coord)) {
			currentCoords.add(coord);
		//}
	}
	
	private static void drawLines(Graphics g) {
		if (lines.size() > 0) {
			for (Line line : lines) {
				g.setColor(Color.red);
				g.drawLine(line.getC1().getX(), line.getC1().getY(), line.getC2().getX(), line.getC2().getY());
			}
		}
	}
	private static void drawRoomOnMap(Graphics g, World world, int x, int y) {
		for (int j = 0; j < Resources.MAP_ROOM_SIZE; j++) {
			for (int i = 0; i < Resources.MAP_ROOM_SIZE; i++) {
				if (i == Resources.MAP_ROOM_MID_TILE && j == 0 && world.getRoomAt(x/Resources.MAP_UNIT_SIZE,y/Resources.MAP_UNIT_SIZE).getExits().contains(Direction.NORTH)) {
					continue;
				}
				if (i == Resources.MAP_ROOM_MID_TILE && j == Resources.MAP_ROOM_SIZE-1 && world.getRoomAt(x/Resources.MAP_UNIT_SIZE,y/Resources.MAP_UNIT_SIZE).getExits().contains(Direction.SOUTH)) {
					continue;
				}
				if (i == Resources.MAP_ROOM_SIZE-1 && j == Resources.MAP_ROOM_MID_TILE && world.getRoomAt(x/Resources.MAP_UNIT_SIZE,y/Resources.MAP_UNIT_SIZE).getExits().contains(Direction.EAST)) {
					continue;
				}
				if (i == 0 && j == Resources.MAP_ROOM_MID_TILE && world.getRoomAt(x/Resources.MAP_UNIT_SIZE,y/Resources.MAP_UNIT_SIZE).getExits().contains(Direction.WEST)) {
					continue;
				}
				//checks to see it is a non-wall area
				if (!(i == 0 || i == Resources.MAP_ROOM_SIZE-1 || j == 0 || j == Resources.MAP_ROOM_SIZE - 1)) {
					continue;
				}
				g.fillRect(x + (Resources.SCREEN_WIDTH-Tile.SIZE) + (i * Resources.TILE_ON_MAP_SIZE_WIDTH), y + (j * Resources.TILE_ON_MAP_SIZE_HEIGHT), Resources.TILE_ON_MAP_SIZE_WIDTH, Resources.TILE_ON_MAP_SIZE_HEIGHT);
			}
		}
	}
	


}
