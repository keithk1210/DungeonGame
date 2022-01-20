package game.inventory;

import game.items.Item;

public class InventorySlot {
	
	Item item;
	int amount;
	
	public InventorySlot(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}

	public Item getItem() {
		return this.item;
	}
}
