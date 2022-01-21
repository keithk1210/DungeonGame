package game.items;

import framework.resources.ItemID;
import framework.resources.TextureID;
import game.entities.BombEntity;
import game.entities.Entity;
import game.inventory.Inventory;
import game.world.Tile;

public class Bomb extends Item {
	
	public Bomb() {
		this.textureID = TextureID.BOMB;
		this.itemID = ItemID.BOMB;
		this.consumable = true;
	}
	
	@Override
	public void use() {
		Entity owner = this.slot.getContainer().getOwner();
		owner.getWorld().getRoomAt(owner.getWorldX(), owner.getWorldY()).spawnEntity(new BombEntity(TextureID.BOMB,(int)owner.getCenterX(),(int)owner.getCenterY(),Tile.SIZE,owner.getWorld()));
	}
}
