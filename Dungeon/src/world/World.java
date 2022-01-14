package world;

import java.util.HashSet;

import game.projectiles.Projectile;

public class World {
	
	private Room[][] rooms;
	private HashSet<Projectile> projectiles;
	private int size;
	
	
	public World(int _size) {
		size = _size;
		rooms = new Room[size][size];
		projectiles = new HashSet<Projectile>();
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
	
	public HashSet<Projectile> getProjectiles() {
		return projectiles;
	}
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
}
