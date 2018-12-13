package me.zombies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class Building extends Rectangle{

	Player player;
	int WIDTH;
	int HEIGHT;
	Color colour = Color.GRAY;
	
	Building(Player p, int width, int height){
		player = p;
		WIDTH = width;
		HEIGHT = height;
		createBuilding();
	}
	
	void createBuilding() {	
		x = (int)(Math.random()*WIDTH*7) - WIDTH*3;
		y = (int)(Math.random()*HEIGHT*7) - HEIGHT*3;
		width = (int)(Math.random()*150)+50;
		height = (int)(Math.random()*150)+50;;		
	}
	
	
	void paint(Graphics g) {
		g.setColor(colour);
		//g.fillRect(x-ZombiesMain.mapX, y-ZombiesMain.mapY, width, height);
		g.fillRect(x+ZombiesMain.mapX, y+ZombiesMain.mapY, width, height);
		
	}
		
/*	@Override
	public String toString() {
		String s= String.format("x=%d, y=%d, x2=%d, y2=%d", x,y,x+width, y+ height);
		return s;
	}
*/
   }