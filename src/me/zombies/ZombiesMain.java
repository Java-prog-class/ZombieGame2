package me.zombies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ZombiesMain implements MouseListener, KeyListener{

	public static void main(String[] args) {
		new ZombiesMain();
	}
	
	static int panW = 800, panH = 500;
	static int round = 1;
	static int mapSpeed = 0;
	
	JFrame window = new JFrame();
	DrawingPanel drPanel;
	static int panW = 800, panH = 500;
	static int round = 1;
	Player player = new Player();
	static int mapSpeed = 0;
	Zombie ztest;
	Player player = new Player();	
	private BufferedImage backImg = null;
	Weapon[] weapons = new Weapon[3]; 
	ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	
	
	ZombiesMain(){
		setup();
		spawnEnemies(round*10);
	}


	void setup() {
		window = new JFrame("Zombies");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		drPanel = new DrawingPanel();
		drPanel.addKeyListener(this);
		window.add(drPanel);
		weapons[0] = new Weapon(Weapon.PISTOL);
		weapons[1] = new Weapon(Weapon.RIFLE);
		weapons[2] = new Weapon(Weapon.SHOTGUN);
		
		player.currentWeapon = weapons[0];
		
		try
		{
		    backImg = ImageIO.read( new File("desert.jpg" ));
		}
		catch ( IOException exc ){}
		
		window.setVisible(true);
		drPanel.requestFocus(); //do we only have to do this once?
		
		drPanel.repaint();
	}
	
	void spawnEnemies(int n) {
		for (int i = 0; i < n; i++) {
			int rnd = (int) (Math.random()*3)+1;
			Zombie z = null;
			if (rnd == 1) z = new Zombie("light"); 
			if (rnd == 2) z = new Zombie("medium"); 
			if (rnd == 3) z = new Zombie("heavy"); 
			zombies.add(z);
		}
	}
	
	void movePlayer(String direction) {
		if (direction.equals("up")) {
			mapSpeed = 10;
		}
	}


	private class DrawingPanel extends JPanel {
		boolean screenInit = false;
		DrawingPanel() {
			this.setBackground(Color.WHITE);	
		}

		@Override
		public void paintComponent(Graphics g) {
			//drPanel.requestFocus();
			panH = this.getHeight();
			panW = this.getWidth();
			if (!screenInit) {	//only do this the very first time that the screen is painted
				player.x=panW/2;				
				player.y=panH/2;
				
				//System.out.println(panW + " " + panH);
				if (panW > 10) screenInit = true;
			}
			super.paintComponent(g); //clear screen and repaint using background color
			Graphics2D g2 = (Graphics2D) g;		
			
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g.setColor(Color.GREEN.darker());
			g.drawImage(backImg, 100, 100, 100, 100, drPanel);	//background image
			g.setColor(Color.BLUE);
			g.fillOval(player.x-player.r/2, player.y-player.r/2, player.r, player.r);
			for (Zombie z : zombies) {
				if (z.type.equals("light")) g.setColor(Color.RED.brighter());
				if (z.type.equals("medium")) g.setColor(Color.RED);
				if (z.type.equals("heavy")) g.setColor(Color.RED.darker());
				z.paint(g);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("right");
			movePlayer("right");
		}
		if (e.getKeyCode() == KeyEvent.VK_W|| e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("up");
			movePlayer("up");
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("left");
			movePlayer("left");
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("down");
			movePlayer("down");
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			movePlayer("Swap");
			int weaponnum = player.currentWeapon.type;
			weaponnum++;
			if(weaponnum > weapons.length) {
				weaponnum = 0;
			}
			player.currentWeapon = weapons[weaponnum];
			System.out.println(player.currentWeapon.name);
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
