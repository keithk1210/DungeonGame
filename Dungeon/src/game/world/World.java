package game.world;

import java.util.HashSet;

import game.entities.projectiles.Projectile;

public class World {
	
	private Room[][] rooms;
	
	private int size;
	
	
	public World(int _size) {
		size = _size;
		rooms = new Room[size][size];
		
	}
	
	public void setRooms(Room[][] _rooms) {
		rooms = _rooms;
	}
	
	public Room getRoomAt(int x, int y) {
		return rooms[y][x];
	}
	public int getSize() {
		return size;
	}
	
	
}
