package game.inventory;

import game.items.Bomb;

public class InventoryCreator {
	
	public static Inventory newEnemyInventory() {
		return new Inventory(new InventorySlot(new Bomb(), 1));
	}
	
}
