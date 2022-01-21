package game.entities;

import java.awt.event.MouseEvent;

import framework.gui.GUI;
import framework.resources.Resources;
import framework.resources.TextureID;
import framework.utils.MathHelper.Direction;
import framework.utils.Structs.Coord;
import game.inventory.Inventory;
import game.inventory.InventorySlot;
import game.items.Item;
import game.weapons.Gun;
import game.world.Tile;
import game.world.World;

public class Player extends Entity {
	
	private static final long serialVersionUID = 1L;
	private Gun gun;
	private Inventory inventory;
	private World world;
	
	public Player(World world, Inventory inventory) {
		super(TextureID.CONJ_BLUE_FRONT, Resources.MIDDLE_X, Resources.MIDDLE_Y, Tile.SIZE,world);
		this.world = world;
		gun = new Gun(this);
		//arrowKeys = new Key[] {new Key(false,KeyEvent.VK_UP), new Key(false,KeyEvent.VK_RIGHT),
		speed = 10;
		this.health = 20;
		this.animationFrame = 0;
		this.animationDelay = 0;
		this.facing = Direction.SOUTH;
		this.inventory = inventory;
		inventory.setOwner(this);
		if (world.getSize() != Resources.DEBUG_WORLD_SIZE) {
			posXInWorld = (world.getSize()-1)/2;
			posYInWorld = (world.getSize()-1)/2;
			
		} else {
			posXInWorld = 0;
			posYInWorld = 0;
		}
		GUI.setDiscovered(posXInWorld, posYInWorld);
		addCoordForGUI();
	}
	
	@Override
	public void move() {
		super.move();
		updateWorldAndRoomPosition();
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	private void updateWorldAndRoomPosition() {
		if (this.x < 0) {
			this.x = Resources.SCREEN_WIDTH - Tile.SIZE;
			posXInWorld--;
			GUI.setDiscovered(posXInWorld, posYInWorld);
			addCoordForGUI();
		} else if (this.x >= Resources.SCREEN_WIDTH) {
			this.x = 0;
			posXInWorld++;
			GUI.setDiscovered(posXInWorld, posYInWorld);
			addCoordForGUI();
		} else if (this.y < 0) {
			this.y = Resources.SCREEN_HEIGHT - Tile.SIZE;
			posYInWorld--;
			GUI.setDiscovered(posXInWorld, posYInWorld);
			addCoordForGUI();
		} else if (this.y >= Resources.SCREEN_HEIGHT) {
			this.y = 0;
			posYInWorld++;
			GUI.setDiscovered(posXInWorld, posYInWorld);
			addCoordForGUI();
		}
	}
	
	private void addCoordForGUI() {
		GUI.addNewCoord(new Coord(Resources.SCREEN_WIDTH-Tile.SIZE + ((getWorldX()) * Resources.MAP_UNIT_SIZE) + Resources.MAP_UNIT_SIZE/2, ((getWorldY()) * Resources.MAP_UNIT_SIZE) + Resources.MAP_UNIT_SIZE/2));
	}
	
	
	
	public Gun getGun() {
		return gun;
	}
	
	
	
	public void pickUpItem(InventorySlot inventorySlot) {
		this.inventory.addItem(inventorySlot,true);
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	@Override
	public void useItem() {
		if (this.inventory.getSlotAt(this.inventory.getSelectedSlot().getX(),this.getInventory().getSelectedSlot().getY()) != null) {
			Item selectedItem = this.inventory.getSlotAt(this.inventory.getSelectedSlot().getX(),this.inventory.getSelectedSlot().getY()).getItem();
			selectedItem.use();
			if (selectedItem.isConsumable()) {
				this.inventory.getSlotAt(this.inventory.getSelectedSlot().getX(), this.inventory.getSelectedSlot().getY()).changeAmountBy(-1);
			}
			
		}
	}
	
}


