package game.inventory;

import java.util.HashSet;

import game.entities.Entity;

public class Inventory {
	
	private HashSet<InventorySlot> contents;
	
	
	public Inventory(InventorySlot... contents) {
		this.contents = new HashSet<InventorySlot>();
		for (InventorySlot slot : contents) {
			this.contents.add(slot);
		}
	}
	
	public Inventory(Entity owner) {
		
	}
	
	public HashSet<InventorySlot> getContents() {
		return this.contents;
	}
	
	public void addItem(InventorySlot inventorySlot) {
		this.contents.add(inventorySlot);
	}

}
