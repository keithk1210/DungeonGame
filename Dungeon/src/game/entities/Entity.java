package game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.utils.MathHelper;
import framework.utils.MathHelper.Direction;
import game.world.Tile;

public class Entity extends Rectangle {
	
private static final long serialVersionUID = 1L;
	
	protected boolean up;
	protected boolean down;
	protected boolean left;
	protected boolean right;
	protected boolean inFlight;
	protected int animationFrame;
	protected int animationDelay;
	protected int motionDelay;
	
	protected int speed;
	protected int health;
	protected int attack;
	
	protected MathHelper.Direction facing;
	protected MathHelper.Direction launchDiretion;
	
	protected byte entityID;
	
	private static final int ERROR = 15;
	
	public Entity(byte _entityID, int posXinRoom, int posYinRoom, int size) {
		super(posXinRoom, posYinRoom, size, size);
		this.entityID = _entityID;
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;
		this.speed = 4;
		this.motionDelay = 0;
	}
	
	public void move() {
		if (this.motionDelay == 0) {
			if(up) {
				this.setCenterY((int)this.getCenterY()-speed);
				this.facing = MathHelper.Direction.NORTH;
			}
			if(down) {
				this.setCenterY((int)this.getCenterY()+speed);
				this.facing = MathHelper.Direction.SOUTH;
			}
			if(left) {
				this.setCenterX((int)this.getCenterX()-speed);
				this.facing = MathHelper.Direction.WEST;
			}
			if(right) {
				this.setCenterX((int)this.getCenterX()+speed);
				this.facing = MathHelper.Direction.EAST;
			}
		} else {
			this.motionDelay--;
			this.setCenterX((int)(this.getCenterX() + (this.launchDiretion.dirX * 10)));
			this.setCenterY((int)(this.getCenterY() + (this.launchDiretion.dirY * 10)));
		}
	}
	
	
	
	public void handleCollisionWithTile(Tile tile) {
		Rectangle intersection = this.intersection(tile);
		if(intersection.isEmpty() || !tile.isWall())
			return;
			
		if(intersection.width > intersection.height) {
			if(this.y < tile.y)
				this.y = tile.y - this.height;
			else
				this.y = tile.y + this.height;
		}
		else {
			if(this.x < tile.x)
				this.x = tile.x - this.width;
			else
				this.x = tile.x + this.width;
		}
	}
	
	public void handleCollisionWithEntity(Entity entity) {
		if (motionDelay == 0) {
			if (entity.getX() >= this.getX()-Tile.SIZE && entity.getX() <= this.getX()+Tile.SIZE) {
				if (Math.abs(entity.getBounds().getMaxY() - this.getBounds().getMinY()) < ERROR) {
					launchEntity(entity,Direction.NORTH);
					this.attack(entity);
					return;
				} else if (Math.abs(entity.getBounds().getMinY() - this.getBounds().getMaxY()) < ERROR){
					launchEntity(entity,Direction.SOUTH);
					this.attack(entity);
					return;
				}
			} else if (entity.getY() >= this.getY()-Tile.SIZE && entity.getY() <= this.getY()+Tile.SIZE) {
				
				if (Math.abs(entity.getBounds().getMinX() - this.getBounds().getMaxX()) < ERROR) {
					launchEntity(entity,Direction.EAST);
					this.attack(entity);
					return;
				} else if (Math.abs(entity.getBounds().getMaxX() - this.getBounds().getMinX()) < ERROR) {
					launchEntity(entity,Direction.WEST);
					this.attack(entity);
					return;
				}
			}
		}
	}

	
	public Color debugLines(Entity entity) {
		if (motionDelay == 0) {
			if (entity.getX() >= this.getX()-Tile.SIZE && entity.getX() <= this.getX()+Tile.SIZE) {
				if (Math.abs(entity.getBounds().getMaxY() - this.getBounds().getMinY()) < ERROR) {
					return Color.green;
				} else if (Math.abs(entity.getBounds().getMinY() - this.getBounds().getMaxY()) < ERROR){
					return Color.green;
				}
			} else if (entity.getY() >= this.getY()-Tile.SIZE && entity.getY() <= this.getY()+Tile.SIZE) {
				
				if (Math.abs(entity.getBounds().getMinX() - this.getBounds().getMaxX()) < ERROR) {
					return Color.blue;
				} else if (Math.abs(entity.getBounds().getMaxX() - this.getBounds().getMinX()) < ERROR) {
					return Color.blue;
				}
			}
		}
		return Color.red;
	}
	
	protected void launchEntity(Entity entity,MathHelper.Direction direction) {
		entity.setLaunchDirection(direction);
		entity.setMotionDelay(10);
	}
	
	protected boolean touchingEntity(Entity entity) {
		boolean upperLeft = this.contains((int)entity.getBounds().getMinX(),(int) entity.getBounds().getMinY());
		boolean upperRight = this.contains((int)entity.getBounds().getMaxX(),(int)entity.getBounds().getMinY());
		boolean bottomLeft = this.contains((int)entity.getBounds().getMinX(), (int)entity.getBounds().getMaxX());
		boolean bottomRight = this.contains((int)entity.getBounds().getMaxX(),(int)entity.getBounds().getMaxY());
		return upperLeft || upperRight || bottomLeft || bottomRight;
	}
	
	public void setMotionDelay(int delay) {
		this.motionDelay = delay;
	}
	
	public void setMovingUp(boolean up) {
		this.up = up;
	}
	
	public void setMovingDown(boolean down) {
		this.down = down;
	}
	
	public void setMovingLeft(boolean left) {
		this.left = left;
	}
	
	public void setMovingRight(boolean right) {
		this.right = right;
	}
		
	public void setCenterX(int x) {
		super.x = x - super.width/2;
	}

	public void setCenterY(int y) {
		super.y = y - super.height/2;
	}
	
	public void render(Graphics g) {
		
	}
	
	public void setLaunchDirection(MathHelper.Direction direction) {
		this.launchDiretion = direction;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int newHealth) {
		this.health = newHealth;
	}

	public void attack(Entity entity) {
			entity.setHealth(entity.getHealth()-this.attack);
	}

}
