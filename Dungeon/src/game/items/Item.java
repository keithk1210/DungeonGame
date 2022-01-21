package game.items;

import game.inventory.InventorySlot;

public abstract class Item {
	
	protected byte textureID;
	protected byte itemID;
	protected InventorySlot slot;
	protected boolean consumable;
	
	public Item() {
	}
	
	public byte getTextureID() {
		return this.textureID;
	}
	public byte getItemID() {
		return this.itemID;
	}
	
	public InventorySlot getSlot() {
		return this.slot;
	}
	public void setSlot(InventorySlot slot) {
		this.slot = slot;
	}
	public boolean isConsumable() {
		return consumable;
	}
	
	public abstract void use();
}
