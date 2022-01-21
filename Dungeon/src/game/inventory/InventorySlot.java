package game.inventory;

import game.entities.Entity;
import game.items.Item;

public class InventorySlot {
	
	private Item item;
	private int amount;
	private Inventory container;
	/*
	public InventorySlot(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}
	*/
	public InventorySlot() {}
	
	public void setItemAndAmount(Item item, int amount) {
		item.setSlot(this);
		this.item = item;
		this.amount = amount;
	}

	public Item getItem() {
		return this.item;
	}
	
	public void changeAmountBy(int amount) {
		this.amount += amount;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public Inventory getContainer() {
		return this.container;
	}
	
	public void setContainer(Inventory container) {
		this.container = container;
	}
}
