package game.inventory;

import framework.resources.Resources;
import framework.utils.Structs.Coord;
import game.entities.Entity;
import game.entities.Player;

public class Inventory {
	
	private InventorySlot[][] contents;
	private Entity owner;
	private Coord currentSlot;
	private Coord selectedSlot;
	
	
	public Inventory(InventorySlot... contents) {
		this.contents = new InventorySlot[Resources.HEIGHT_IN_TILES-1][Resources.WIDTH_IN_TILES];
		currentSlot = new Coord(0,0);
		this.selectedSlot = new Coord(0,0);
		for (InventorySlot slot : contents) {
			this.addItem(slot,false);
		}
	}

	public InventorySlot[][] getContents() {
		return this.contents;
	}
	
	public InventorySlot getSlotAt(int x, int y) {
		System.out.println(this.contents[y][x]);
		return this.contents[y][x];
	}
	
	public Entity getOwner() {
		return this.owner;
	}
	
	public void setOwner(Entity owner) {
		this.owner = owner;
	}
	
	public Coord getSelectedSlot() {
		return this.selectedSlot;
	}
	
	public void setSelectedSlotX(int x) {
		this.selectedSlot.setX(x);
	}
	public void setSelectedSlotY(int y) {
		this.selectedSlot.setY(y);
	}
	
	public void addItem(InventorySlot newItemSlot, boolean print) {
		for (int y = 0; y < contents.length; y++) {
			for (int x = 0; x < contents[y].length; x++) {
				if (contents[y][x] != null) {
					//System.out.println()
					if (contents[y][x].getItem().getItemID() == newItemSlot.getItem().getItemID()) {
						contents[y][x].changeAmountBy(newItemSlot.getAmount());
						if (print) {
							System.out.println("DUPLICATE ITEM!!!");
							for (int j = 0; j < contents.length; j++) {
								for (int i = 0; i < contents[j].length; i++) {
									System.out.print(contents[j][i]);
									}
								System.out.println();
								}
							}
						return;
					}
				}
			}
		}
		newItemSlot.setContainer(this);
		if (this.owner instanceof Player) {
			System.out.println("in add item x: " +  currentSlot.getX() + " y: " + currentSlot.getX());
		}
		contents[currentSlot.getY()][currentSlot.getX()] = newItemSlot;
		currentSlot.setX(currentSlot.getX()+1);
		if (currentSlot.getX() == Resources.WIDTH_IN_TILES-1) {
			currentSlot.setY(currentSlot.getY()+1);
			currentSlot.setX(0);
		}
		if (print) {
			System.out.println("NEW");
			System.out.println(this);
			for (int y = 0; y < contents.length; y++) {
				for (int x = 0; x < contents[y].length; x++) {
					System.out.print(contents[y][x]);
					}
				System.out.println();
				}
			}
	}

}
