package world;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import resources.Resources;

public class World {
	
	private Room[][] rooms;
	
	
	public World() {
		rooms = new Room[Resources.WORLD_SIZE][Resources.WORLD_SIZE];
	}
	
	public void setRooms(Room[][] _rooms) {
		rooms = _rooms;
	}
	
	public Room getRoomAt(int x, int y) {
		return rooms[y][x];
	}
}
