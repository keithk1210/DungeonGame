package entities;

import java.awt.Color;
import java.awt.Graphics;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import resources.Resources;
import utils.MathHelper;
import utils.Structs.Coord;
import world.Tile;

public class Enemy extends Entity {
	
	static final long serialVersionUID = 1L;
	private Player target;
	
	public Enemy(Player _target) {
		super(MathHelper.randomInt(1, Resources.WIDTH_IN_TILES-1),(MathHelper.randomInt(1, Resources.HEIGHT_IN_TILES-1)));
		target = _target;
	}
	
	@Override
	public void move() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)this.getX(),(int)this.getY(), Tile.SIZE, Tile.SIZE);
		drawLines(g);
	}
	
	public void drawLines(Graphics g) {
		//g.setColor(Color.white);
		//Line north = new Line((double)0,(double)Resources.MIDDLE_SCREEN_Y,(double)Resources.SCREEN_WIDTH,(double)Resources.MIDDLE_SCREEN_Y);
		/*
		if (getReferencePoint() != null)
			g.drawLine((int)this.getCenterX(),(int)this.getCenterY(), getReferencePoint().getX(), getReferencePoint().getY());
		*/
		Line line1 = new Line((double)0,(double)Resources.MIDDLE_SCREEN_Y,(double)Resources.SCREEN_WIDTH,(double)Resources.MIDDLE_SCREEN_Y);
		Line line2 = new Line((double)Resources.MIDDLE_SCREEN_X,(double)0,(double)Resources.MIDDLE_SCREEN_X,(double)Resources.SCREEN_HEIGHT);
		Shape shape = line1.intersect(line1,line2);
		System.out.println(shape.layoutXProperty() + " " + shape.layoutYProperty());
	}
	
	public enum Quadrant {
		I(Resources.MIDDLE_SCREEN_X,Resources.SCREEN_WIDTH,0,Resources.MIDDLE_SCREEN_Y, new Coord(Resources.SCREEN_WIDTH,0)),
		II(0,Resources.MIDDLE_SCREEN_X, 0,Resources.MIDDLE_SCREEN_Y, new Coord(0,0)),
		III(0, Resources.MIDDLE_SCREEN_X, Resources.MIDDLE_SCREEN_Y,Resources.SCREEN_HEIGHT, new Coord(0,Resources.SCREEN_HEIGHT)),
		IV(Resources.MIDDLE_SCREEN_X, Resources.SCREEN_WIDTH, Resources.MIDDLE_SCREEN_Y,Resources.SCREEN_HEIGHT, new Coord(Resources.SCREEN_WIDTH, Resources.SCREEN_HEIGHT));
		
		public int minX;
		public int maxX;
		public int minY;
		public int maxY;
		public Coord referencePoint;

		private Quadrant(int minX, int maxX, int minY, int maxY, Coord referencePoint) {
			this.minX = minX;
			this.maxX = maxX;
			this.minY = minY;
			this.maxY = maxY;
			this.referencePoint = referencePoint;
		}
	}
	
	public Coord getReferencePoint() {
		for (Quadrant quadrant : Quadrant.values()) {
			if (target.getCenterX() > quadrant.minX && target.getCenterX() < quadrant.maxX && target.getCenterY() > quadrant.minY && target.getCenterY() < quadrant.maxY) {
				return quadrant.referencePoint;
			}
		}
		return null;
	}
	
	public double getSlope() {
		return (target.getCenterY() - this .getCenterY())/(target.getCenterX() - this.getCenterX());
	}
}
