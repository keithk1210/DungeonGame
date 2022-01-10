package utils;

public class MathHelper {
	
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

}
