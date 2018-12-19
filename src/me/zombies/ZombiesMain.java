package me.zombies;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class ZombiesMain implements MouseListener, KeyListener{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ZombiesMain();
			}
		});		
	}

	static int panW = 800, panH = 500;
	static int round = 1;
	static int mapX = 0, mapY = 0;
	final static int TZ_SPEED = 10;
	final static int POWERUP_SPEED = 100;
	boolean invincible = false;
	
	double invincibleSeconds = 0;
	double damageSeconds = 0;
	double speedSeconds = 0;

	JFrame window = new JFrame();
	DrawingPanel drPanel;
	Player player = new Player();
	JLabel lblWeapon = new JLabel();
	JLabel lblAmmo = new JLabel();
	String name = "";

	private BufferedImage backImg = null;
	
	ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	ArrayList<Building> buildings = new ArrayList<Building>();
	ArrayList<Powerup> powerups = new ArrayList<Powerup>();
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	long t1 = System.currentTimeMillis();
	
	ZombiesMain(){
		setup();
		spawnEnemies(round*10);
		spawnPowerups(5);
		Timer moveTimer = new Timer(TZ_SPEED, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long t2 = System.currentTimeMillis();
				//if (t2-t1 < 15) continue;
				//System.out.println(""+(t2-t1));
				t1=t2;
				moveZombies();
				moveBullets();
				drPanel.repaint();
				if (Player.HP <= 0) System.exit(0);
			}
		});
		moveTimer.start();

		Timer powerTimer = new Timer(POWERUP_SPEED, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				invincibleSeconds += POWERUP_SPEED/1000;
				damageSeconds += POWERUP_SPEED/1000;
				speedSeconds += POWERUP_SPEED/1000;
				
				usePowerups();
				drPanel.repaint();
				
				if (invincibleSeconds == 10.0) invincible = false;
				//if (damageSeconds == 10.0)
				if (speedSeconds == 10.0) player.vx = player.vy -= 5;
				
			}
		});
		powerTimer.start();
	}

	void setup() {
		window = new JFrame("Zombies");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		drPanel = new DrawingPanel();
		drPanel.addKeyListener(this);
		drPanel.addMouseListener(this);
		window.add(drPanel);
		
		weapons.add(new Weapon(0));
		weapons.add(new Weapon(1));
		weapons.add(new Weapon(2));	
		lblWeapon.setText(weapons.get(0).name);	
		drPanel.add(lblWeapon);
		
		lblAmmo = new JLabel("AMMO: "+ weapons.get(0).getAmmo());
		drPanel.add(lblAmmo);


		backImg = loadImage("desert.jpg");
		genBuildings();

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

	void spawnPowerups(int n) {
		for (int i = 0; i < n; i++) {
			Powerup p = new Powerup(i);
			powerups.add(p);
		}
	}

	//Adding Buildings to an array
	void genBuildings() {
		for(int i=0;i<75;i++) {
			buildings.add(new Building(player,panW,panH));			
		}		
	}

	void resetBuildingLocation() {
		//for (Building bd: buildings) {
		for (int i=0; i<buildings.size(); i++) {
			Building bd = buildings.get(i);
			//System.out.println(bd.toString());

			if(bd.intersects(player)){
				//				System.out.print("*** ");
				bd.x = bd.x + bd.width + 50;
				//				bd.colour = Color.YELLOW;
				//System.out.println(bd.toString());
			}
		}

	}
	
	void movePlayer(String direction) {
		if (direction.equals("up")) {
			player.y -= player.vy;
			mapY += player.vy;
		}
		if (direction.equals("down")) {
			player.y += player.vy;
			mapY -= player.vy;
		}
		if (direction.equals("right")) {
			player.x += player.vx;
			mapX -= player.vx;
		}
		if (direction.equals("left")) {
			player.x -= player.vx;
			mapX += player.vx;
		}
				
		for(Building bd : buildings) {	
			if(bd.intersects(player)) {
				if(direction.equals("right")) {
					mapX += player.vx;
					player.x -= player.vx;
				}else if(direction.equals("left")) {
					mapX -= player.vx;
					player.x += player.vx;
				}else if(direction.equals("up")) {
					mapY -= player.vy;
					player.y += player.vy;
				}else if(direction.equals("down")) {
					mapY += player.vy;
					player.y -= player.vy;
				}
			}
		}	
	}

	void moveZombies() {
		for (Zombie z: zombies) {
			if (z.x+mapX < panW/2) z.vx = 1;
			if (z.x+mapX > panW/2) z.vx = -1;
			if (z.y+mapY < panH/2) z.vy = 1;
			if (z.y+mapY > panH/2) z.vy = -1;		


			if (z.type == "light") {
				z.x += z.vx*3;
				z.y += z.vy*3;
			}
			if (z.type == "medium") {
				z.x += z.vx*2;
				z.y += z.vy*2;
			}
			if (z.type == "heavy") {
				z.x += z.vx;
				z.y += z.vy;
			}
		
			//Detect if zombie and player are in the same location
			if (z.intersects(player)) {
				if (!invincible) player.decreaseHP(100, z);
				//Move zombie away after hitting player
				if (player.x+player.width > z.x) z.x -= 30;	//Approach from right
				if (player.x-player.width < z.x) z.x += 30;	//Approach from left
				if (player.y-player.height < z.y) z.y += 30;	//Approach from beneath
				if (player.y+player.height > z.y) z.y -= 30;	//Approach from above
				break;
			}
		}
		//checking for intersection between buildings and zombies
		for(Building bd : buildings) {
			for(Zombie z : zombies) {
				if(z.intersects(bd)) {
					if(bd.x > z.x && bd.width < z.x)z.x = z.x-10;//left of building
					if(bd.x < z.x && bd.width < z.x)z.x = z.x+10;//right of building
					if(bd.y < z.y && bd.height < z.height)z.y = z.y - 10;// of building
					System.out.println("The Zombie boi hit me");
				}
			}
		}
	}

	void usePowerups() {
		for (Powerup p : powerups) {
			if (p.intersects(player)) {
				if (p.type.equals("IncreaseHealth")) {
					player.HP += 200;
					if (player.HP > 1000) player.HP = 1000;
				}
				if (p.type.equals("Invincible")) {
					invincible = true;
					invincibleSeconds = 0;
				}
				if (p.type.equals("IncreaseDamage")) {
					damageSeconds = 0;
				}
				if (p.type.equals("IncreaseSpeed")) {
					player.vx += 5;
					player.vy += 5;
					speedSeconds = 0;
				}
				if (p.type.equals("RestoreHealth")) {
					player.HP = 1000;
				}
				powerups.remove(p);
				break;
			}
		}
	}

	void moveBullets() {
		for(Bullet b : bullets) {
			b.x+=b.vx;
			b.y+=b.vy;
		}
	}

	void killZombies() {
		for (Bullet b: bullets) {
			for(Zombie z: zombies) {
				if (z.intersects(b)) {
					z.decreaseHealth(weapons.get(player.currentWeapon).getDamage());
					bullets.remove(b);
					System.out.println("yeet");
				}
				
				break;				
			}
				break;		
		}	
		
	}
	
	BufferedImage loadImage(String fn) {
		BufferedImage image = null;		
		InputStream inputStr = ZombiesMain.class.getClassLoader().getResourceAsStream(fn);
		try {
			image = ImageIO.read(inputStr);
		} catch ( IOException exc ){
			System.out.println("image not found");
		}

		return image;
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
			panH = this.getHeight();			

			//***********************************************************************
			//only do this the very first time that the screen is painted
			if (!screenInit) {	
				player.x=panW/2;				
				player.y=panH/2;
				resetBuildingLocation();

				for (Zombie z : zombies) {
					int testX = (int) (Math.random()*panW);
					int testY = (int) (Math.random()*panH);
					if (z.intersects(player)) {
						return;	
					}
					else {
						z.x = testX;
						z.y = testY;
					}
				}
				for (Powerup p : powerups) {
					int testX = (int) (Math.random()*panW);
					int testY = (int) (Math.random()*panH);
					if (p.intersects(player)) {
						return;	
					}
					else {
						p.x = testX;
						p.y = testY;
					}
				}
				//System.out.println(panW + " " + panH);
				if (panW > 10) screenInit = true;
			}
			//***********************************************************************

			super.paintComponent(g); //clear screen and repaint using background color
			Graphics2D g2 = (Graphics2D) g;		

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g.setColor(Color.GREEN.darker());
			g.drawImage(backImg, 0, 0, panW, panH, drPanel);	//background image

			for(Building bd : buildings) {	
				bd.paint(g);				
			}
			//drawPlayer
			g.setColor(Color.BLUE);			
			g.fillOval(player.x +mapX, player.y+mapY, player.width, player.height);

			g.setColor(Color.BLACK);
			g.drawRect(10, 10, 500, 20);			
			g.setColor(Color.RED);
			g.fillRect(10, 10, Player.HP/2, 20);

			for (Zombie z : zombies) {	
				if (z.type.equals("light")) g.setColor(Color.RED.brighter());
				if (z.type.equals("medium")) g.setColor(Color.RED.darker());
				if (z.type.equals("heavy")) g.setColor(Color.RED.darker().darker());
				z.paint(g);
			}
			for (Powerup p : powerups) {	
				if (p.type.equals("RestoreHealth")) g.setColor(Color.YELLOW);
				if (p.type.equals("IncreaseHealth")) g.setColor(Color.PINK);
				if (p.type.equals("Invincible")) g.setColor(Color.CYAN);
				if (p.type.equals("IncreaseSpeed")) g.setColor(Color.white);
				if (p.type.equals("IncreaseDamage")) g.setColor(Color.black);
				p.paint(g);
			}
			
			for (Bullet b:bullets) {
				b.paint(g);
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
		if (e.getKeyCode() == KeyEvent.VK_W|| e.getKeyCode() == KeyEvent.VK_UP) {
			movePlayer("up");
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			movePlayer("left");
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			movePlayer("down");
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			weapons.get(player.currentWeapon).reload();
			lblAmmo.setText("AMMO: "+ weapons.get(player.currentWeapon).getAmmo());		
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)){
			player.currentWeapon++;
			if (player.currentWeapon>2) player.currentWeapon=0;
			lblWeapon.setText(weapons.get(player.currentWeapon).name);
		}
		else {
			int mx = e.getX();
			int my = e.getY();
			int w = player.currentWeapon;
			if (weapons.get(w).getAmmo() == 0) weapons.get(w).ammo = 0;
			else bullets.add(weapons.get(w).shoot(mx,my,player.currentWeapon, panW/2, panH/2));
			
		}
		lblAmmo.setText("AMMO: "+ weapons.get(player.currentWeapon).getAmmo());		
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