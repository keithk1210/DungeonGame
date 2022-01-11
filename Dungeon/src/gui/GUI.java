package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import entities.Player;
import resources.Resources;
import world.Tile;

public class GUI {
	
	static private boolean discovered[][];
	static private Queue<Coord> currentCoords = new LinkedList<Coord>();
	static private List<Line> lines = new ArrayList<Line>();
	
	public static void render(Graphics g, Player player) {
		g.setFont(new Font("Araial", Font.PLAIN, 50));
		g.drawString("(" + player.getWorldX() + "," + player.getWorldY() + ")", Tile.SIZE/2, Tile.SIZE/2);
		
		
		for (int y = 0; y < Tile.SIZE; y += Resources.MAP_UNIT_SIZE) {
			
			for (int x = 0; x < Tile.SIZE; x += Resources.MAP_UNIT_SIZE) {
				
				if (discovered[y/Resources.MAP_UNIT_SIZE][x/Resources.MAP_UNIT_SIZE]) {
					g.setColor(Color.green);
				} else {
					g.setColor(Color.red);
				}
				g.fillRect(x + (Resources.SCREEN_WIDTH-Tile.SIZE), y, Resources.MAP_UNIT_SIZE, Resources.MAP_UNIT_SIZE);
			}
		}
		g.setColor(Resources.PLAYER_COLOR);
		int playerMapX = Resources.SCREEN_WIDTH-Tile.SIZE + ((player.getWorldX()) * Resources.MAP_UNIT_SIZE) + Resources.MAP_UNIT_SIZE/4;
		int playerMapY = ((player.getWorldY()) * Resources.MAP_UNIT_SIZE) + Resources.MAP_UNIT_SIZE/4;
		if (currentCoords.size() > 2) {
			lines.add(new Line(currentCoords.remove(),currentCoords.remove()));
		}
		g.fillRect(playerMapX, playerMapY, Resources.MAP_UNIT_SIZE/2,Resources.MAP_UNIT_SIZE/2);
		drawLines(g);
		
	}
	
	public static void initlaizeDiscovered() {
		discovered = new boolean[Resources.WORLD_SIZE][Resources.WORLD_SIZE];
		for (int y = 0; y < Resources.WORLD_SIZE; y++) {
			for (int x = 0; x < Resources.WORLD_SIZE; x++) {
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
			System.out.println(coord.getX() + " " +  coord.getY());
		//}
	}
	
	public static void drawLines(Graphics g) {
		if (lines.size() > 0) {
			for (Line line : lines) {
				g.setColor(Color.yellow);
				g.drawLine(line.getC1().getX(), line.getC1().getY(), line.getC2().getX(), line.getC2().getY());
			}
		}
	}
	
	public static class Line {
		private Coord c1;
		private Coord c2;
		
		public Line(Coord _c1, Coord _c2) {
			c1 = _c1;
			c2 = _c2;
			
		}
		
		public Coord getC1() {
			return c1;
		}
		public Coord getC2() {
			return c2;
		}
		
	}
	
	public static class Coord {
		private int x;
		private int y;
		
		public Coord(int _x, int _y) {
			x = _x;
			y = _y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		
	}

}
