package me.zombies;

import java.awt.Color;
import java.awt.Graphics;

class Zombie {
	//Static Variables
	private  int health = 500;
	String type = "medium";	//light, medium, or heavy
	private int zx, zy;	//Position;
	private int vx, vy;	//Speed	
	private int r = 15;	//Radius of drawing
	
	private static int count = 0;
	
	Zombie(String type) {
		
		if (count > ZombiesMain.round*10) return; //Will be moved to ZomibiesMain if they are spawned from there
		int screenW = ZombiesMain.panW;
		int screenH = ZombiesMain.panH;
		//Set spawning position
		int x = (int) (Math.random()*screenW);
		int y = (int) (Math.random()*screenH);
		
		//Only spawn within certain radius
		if (x > (screenW/2)+(screenW/4) && x < screenW-(screenW/4)
				|| x < (screenW/3)-(screenW/4) && x > screenW+(screenW/4)) {
			zx = x;
		} else return;
		if (y > (screenH/2)+(screenH/4) && x < screenH-(screenH/4)
				|| x < (screenH/3)-(screenH/4) && x > screenH+(screenH/4)) {
			zy = y;
		} else return;
		
		//Set various speeds
		if (type == "light") {
			this.type = type;
			vx = 8-ZombiesMain.mapSpeed;
			vy = 8-ZombiesMain.mapSpeed;
		}
		if (type == "medium") {
			vx = 5-ZombiesMain.mapSpeed;
			vy = 5-ZombiesMain.mapSpeed;
		}
		if (type == "heavy") {
			vx = 3-ZombiesMain.mapSpeed;
			vy = 3-ZombiesMain.mapSpeed;
		}
	}
	
	void decreaseHealth(int n) {
		
		if (this.type == "light") {
			this.health -= n/Math.random()*.25;
		}
		if (type == "medium") {
			health -= n/Math.random()*.50;
		}
		if (type == "heavy") {			
			this.health -= n/Math.random()*.100;
		}
	}
	
	void paint(Graphics g) {
		g.setColor(Color.red);
		g.drawOval(zx, zy, r, r);
		count++;
	}
	
	int getHealth() {
		return health;
	}
	
}
