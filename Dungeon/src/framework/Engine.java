package framework;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import framework.gamestates.GameStateManager;
import framework.gui.WindowManager;
import game.states.MainMenu;

public class Engine {
	
private static GameStateManager gameStateManager;
	
	private static WindowManager windowManager;
	private static Timer timer;
	
	public static void init() {
		gameStateManager = new GameStateManager();
		windowManager = new WindowManager();
		timer = new Timer(20, new MainGameLoop());
	}
	
	public static void start() {
		gameStateManager.stackState(new MainMenu(gameStateManager));
		windowManager.addPanel(new GameScreen());
		windowManager.addKeyListener(new Keyboard());
		windowManager.addMouseListener(new ML());
		windowManager.addMouseMotionListener(new MML());
		windowManager.createWindow();
		timer.start();
	}
	
	private static class MainGameLoop implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			gameStateManager.loop();
		}
		
	}
	
	private static class GameScreen extends JPanel {
			
			private static final long serialVersionUID = 1L;
	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				gameStateManager.render(g);
				repaint();
			}
		}
	
	private static class Keyboard implements KeyListener {

		@Override
		public void keyPressed(KeyEvent key) {
			gameStateManager.keyPressed(key.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent key) {
			gameStateManager.keyReleased(key.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
	}
	
	private static class MML implements MouseMotionListener {
		
	
		@Override
		public void mouseMoved(MouseEvent e) {
			gameStateManager.mouseMoved(e);
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
		}
		
	}
	
	private static class ML implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			gameStateManager.mouseClicked(e);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
	}
	
	
	

}
