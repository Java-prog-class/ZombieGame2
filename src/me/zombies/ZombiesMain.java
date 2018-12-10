package me.zombies;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ZombiesMain implements MouseListener, KeyListener{

	public static void main(String[] args) {
		new ZombiesMain();
	}

	static int panW = 800, panH = 500;
	static int round = 1;
	static int mapSpeedX = 0;
	static int mapSpeedY = 0;
	static int mapX = 0, mapY = 0;
	final static int TZ_SPEED = 10;

	JFrame window = new JFrame();
	DrawingPanel drPanel;
	Player player = new Player();	

	private BufferedImage backImg = null;
	ArrayList<Weapon> weapons = new ArrayList<Weapon>();

	ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	ArrayList<Building> buildings = new ArrayList<Building>();



	ZombiesMain(){
		setup();
		spawnEnemies(round*10);
		
		Timer moveTimer = new Timer(TZ_SPEED, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveZombies();
				drPanel.repaint();
				if (Player.HP <= 0) System.exit(0);
			}
		});
		moveTimer.start();
	}


	void setup() {
		window = new JFrame("Zombies");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		drPanel = new DrawingPanel();
		drPanel.addKeyListener(this);
		window.add(drPanel);
		
		Weapon pistol = new Weapon(1);
		weapons.add(pistol);
		Weapon rifle = new Weapon(2);
		weapons.add(rifle);
		Weapon shotgun = new Weapon(3);
		weapons.add(shotgun);

		try
		{
			backImg = ImageIO.read( new File("desert.jpg" ));
		}
		catch ( IOException exc ){}
		gen();

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

	//Adding BUildings to an array
	void gen() {
		for(int i=0;i<5;i++) {
			buildings.add(new Building(player, panW/2, panH/2));
		}
	}

	void movePlayer(String direction) {
		if (direction.equals("up")) {
			//			mapSpeedY = -10;
			mapY -= player.vy;
		}
		if (direction.equals("down")) {
			//			mapSpeedY = 10;
			mapY += player.vy;
		}
		if (direction.equals("right")) {
			//			mapSpeedX = 10;
			mapX += player.vx;
		}
		if (direction.equals("left")) {
			//			mapSpeedX = -10;
			mapX -= player.vx;
		}
		//		System.out.println(mapX + " " + mapY);
	}

	void moveZombies() {
		for (Zombie z: zombies) {
			if (z.type == "light") {
				z.zx += z.vx;
				z.zy += z.vy;
			}
			if (z.type == "medium") {
				z.zx += z.vx;
				z.zy += z.vy;
			}
			if (z.type == "heavy") {
				z.zx += z.vx;
				z.zy += z.vy;
			}


			//Detect if zombie and player are in the same location
			if (z.zx-mapX+z.r/2 >= panW/2-player.r && z.zx-mapX+z.r/2 <= panW/2+player.r
					|| z.zx-mapX-z.r/2 <= panW/2-player.r && z.zx-mapX-z.r/2 >= panW/2+player.r) {

				if (z.zy-mapY+z.r/2 >= panH/2-player.r && z.zy-mapY+z.r/2 <= panH/2+player.r
						|| z.zy-mapY-z.r/2 <= panH/2-player.r && z.zy-mapY-z.r/2 >= panH/2+player.r) {
					player.decreaseHP(100, z);

					//Move zombie away after hitting player
					if (player.x+player.r > z.zx) z.zx -= 10;	//Approach from right
					if (player.x+player.r < z.zx) z.zx += 10;	//Approach from left
					if (player.y < z.zy) z.zy += 10;	//Approach from beneath
					if (player.y > z.zy) z.zy -= 10;	//Approach from above
					break;
				}
			}		
		}
	}
	

	BufferedImage loadImage(String fn) {
		BufferedImage image = null;
		
		InputStream inputStr = ZombiesMain.class.getClassLoader().getResourceAsStream("desert.jpg");
		try
		{
		    image = ImageIO.read(inputStr);
		}
		catch ( IOException exc ){
			
		}
		
		return image;
	}
	
	void moveBuildings(){
		for(Building e : buildings) {
			
		}

	}

	@SuppressWarnings("serial")
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

				for (Zombie z : zombies) {
					z.zx = (int) (Math.random()*panW);
					z.zy = (int) (Math.random()*panH);
				}
				//System.out.println(panW + " " + panH);
				if (panW > 10) screenInit = true;
			}

			super.paintComponent(g); //clear screen and repaint using background color
			Graphics2D g2 = (Graphics2D) g;		

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g.setColor(Color.GREEN.darker());
			g.drawImage(backImg, 0, 0, panW, panH, drPanel);	//background image
			
			//drawPlayer
			g.setColor(Color.BLUE);			
			g.fillOval(player.x-player.r/2, player.y-player.r/2, player.r, player.r);

			

			for(Building bd : buildings) {	
				bd.paint(g);
			}

			g.setColor(Color.BLACK);
			g.drawRect(10, 10, 500, 20);			
			g.setColor(Color.RED);
			g.fillRect(10, 10, Player.HP/2, 20);
			
			for (Zombie z : zombies) {
				if (z.type.equals("light")) g.setColor(Color.RED.brighter());
				if (z.type.equals("medium")) g.setColor(Color.RED);
				if (z.type.equals("heavy")) g.setColor(Color.RED.darker());
				z.paint(g);
			}


		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movePlayer("right");
		}
		if (e.getKeyCode() == KeyEvent.VK_W|| e.getKeyCode() == KeyEvent.VK_UP) {;
		movePlayer("up");
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			movePlayer("left");
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			movePlayer("down");
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mapSpeedX = 0;
		mapSpeedY = 0;
	}
	@Override

	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Weapon.shoot();
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
