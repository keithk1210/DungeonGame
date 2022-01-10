package world;

import java.awt.Rectangle;

public class Tile extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	private boolean wall;
	private byte id;
	
	public static final int SIZE = 100;
	
	public Tile(byte _id, int posXinRoom, int posYinRoom, boolean isWall) {
		super(SIZE * posXinRoom, SIZE * posYinRoom, SIZE, SIZE);
		id = _id;
		wall = isWall;
	}
	
	public byte getId() {
		return id;
	}
	
	public boolean isWall() {
		return wall;
	}

}
