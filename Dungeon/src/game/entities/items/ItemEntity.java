package game.entities.items;

import java.awt.Color;
import java.awt.Graphics;

import game.entities.Entity;
import game.inventory.InventorySlot;

public class ItemEntity extends Entity {
	
	private InventorySlot inventorySlot;
	
	public ItemEntity(byte id, int posXinRoom, int posYinRoom, int _size, InventorySlot inventorySlot) {
		super(id,posXinRoom,posYinRoom,_size);
		this.inventorySlot = inventorySlot;
		this.entityID = inventorySlot.getItem().getId();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(this.x, this.y, this.size, this.size);
	}
	
	public InventorySlot getInventorySlot() {
		return this.inventorySlot;
	}
}
