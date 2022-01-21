package game.inventory;

import game.items.Bomb;
import game.items.Item;

public class InventoryCreator {
	
	public Inventory newEnemyInventory() {
		Inventory inventory = new Inventory();
		Item bomb = new Bomb();
		InventorySlot oneBomb = new InventorySlot();
		oneBomb.setItemAndAmount(bomb, 1);
		inventory.addItem(oneBomb,false);
		return inventory;
	}
	
}
