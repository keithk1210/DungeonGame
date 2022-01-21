package game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.resources.Resources;
import framework.utils.MathHelper;
import framework.utils.MathHelper.Direction;
import game.inventory.Inventory;
import game.world.Tile;
import game.world.World;

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
	protected int size;
	private final byte initialID;
	protected int invulTime;
	private static final byte[] frames = new byte[] {0,1,0,2};
	protected World world;
	protected int speed;
	protected int health;
	protected int attack;
	protected int posXInWorld;
	protected int posYInWorld;
	protected Inventory inventory;
	
	protected MathHelper.Direction facing;
	protected MathHelper.Direction launchDiretion;
	
	protected byte textureID;
	
	private static final int ERROR = 15;
	
	public Entity(byte textureID, int posXinRoom, int posYinRoom, int _size, World world) {
		super(posXinRoom, posYinRoom, _size, _size);
		this.world = world;
		this.size = _size;
		this.textureID = textureID;
		this.initialID = this.textureID;
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;
		this.speed = 4;
		this.motionDelay = 0;
		this.facing = Direction.SOUTH;
	}
	
	public void move() {
		if (this.motionDelay == 0) {
			this.updateSprite();
			if(up) {
				this.setCenterY((int)this.getCenterY()-speed);
				this.facing = Direction.NORTH;
			}
			if(down) {
				this.setCenterY((int)this.getCenterY()+speed);
				this.facing = Direction.SOUTH;
			}
			if(left) {
				this.setCenterX((int)this.getCenterX()-speed);
				this.facing = Direction.WEST;
			}
			if(right) {
				this.setCenterX((int)this.getCenterX()+speed);
				this.facing = Direction.EAST;
			}
		} else {
			this.motionDelay--;
			this.setCenterX((int)(this.getCenterX() + (this.launchDiretion.dirX * 10)));
			this.setCenterY((int)(this.getCenterY() + (this.launchDiretion.dirY * 10)));
		}
	}
	
	public void updateFacing(MathHelper.Direction direction) {
		this.facing = direction;
	}
	
	public void updateFacing() {}
	
	private void updateWorldAndRoomPosition() {
		if (this.x < 0) {
			this.x = Resources.SCREEN_WIDTH - Tile.SIZE;
			posXInWorld--;
		} else if (this.x >= Resources.SCREEN_WIDTH) {
			this.x = 0;
			posXInWorld++;
		} else if (this.y < 0) {
			this.y = Resources.SCREEN_HEIGHT - Tile.SIZE;
			posYInWorld--;
		} else if (this.y >= Resources.SCREEN_HEIGHT) {
			this.y = 0;
			posYInWorld++;
		}
	}
	
	public int getWorldX() {
		return posXInWorld;
	}
	public int getWorldY() {
		return posYInWorld;
	}
	
	protected void updateSprite() {
		switch (this.facing) {
			case NORTH:
				this.textureID = (byte)(this.initialID + 9);
				break;
			case SOUTH:
				this.textureID = (this.initialID);
				break;
			case EAST:
				this.textureID = (byte)(this.initialID+6);
				break;
			case WEST:
				this.textureID = (byte)(this.initialID+3);
				break;
		}
	}
	
	
	public void render(Graphics g) {
		if (up || down || left || right) {
			animationDelay++;
			if (animationDelay == 100) {
				animationFrame++;
				animationDelay = 0;
				if (animationFrame > frames.length - 1) {
					animationFrame = 0;
				}
			}
		}
		g.drawImage(Resources.TEXTURES.get(this.textureID + frames[animationFrame]), this.x, this.y, this.size, this.size, null);
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
	
	
	public void setLaunchDirection(MathHelper.Direction direction) {
		this.launchDiretion = direction;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int newHealth) {
		this.health = newHealth;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public World getWorld() {
		return world;
	}
	
	public void decrementInvulTime() {
		if (this.invulTime != 0) {
			this.invulTime = this.invulTime - 1;
		}
	}

	public void attack(Entity entity) {
		if (entity.invulTime == 0) {
			entity.setHealth(entity.getHealth()-this.attack);
			entity.invulTime = 5;
		}
		
	}
	
	public void useItem() {
	}
	
	

	
}
