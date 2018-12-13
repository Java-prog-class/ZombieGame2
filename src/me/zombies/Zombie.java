package me.zombies;

import java.awt.Graphics;

class Zombie {
	//Static Variables
	private  int health = 500;
	String type = "medium";	//light, medium, or heavy
	int zx, zy;	//Position;
	int vx, vy;	//Speed	
	int r = 15;	//Radius of drawing
	
	
	Zombie(String type) {	
		//Set various speeds
		if (type == "light") {
			vx = 0;
			vy = 0;
		}
		if (type == "medium") {
			vx = 0;
			vy = 0;
		}
		if (type == "heavy") {
			vx = 0;
			vy = 0;
		}
	}
	
	void decreaseHealth(int n) {
		
		if (type == "light") {
			health -= n/Math.random()*.25;
		}
		if (type == "medium") {
			health -= n/Math.random()*.50;
		}
		if (type == "heavy") {			
			health -= n/Math.random()*.100;
		}
	}
	
	void paint(Graphics g) {
		g.fillOval(zx+ZombiesMain.mapX, zy+ZombiesMain.mapY, width, height);
	}
	
	int getHealth() {
		return health;
	}
	
}