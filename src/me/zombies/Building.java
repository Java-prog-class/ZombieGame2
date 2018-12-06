package me.zombies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class Building extends Rectangle{

	Player player;
	
	Building(Player p){
		player = p;
		createBuilding();
	}
	
	void createBuilding() {	
		x = (int)(Math.random()*1000);
		y = (int)(Math.random()*1000);
		width = (int)(Math.random()*150);
		height = (int)(Math.random()*150);
		check();
		
	}
	
	void check() {
		if(x == player.x) {
			x = (int)Math.random()*1000;
			check();
		}else if(y == player.y) {
			y = (int)Math.random()*1000;
			check();
		}else if(width == player.x) {
			width = (int)Math.random()*150;
			check();
		}else if(height == player.y){
			height = (int)Math.random()*150;
		}
	}
	
	void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x-ZombiesMain.mapX, y-ZombiesMain.mapY, width, height);
		
	}
	
}
