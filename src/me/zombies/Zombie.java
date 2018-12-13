package me.zombies;

import java.awt.Graphics;
import java.awt.Rectangle;

@SuppressWarnings("serial")
class Zombie extends Rectangle {
	//Variables
	private  int health = 500;
	String type = "medium";	//light, medium, or heavy
	int zx, zy;	//Position;
	int vx, vy;	//Speed	
	int width = height = 15;
	//int r = 15;	//Radius of drawing
		
	public Zombie(String type) {	 
		//Set various types
		if (type.equals("light")) this.type = "light";
		if (type.equals("medium")) this.type = "medium";
		if (type.equals("heavy")) this.type = "heavy";;
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
		g.fillOval(zx+ZombiesMain.mapX, zy+ZombiesMain.mapY, width, height);
	}
	
	int getHealth() {
		return health;
	}
	
}