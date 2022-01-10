import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.lang.Object;
import java.util.AbstractCollection;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	
	static final int SCREEN_WIDTH = 900;
	static final int SCREEN_HEIGHT = 900;
	static final int UNIT_SIZE = 100;
	static final int DELAY = 500;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int MAX_DOORS = 5;
	static final int DOOR_VAL = 1;
	static final int LOCKED_VAL = 2;
	int x = (SCREEN_WIDTH-UNIT_SIZE)/2;
	int y = (SCREEN_HEIGHT-UNIT_SIZE)/2;
	int upperBound = 3;
	int roomWKey;
	Timer timer;
	Random random;
	char direction;
	boolean running = false;
	boolean keyGot = false;
	List<Integer> doorsList = new ArrayList<Integer>();
	List<Integer> coordsList = new ArrayList<Integer>();
	ImmutableList<Integer> doorsPerm = ImmutableList.of(1,1,1,1);
	ImmutableList<Integer> coordsPerm = ImmutableList.of(0,0);
	HashMap<ImmutableList<Integer>,ImmutableList<Integer>> coordsAndDoors = new HashMap<ImmutableList<Integer>,ImmutableList<Integer>>();
	HashMap<ImmutableList<Integer>,Boolean> coordsAndKey = new HashMap<ImmutableList<Integer>,Boolean>();
	
	GamePanel() {
		System.out.println("Started");
		
		if (!running) {
			for (int i = 0; i < 4;i++) {
				doorsList.add(1);
			}	
			for (int i = 0;i<2;i++) {
				coordsList.add(0);
			}
			coordsAndDoors.put(coordsPerm, doorsPerm);
			running = true;
		}
		coordsAndKey.put(coordsPerm, false);
		random = new Random();
		//sets the room that will have a key
		roomWKey = random.nextInt(MAX_DOORS+1);
		while (roomWKey == 1 || roomWKey == 0) {
			roomWKey = random.nextInt(MAX_DOORS+1);
		}
		System.out.println("room w key " + roomWKey);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.white);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		//Timer timer = new Timer(DELAY, this);
		//timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		//draws grid
		for (int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
			g.setColor(Color.black);
			g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
			g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
		}
		
		//draws walls
		for (int i=0; i<SCREEN_WIDTH/UNIT_SIZE; i++) {
			g.fillRect(i * UNIT_SIZE, 0, UNIT_SIZE, UNIT_SIZE);
			g.fillRect(i * UNIT_SIZE, SCREEN_HEIGHT - UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
		}
		for (int i=0; i<SCREEN_HEIGHT/UNIT_SIZE; i++) {
			g.fillRect(0, i * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
			g.fillRect(SCREEN_WIDTH - UNIT_SIZE, i * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
		}
		//draws doors
		//left door
		if (doorsList.get(0) == DOOR_VAL) {
			g.setColor(Color.white);
			g.fillRect(0, (SCREEN_HEIGHT-UNIT_SIZE)/2, UNIT_SIZE, UNIT_SIZE);
		}
		if (doorsList.get(0) == LOCKED_VAL) {
			g.setColor(Color.red);
			g.fillRect(0, (SCREEN_HEIGHT-UNIT_SIZE)/2, UNIT_SIZE, UNIT_SIZE);
		}
		//up door
		if (doorsList.get(1) == DOOR_VAL) {
			g.setColor(Color.white);
			g.fillRect((SCREEN_WIDTH-UNIT_SIZE)/2, 0, UNIT_SIZE, UNIT_SIZE);
		}
		if (doorsList.get(1) == LOCKED_VAL) {
			g.setColor(Color.red);
			g.fillRect((SCREEN_WIDTH-UNIT_SIZE)/2, 0, UNIT_SIZE, UNIT_SIZE);
		}
		//right door
		if (doorsList.get(2) == DOOR_VAL) {
			g.setColor(Color.white);
			g.fillRect(SCREEN_WIDTH - UNIT_SIZE, (SCREEN_HEIGHT-UNIT_SIZE)/2, UNIT_SIZE, UNIT_SIZE);
			
		}
		if (doorsList.get(2) == LOCKED_VAL) {
			g.setColor(Color.red);
			g.fillRect(SCREEN_WIDTH - UNIT_SIZE, (SCREEN_HEIGHT-UNIT_SIZE)/2, UNIT_SIZE, UNIT_SIZE);
			
		}
		//down door
		if (doorsList.get(3) == DOOR_VAL) {
			g.setColor(Color.white);
			g.fillRect((SCREEN_WIDTH-UNIT_SIZE)/2, SCREEN_HEIGHT - UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
		}
		if (doorsList.get(3) == LOCKED_VAL) {
			g.setColor(Color.red);
			g.fillRect((SCREEN_WIDTH-UNIT_SIZE)/2, SCREEN_HEIGHT - UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
		}
		//draws key
		if (coordsAndKey.get(ImmutableList.copyOf(coordsList)) != null && keyGot == false) {
			if(coordsAndKey.get(ImmutableList.copyOf(coordsList))) {
			g.setColor(Color.red);
			g.fillRect((SCREEN_WIDTH-UNIT_SIZE)/2,(SCREEN_HEIGHT-UNIT_SIZE)/2, UNIT_SIZE, UNIT_SIZE);
		}
		}
		//draws player
		g.setColor(Color.blue);
		g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD, 75));
		g.drawString("["+coordsList.get(0)+", "+coordsList.get(1)+"]",25,75);
	}
	public void move() {
		switch(direction) {
		case 'U':
			//if your about to go into a door then run this
			if (y == UNIT_SIZE && x == (SCREEN_WIDTH-UNIT_SIZE)/2 && doorsList.get(1) == DOOR_VAL) {
				y = y - UNIT_SIZE;
				break;
			}
			
			//if you're about to pass the barrier
			if (y == 0) {
				updateCoords("up");
				switchDoor("up");
				switchPlayer("up");
				break;
			}
			//if you're about to get the key
			if (x == (SCREEN_WIDTH-UNIT_SIZE)/2 && y == ((SCREEN_HEIGHT-UNIT_SIZE)/2 + UNIT_SIZE) && coordsAndKey.get(ImmutableList.copyOf(coordsList)) != null) {
					if (coordsAndKey.get(ImmutableList.copyOf(coordsList))) {
					y = y - UNIT_SIZE;
					keyGot = true;
					break;
					}
			}
			if (y == UNIT_SIZE && x == (SCREEN_WIDTH-UNIT_SIZE)/2 && doorsList.get(1) == LOCKED_VAL && keyGot == true) {
				y = y - UNIT_SIZE;
				break;
			}
			//to prevent from moving into a wall, otherwise just allows movement
			if (y != UNIT_SIZE) {
				y = y - UNIT_SIZE;
				break;
			}
			break;
		case 'D':
			//if your about to go into a door then run this
			if (y == SCREEN_HEIGHT - 2 * UNIT_SIZE && x == (SCREEN_WIDTH-UNIT_SIZE)/2 && doorsList.get(3) == DOOR_VAL) {
				y = y + UNIT_SIZE;
				break;
			}
			//if your about to pass the barrier
			if (y == SCREEN_HEIGHT - UNIT_SIZE) {
				updateCoords("down");
				switchDoor("down");
				switchPlayer("down");
				break;
			}
			//if you're about to get the key
			if (x == (SCREEN_WIDTH-UNIT_SIZE)/2 && y == ((SCREEN_HEIGHT-UNIT_SIZE)/2 - UNIT_SIZE) && coordsAndKey.get(ImmutableList.copyOf(coordsList)) != null) {
					if (coordsAndKey.get(ImmutableList.copyOf(coordsList))) {
					y = y + UNIT_SIZE;
					keyGot = true;
					break;
					}
			}
			if (y == SCREEN_HEIGHT - 2 * UNIT_SIZE && x == (SCREEN_WIDTH-UNIT_SIZE)/2 && doorsList.get(3) == LOCKED_VAL && keyGot == true) {
				y = y + UNIT_SIZE;
				break;
			}
			//prevents from going into wall
			if (y != SCREEN_HEIGHT - 2 * UNIT_SIZE) {
				y = y + UNIT_SIZE;
				break;
			}
			break;
		case 'L':
			//if you're about to move INTO a door, this happens to allow you to pass the barrier
			if (y == (SCREEN_HEIGHT-UNIT_SIZE)/2 && x == UNIT_SIZE && doorsList.get(0) == DOOR_VAL) {
				x = x - UNIT_SIZE;
				break;
			}
			//if your about to pass the barrier
			if (x == 0) {
				updateCoords("left");
				switchDoor("left");
				switchPlayer("left");
				break;
			}
			//if you're about to get the key
			if (x == (SCREEN_WIDTH-UNIT_SIZE)/2 + UNIT_SIZE && y == ((SCREEN_HEIGHT-UNIT_SIZE)/2) && coordsAndKey.get(ImmutableList.copyOf(coordsList)) != null) {
				if (coordsAndKey.get(ImmutableList.copyOf(coordsList))) {
					x = x - UNIT_SIZE;
					keyGot = true;
					break;
				}
			}
			if (y == (SCREEN_HEIGHT-UNIT_SIZE)/2 && x == UNIT_SIZE && doorsList.get(0) == LOCKED_VAL && keyGot==true) {
				x = x - UNIT_SIZE;
				break;
			}
			//if you're about to go into a wall
			if (x != UNIT_SIZE && x != 0) {
				x = x - UNIT_SIZE;
				break;
			}
			break;
		case 'R':
			//if your about to go into a door then run this
			if (y == (SCREEN_HEIGHT-UNIT_SIZE)/2 && x == (SCREEN_WIDTH - UNIT_SIZE * 2) && doorsList.get(2) == DOOR_VAL) {
				x = x + UNIT_SIZE;
				break;
			}
			//if your about to pass the barrier
			if (x == SCREEN_WIDTH - UNIT_SIZE) {
				updateCoords("right");
				switchDoor("right");
				switchPlayer("right");
				break;
			}
			//if you're about to get the key
			if (x == (SCREEN_WIDTH-UNIT_SIZE)/2 - UNIT_SIZE && y == ((SCREEN_HEIGHT-UNIT_SIZE)/2) && coordsAndKey.get(ImmutableList.copyOf(coordsList)) != null) {
				if (coordsAndKey.get(ImmutableList.copyOf(coordsList))) {
					x = x + UNIT_SIZE;
					keyGot = true;
					break;
			}
			}
			if (y == (SCREEN_HEIGHT-UNIT_SIZE)/2 && x == (SCREEN_WIDTH - UNIT_SIZE * 2) && doorsList.get(2) == LOCKED_VAL && keyGot == true) {
				x = x + UNIT_SIZE;
				break;
			}
			
			//if you're about to go into a wall
			if (x != SCREEN_WIDTH - 2 * UNIT_SIZE) {
				x = x + UNIT_SIZE;
				break;
			}
			if (x == SCREEN_WIDTH - 2 * UNIT_SIZE) {
				break;
			}
			break;
		}
	}
	//the string parameter is the door that is being entered
	public void switchDoor(String direction) {
		boolean alreadyHasDoors = false;
		
		if(coordsAndDoors.size() == MAX_DOORS) {
			upperBound = 1;
		}
		
		int x2 = coordsList.get(0);
		int y2 = coordsList.get(1);
		//make sure no illegal doors happen
		
		for (ImmutableList<Integer> key : coordsAndDoors.keySet()) {
			if (key.equals(coordsList)) {
				alreadyHasDoors = true;
				for (int i=0;i < 4;i++) {
					doorsList.set(i, coordsAndDoors.get(key).get(i));
				}
			}
		}
		
		if (!alreadyHasDoors) {
			switch (direction) {
				case "left":
					for (int i = 0; i < doorsList.size(); i++) {
						if (i != 2) {
							doorsList.set(i, random.nextInt(upperBound));
						} else {
							doorsList.set(i, 1);
						}
						
					}
					break;
				case "up":
					for (int i = 0; i < doorsList.size(); i++) {
						if (i != 3) {
							doorsList.set(i, random.nextInt(upperBound));
						} else {
							doorsList.set(i, 1);
						}
					}
					break;
				case "right":
					for (int i = 0; i < doorsList.size(); i++) {
						if (i != 0) {
							doorsList.set(i, random.nextInt(upperBound));
						} else {
							doorsList.set(i, 1);
						}
					}
					break;
				case "down":
					for (int i = 0; i < doorsList.size(); i++) {
						if (i != 1) {
							doorsList.set(i, random.nextInt(upperBound));
						} else {
							doorsList.set(i, DOOR_VAL);
						}
					}
					break;
			}
			for (ImmutableList<Integer> key : coordsAndDoors.keySet()) {
				if (key.get(0) == x2-1 && key.get(1) == y2) {
					if (coordsAndDoors.get(key).get(2) != DOOR_VAL && coordsAndDoors.get(key).get(2) != LOCKED_VAL) {
						doorsList.set(0, 0);
					}else {
						if (coordsAndDoors.get(key).get(2) == DOOR_VAL) {
							doorsList.set(0, DOOR_VAL);
					}
						if (coordsAndDoors.get(key).get(2) == LOCKED_VAL) {
							doorsList.set(0, LOCKED_VAL);
						}
					}
				}
				if (key.get(1) == y2+1 && key.get(0) == x2) {
					if (coordsAndDoors.get(key).get(3) != DOOR_VAL && coordsAndDoors.get(key).get(3) != LOCKED_VAL) {
						doorsList.set(1, 0);
					}else {
						if(coordsAndDoors.get(key).get(3) == DOOR_VAL) {
							doorsList.set(1, DOOR_VAL);
						}
						if(coordsAndDoors.get(key).get(3) == LOCKED_VAL) {
							doorsList.set(1, LOCKED_VAL);
						}
					}
			}
				if (key.get(0) == x2+1 && key.get(1) == y2) {
					if (coordsAndDoors.get(key).get(0) != DOOR_VAL && coordsAndDoors.get(key).get(0) != LOCKED_VAL) {
						doorsList.set(2, 0);
					}else {
						if(coordsAndDoors.get(key).get(0) == DOOR_VAL) {
							doorsList.set(2, DOOR_VAL);
						}
						if(coordsAndDoors.get(key).get(0) == LOCKED_VAL) {
							doorsList.set(2, LOCKED_VAL);
						}
					}
			}
				if (key.get(1) == y2-1 && key.get(0) == x2) {
					if (coordsAndDoors.get(key).get(1) != DOOR_VAL && coordsAndDoors.get(key).get(1) != LOCKED_VAL) {
						doorsList.set(3, 0);
					}else {
						if(coordsAndDoors.get(key).get(1) == DOOR_VAL) {
							doorsList.set(3, DOOR_VAL);
						}
						if(coordsAndDoors.get(key).get(1) == LOCKED_VAL) {
							doorsList.set(3, LOCKED_VAL);
						}
					}
			}
				}
			coordsAndDoors.put(ImmutableList.copyOf(coordsList),ImmutableList.copyOf(doorsList));
		}
		System.out.println("coordsanddoors size " + coordsAndDoors.size());
		if (coordsAndDoors.size() == roomWKey && coordsAndKey.get(coordsList) == null && keyGot == false) {
			coordsAndKey.put(ImmutableList.copyOf(coordsList), true);
		} 
		if (coordsAndDoors.size() != roomWKey && coordsAndKey.get(coordsList) == null && keyGot == false) {
			coordsAndKey.put(ImmutableList.copyOf(coordsList), false);
		}
		System.out.println("new");
		for (ImmutableList<Integer> l : coordsAndDoors.keySet()) {
			System.out.println("---");
			System.out.println("keys " + l);
			System.out.println("vals " + coordsAndDoors.get(l));
		}

		}
		
		


	
	public void switchPlayer(String d) {
		switch (d) {
		case "left":
			x = SCREEN_WIDTH - UNIT_SIZE;
			y = (SCREEN_HEIGHT - UNIT_SIZE) / 2;
			break;
		case "up":
			x = (SCREEN_WIDTH-UNIT_SIZE)/2;
			y = SCREEN_HEIGHT-UNIT_SIZE;
			break;
		case "right":
			x = 0;
			y = (SCREEN_HEIGHT - UNIT_SIZE) / 2;
			break;
		case "down":
			x = (SCREEN_WIDTH-UNIT_SIZE)/2;;
			y = 0;
			break;
	}
	}
	
	public void updateCoords(String s) {
				switch(s) {
			case "left":
				coordsList.set(0, coordsList.get(0)-1);
				break;
			case "up":
				coordsList.set(1, coordsList.get(1)+1);
				break;
			case "right":
				coordsList.set(0, coordsList.get(0)+1);
				break;
			case "down":
				coordsList.set(1, coordsList.get(1)-1);
				break;
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
					direction = 'L';
					move();
					repaint();
				break;
			case KeyEvent.VK_RIGHT:
					direction = 'R';
					move();
					repaint();
				break;
			case KeyEvent.VK_UP:
					direction = 'U';
					move();
					repaint();
				break;
			case KeyEvent.VK_DOWN:
					direction = 'D';
					move();
					repaint();
				break;
			
			}
			}
		
	}
}
