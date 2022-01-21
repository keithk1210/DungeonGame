package game.entities;

import java.awt.Graphics;

import framework.resources.Resources;
import game.world.World;

public class BombEntity extends Entity {
	
	static final long serialVersionUID = 1L;
	private final int explodeTime = 50;
	
	public BombEntity(byte textureID, int posXInRoom, int posYInRoom, int size, World world) {
		super(textureID, posXInRoom, posYInRoom, size,world);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Resources.TEXTURES.get(this.textureID), (int)this.getCenterX(),(int) this.getCenterY(), this.size, this.size, null);
	}
}
