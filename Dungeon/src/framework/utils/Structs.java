package framework.utils;



public class Structs {
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
		
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}
		
	}
}
