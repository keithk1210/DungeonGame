package game.entities;

import framework.resources.Resources;
import framework.utils.MathHelper;
import framework.utils.MathHelper.Direction;
import game.entities.items.ItemEntity;
import game.inventory.Inventory;
import game.inventory.InventorySlot;
import game.world.Tile;

public class Enemy extends Entity {
	
	static final long serialVersionUID = 1L;
	private Player target;
	private static final double ERROR = .5;
	private int dx;
	private int dy;
	private Inventory inventory;

	
	public Enemy(Player _target, Inventory inventory) {
		super(Resources.SKELETON_FRONT, MathHelper.randomInt(1, Resources.WIDTH_IN_TILES-1)*Tile.SIZE,(MathHelper.randomInt(1, Resources.HEIGHT_IN_TILES-1))*Tile.SIZE,Tile.SIZE);
		this.inventory = inventory;
		target = _target;
		this.health = 6;
		this.attack = 5;
		this.dx = 0;
		this.dy = 0;
		this.up = true;
	}
	
	@Override
	public void move() {
		this.updateSprite();
		this.setDxAndDy();
		this.setCenterX((int)this.getCenterX()+dx);
		this.setCenterY((int)this.getCenterY()+dy);
	}
	
	@Override 
	public void updateFacing() {
		if (Integer.signum(dx) == 1 && Integer.signum(dy) == 1) {
			if (dy > dx) {
				this.facing = Direction.SOUTH;
				this.up = false;
				this.down = true;
				this.left = false;
				this.right = false;
			} else {
				this.facing = Direction.EAST;
				this.up = false;
				this.down = false;
				this.left = false;
				this.right = true;
			}
		} else if (Integer.signum(dx) == -1 && Integer.signum(dy) == 1) {
			if (dy > Math.abs(dx)) {
				this.facing = Direction.SOUTH;
				this.up = false;
				this.down = true;
				this.left = false;
				this.right = false;
			} else {
				this.facing = Direction.WEST;
				this.up = false;
				this.down = false;
				this.left = false;
				this.right = true;
			}
		} else if (Integer.signum(dx) == 1 && Integer.signum(dy) == -1) {
			if (Math.abs(dy) > dx) {
				this.facing = Direction.NORTH;
				this.up = true;
				this.down = false;
				this.left = false;
				this.right = false;
			} else {
				this.facing = Direction.EAST;
				this.up = false;
				this.down = false;
				this.left = true;
				this.right = false;
			}
		} else if (Integer.signum(dx) == -1 && Integer.signum(dy) == -1) {
			if (Math.abs(dy) > Math.abs(dx)) {
				this.facing = Direction.NORTH;
				this.up = true;
				this.down = false;
				this.left = false;
				this.right = false;
			} else {
				this.facing = Direction.WEST;
				this.up = false;
				this.down = false;
				this.left = false;
				this.right = true;
			}
		}
	}
	
	private void setDxAndDy() {
		setDxAndDyIfTargetIsDirectlyHorizontalOrVertical();
		setDxAndDyIfTargetIsDiagonal();
	}
	
	private void setDxAndDyIfTargetIsDiagonal() {
		if (this.getCenterX() > target.getCenterX() && this.getCenterY() > target.getCenterY()) {
			double angle = Math.atan2((this.getCenterY()-target.getCenterY()),(this.getCenterX()-target.getCenterX()));
			for (double y = this.getCenterY(); y > target.getCenterY(); y -= 0.1) {
				for (double x = this.getCenterX(); x > target.getCenterX(); x -=0.1) {
					double a = this.getCenterX()-x;
					double b = this.getCenterY()-y;
					if (Math.tan(angle) - (b/a) < ERROR) {
						double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
						if (Math.abs(c- this.speed) < ERROR) {
							//super.setCenterX((int)(this.getCenterX()-a));
							//super.setCenterY((int)(this.getCenterY()-b));
							this.dx = (int) -a;
							this.dy = (int )-b;
							return;
						}
					}
				}
			}
	} else if (this.getCenterX() > target.getCenterX() && this.getCenterY() < target.getCenterY()) {
		double angle = Math.atan2((target.getCenterY()-this.getCenterY()),(this.getCenterX()-target.getCenterX()));
		for (double y = this.getCenterY(); y < target.getCenterY(); y += 0.1) {
			for (double x = this.getCenterX(); x > target.getCenterX(); x -=0.1) {
				double a = this.getCenterX()-x;
				double b = y-this.getCenterY();
				if (Math.tan(angle) - (b/a) < ERROR) {
					double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
					if (Math.abs(c- this.speed) < ERROR) {
						//super.setCenterX((int)(this.getCenterX() - a));
						//super.setCenterY((int)(this.getCenterY() + b));
						this.dx = (int) -a;
						this.dy = (int ) b;
						return;
					}
				}
			}
		}
		
	} else if (this.getCenterX() < target.getCenterX() && this.getCenterY() < target.getCenterY()) {
		double angle = Math.atan2(target.getCenterY()-this.getCenterY(),(target.getCenterX()-this.getCenterX()));
		for (double y = this.getCenterY(); y < target.getCenterY(); y += 0.1) {
			for (double x = this.getCenterX(); x < target.getCenterX(); x +=0.1) {
				double a = x -this.getCenterX();
				double b = y - this.getCenterY();
				if (Math.tan(angle) - (b/a) < ERROR) {
					double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
					if (Math.abs(c - this.speed) < ERROR) {
						//super.setCenterX((int)(this.getCenterX() + a));
						//super.setCenterY((int)(this.getCenterY() + b));
						this.dx = (int) a;
						this.dy = (int ) b;
						return;
					}
				}
			}
		}
	} else if (this.getCenterX() < target.getCenterX() && this.getCenterY() > target.getCenterY()) {
		double angle = Math.atan2((this.getCenterY()-target.getCenterY()),(target.getCenterX()-this.getCenterX()));
			for (double y = this.getCenterY(); y > target.getCenterY(); y -= 0.1) {
				for (double x = this.getCenterX(); x < target.getCenterX(); x +=0.1) {
					double a = x-this.getCenterX();
					double b = this.getCenterY()-y;
					//System.out.println(a + " " + b);
					if (Math.tan(angle) - (b/a) < ERROR) {
						double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
						if (Math.abs(c-this.speed) < ERROR) {
							//super.setCenterX((int)(this.getCenterX() + a));
							//super.setCenterY((int)(this.getCenterY() - b));
							this.dx = (int) a;
							this.dy = (int ) -b;
							return;
						}
					}
				}
			}
		}
	}
	private void setDxAndDyIfTargetIsDirectlyHorizontalOrVertical() {
		if (this.getCenterX() == target.getCenterX()) {
			if (this.getCenterY() > target.getCenterY()) {
				this.dx = 0;
				this.dy = -speed;
			} else {
				this.dx = 0;
				this.dy = speed;
			}
		} else if (this.getCenterY() == target.getCenterY()) {
			if (this.getCenterX() > target.getCenterX()) {
				this.dx = -speed;
				this.dy = 0;
			} else {
				this.dx = speed;
				this.dy = 0;
			}
		}
	}
	
	public void dropItems() {
		for (InventorySlot slot : this.inventory.getContents()) {
			target.getWorld().getRoomAt(target.getWorldX(), target.getWorldY()).spawnItemEntity(new ItemEntity(slot.getItem().getId(),(int)this.getCenterX(),(int)this.getCenterY(),Tile.SIZE/2,slot));
		}
	}
	
}
