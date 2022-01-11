package world.generator;
import java.util.Iterator;

import gui.GUI;
import resources.Resources;
import utils.MathHelper;
import utils.MathHelper.Direction;
import world.Room;

public class LevelGenerator {
	
	private Room[][] rooms;
	

	public void generate() {
		GUI.initlaizeDiscovered();
		rooms = new Room[Resources.WORLD_SIZE][Resources.WORLD_SIZE];
		for (int y = 0; y < Resources.WORLD_SIZE; y++) {
			for (int x = 0; x < Resources.WORLD_SIZE; x++) {
				rooms[y][x] = new Room(x,y,MathHelper.randomExits().toArray(Direction[]::new));
				Iterator<MathHelper.Direction> iterator = rooms[y][x].getExits().iterator();
				while (iterator.hasNext()) {
					Direction direction = iterator.next();
					if (!isInWorld(x + direction.dirX, y + direction.dirY)) {
						iterator.remove();
					}
				}
				
			}
		}
		connectExits();
		for (int y = 0; y < Resources.WORLD_SIZE; y++) {
			for (int x = 0; x < Resources.WORLD_SIZE; x++) {
				rooms[y][x].setDoorTiles();
			}
		}
	}
	
	private void connectExits() {
		for (int y = 0; y < Resources.WORLD_SIZE; y++) {
				for (int x = 0; x < Resources.WORLD_SIZE; x++) {
					for (MathHelper.Direction direction : rooms[y][x].getExits()) {
						if (isInWorld(x + direction.dirX, y + direction.dirY)) {
							if (!rooms[y + direction.dirY][x + direction.dirX].getExits().contains(direction.opposite)) {
								rooms[y + direction.dirY][x+ direction.dirX].addExit(direction.opposite);
							}
						}
					}
				}
			}
		}
	
	public boolean isInWorld(int x, int y) {
		return x >= 0 && x <= 4 && y >= 0 && y <= 4;
	}
	
	public Room[][] getRooms() {
		return rooms;
	}
	private void printRooms() {
		for (int y = 0; y < Resources.WORLD_SIZE; y++) {
			for (int x = 0; x < Resources.WORLD_SIZE; x++) {
				System.out.print("x " + x + " y " + y + " " + rooms[y][x].getExits());
			}
			System.out.println("");
		}
	}
	
}
