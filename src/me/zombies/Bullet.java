package me.zombies;

import java.awt.Graphics;

public class Bullet {
	
	int x,y,r;
	int vx = 10, vy = 10;
	
	Bullet(){
		x=ZombiesMain.panW/2;
		y=ZombiesMain.panH/2;
		r=10;
	}
	
	void paint(Graphics g) {
		g.fillOval(x, y, r, r);
	}

	void shoot() {
		
	}
}
