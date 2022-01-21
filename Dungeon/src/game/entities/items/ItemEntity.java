package game.entities.items;

import java.awt.Graphics;

import framework.resources.Resources;
import game.entities.Entity;
import game.inventory.InventorySlot;
import game.world.Tile;
import game.world.World;

public class ItemEntity extends Entity {
	
	private InventorySlot inventorySlot;
	
	public ItemEntity(byte id, int posXinRoom, int posYinRoom, int _size, InventorySlot inventorySlot, World world) {
		super(id,posXinRoom,posYinRoom,_size,world);
		this.inventorySlot = inventorySlot;
		System.out.println(this.inventorySlot.getItem().getItemID());
		this.textureID = inventorySlot.getItem().getTextureID();
	}
	
	public void render(Graphics g) {
		g.drawImage(Resources.TEXTURES.get(this.textureID),(int) this.getCenterX(), (int)this.getCenterY(), Tile.SIZE/2, Tile.SIZE/2, null);
	}
	
	public InventorySlot getInventorySlot() {
		return this.inventorySlot;
	}
}
