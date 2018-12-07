package me.zombies;

import java.awt.Color;
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
		if (type.equals("light")) {
			vx = 0;
			vy = 0;
		}
		if (type.equals("medium")) {
			vx = 0;
			vy = 0;
		}
		if (type.equals("heavy")) {
			vx = 0;
			vy = 0;
		}
	}
	
	void decreaseHealth(int n) {
		
		if (type.equals("light")) {
			health -= n/Math.random()*.25;
		}
		if (type.equals("medium")) {
			health -= n/Math.random()*.50;
		}
		if (type.equals("heavy") ) {			
			health -= n/Math.random()*.100;
		}
	}
	
	void paint(Graphics g) {
		if (type.equals("light")) g.setColor(Color.BLACK);
		if (type.equals("medium")) g.setColor(Color.RED);
		if (type.equals("heavy")) g.setColor(Color.GREEN);
		g.fillOval(zx-ZombiesMain.mapX, zy-ZombiesMain.mapY, r, r);
	}
	
	int getHealth() {
		return health;
	}
	
}
