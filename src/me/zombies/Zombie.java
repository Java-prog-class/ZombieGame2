package me.zombies;

import java.awt.Color;
import java.awt.Graphics;

class Zombie {
	//Static Variables
	private static int health = 500;
	private static String type = "medium";	//light, medium, or heavy
	private static int zx, zy;	//Position;
	private static int vx, vy;	//Speed
	private static int count = 0;
	private static int r = 15;
	
	
	static void createZombie() {
		if (count > ZombiesMain.round*10) return; 
		int screenW = ZombiesMain.panW;
		int screenH = ZombiesMain.panH;
		//Set spawning position
		int x = (int) (Math.random()*screenW);
		int y = (int) (Math.random()*screenH);
		
		//Only spawn within certain radius
		if (x > (screenW/2)+(screenW/4) && x < screenW-(screenW/4)
				|| x < (screenW/3)-(screenW/4) && x > screenW+(screenW/4)) {
			Zombie.zx = x;
		}
		if (y > (screenH/2)+(screenH/4) && x < screenH-(screenH/4)
				|| x < (screenH/3)-(screenH/4) && x > screenH+(screenH/4)) {
			Zombie.zy = y;
		}
		else return;
		//Set various speeds
		if (Zombie.type == "light") {
			Zombie.vx = 8-ZombiesMain.mapSpeed;
			Zombie.vy = 8-ZombiesMain.mapSpeed;
		}
		if (Zombie.type == "medium") {
			Zombie.vx = 5-ZombiesMain.mapSpeed;
			Zombie.vy = 5-ZombiesMain.mapSpeed;
		}
		if (Zombie.type == "heavy") {
			Zombie.vx = 3-ZombiesMain.mapSpeed;
			Zombie.vy = 3-ZombiesMain.mapSpeed;
		}
	}
	
	void decreaseHealth(int n) {
		
		if (Zombie.type == "light") {
			Zombie.health -= n/Math.random()*.25;
		}
		if (Zombie.type == "medium") {
			Zombie.health -= n/Math.random()*.50;
		}
		if (Zombie.type == "heavy") {
			Zombie.health -= n/Math.random()*.100;
		}
	}
	
	void paint(Graphics g) {
		g.setColor(Color.red);
		g.drawOval(zx, zy, r, r);
		count++;
	}
}
