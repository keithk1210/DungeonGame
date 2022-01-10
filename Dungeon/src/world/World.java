package world;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class World {
	
	private List<Room> rooms;
	private Room currentRoom;
	
	public World() {
		rooms = new ArrayList<Room>();
	}
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	
	public Room getRoomAt(int x, int y) {
		for(Room room : rooms) {
				if (x == room.getX() && y == room.getY()) {
					return room;
		}
	}
		return null;
	}
}
