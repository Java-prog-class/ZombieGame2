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
		x = (int)(Math.random()*1000)+1;
		y = (int)(Math.random()*1000)+1;
		width = (int)(Math.random()*150)+50;
		height = (int)(Math.random()*150)+50;
		check();
		
	}
	
	void check() {
		if(x == player.x+player.r) {
			x = (int)(Math.random()*1500);
			check();
		}else if(y == player.y+player.r) {
			y = (int)(Math.random()*1500);
			check();
		}else if(width == player.x+player.r) {
			width = (int)(Math.random()*150);
			check();
		}else if(height == player.y+player.r){
			height = (int)(Math.random()*150);
		}
	}
	
	void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x-ZombiesMain.mapX, y-ZombiesMain.mapY, width, height);
		
	}
	
}
