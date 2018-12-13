package me.zombies;

import java.awt.Graphics;
import java.awt.Rectangle;

@SuppressWarnings("serial")
class Powerup extends Rectangle {
	//Variables
	String type;
	int px, py; //Position
	int width = height = 10;
	
	public Powerup(int type) {
		if (type == 0) this.type = "IncreaseHealth"; //Restore some health
		if (type == 1) this.type = "Invincible";
		if (type == 2) this.type = "IncreaseDamage";
		if (type == 3) this.type = "IncreaseSpeed";
		if (type == 4) this.type = "RestoreHealth";	//Restore all health
	}
	
	void paint(Graphics g) {
		g.fillRect(px-ZombiesMain.mapX, py-ZombiesMain.mapY, width, height);
	}
	
	String getType() {
		return type;
	}
}
