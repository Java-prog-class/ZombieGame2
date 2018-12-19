package me.zombies;

import java.awt.Graphics;
import java.awt.Rectangle;

class Zombie extends Rectangle {
	//Static Variables
	private  int health = 500;
	String type = "medium";	//light, medium, or heavy	
	int vx, vy;	//Speed	

	public Zombie(String type) {	 
		//Set various types
		if (type.equals("light")) this.type = "light";
		if (type.equals("medium")) this.type = "medium";
		if (type.equals("heavy")) this.type = "heavy";;
		width = height = 15;
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
		g.fillOval(x+ZombiesMain.mapX, y+ZombiesMain.mapY, width, height);
	}
	
	int getHealth() {
		return health;
	}
	
}