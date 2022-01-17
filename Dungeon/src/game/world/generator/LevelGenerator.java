package game.world.generator;
import java.util.Iterator;

import framework.gui.GUI;
import framework.utils.MathHelper;
import framework.utils.MathHelper.Direction;
import game.world.Room;
import game.world.World;

public class LevelGenerator {
	
	private Room[][] rooms;
	private World world;
	
	public LevelGenerator(World _world) {
		world = _world;
	}
	

	public void generate() {
		GUI.initlaizeDiscovered();
		rooms = new Room[world.getSize()][world.getSize()];
		for (int y = 0; y < world.getSize(); y++) {
			for (int x = 0; x < world.getSize(); x++) {
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
		for (int y = 0; y < world.getSize(); y++) {
			for (int x = 0; x < world.getSize(); x++) {
				rooms[y][x].setDoorTiles();
			}
		}
	}
	
	private void connectExits() {
		for (int y = 0; y < world.getSize(); y++) {
				for (int x = 0; x < world.getSize(); x++) {
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
		return x >= 0 && x < world.getSize() && y >= 0 && y < world.getSize();
	}
	
	public Room[][] getRooms() {
		return rooms;
	}
	private void printRooms() {
		for (int y = 0; y < world.getSize(); y++) {
			for (int x = 0; x < world.getSize(); x++) {
				System.out.print("x " + x + " y " + y + " " + rooms[y][x].getExits());
			}
			System.out.println("");
		}
	}
	
}
