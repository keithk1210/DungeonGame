package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathHelper {
	
	private static final Random r = new Random();
	private static final int probOfDoor = 3;
	
	public enum Direction {
		NORTH(0, -1),
		SOUTH(0, 1),
		WEST(-1, 0),
		EAST(1, 0);
		
		public int dirX;
		public int dirY;
		public Direction opposite;
		
		static {
			NORTH.opposite = SOUTH;
			SOUTH.opposite = NORTH;
			WEST.opposite = EAST;
			EAST.opposite = WEST;
		}
		
		private Direction(int dirX, int dirY) {
			this.dirX = dirX;
			this.dirY = dirY;
		}
	}
	
	public static int randomInt(int upperBound) {
		return r.nextInt(upperBound);
	}

	public static int randomInt(int lowerBound, int upperBound) {
		return r.nextInt(lowerBound, upperBound);
	}
	
	static public List<Direction> randomExits() {
		List<Direction> exits = new ArrayList<Direction>();
		if (rollDice()) {
			exits.add(Direction.NORTH);
		}
		if (rollDice()) {
			exits.add(Direction.SOUTH);
		}
		if (rollDice()) {
			exits.add(Direction.EAST);
		}
		if (rollDice()) {
			exits.add(Direction.WEST);
		}
		return exits;
	}
	
	static public Direction randomExit() {
		return Direction.values()[r.nextInt(Direction.values().length)];
	}
	
	static public boolean rollDice() {
		if (r.nextInt(probOfDoor) == probOfDoor-1) {
			return true;
		}
		return false;
	}

}
