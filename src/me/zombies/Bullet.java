package me.zombies;

import java.awt.*;

public class Bullet extends Rectangle{
	
	//double x,y,r;
	static int spedp=10;
	static int speds=5;
	static int spedr=15;
	double vx,vy;
	
	Bullet(double vx,double vy){
		x=ZombiesMain.panW/2;
		y=ZombiesMain.panH/2;
		width = height=10;
		
		this.vx=vx;
		this.vy=vy;
	}
	
	void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval((int)x+ZombiesMain.mapX, (int)y+ZombiesMain.mapY, width, height);
	}
}
