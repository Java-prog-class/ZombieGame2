package me.zombies;
//creating bullet(s) object
import java.awt.*;

public class Bullet extends Rectangle{
	
	static int spedp=10;
	static int speds=5;
	static int spedr=15;
	double vx,vy;
	
	Bullet(double vx,double vy,int x,int y){
		this.x=x;
		this.y=y;
		
		width = height = 10;
		
		this.vx=vx;
		this.vy=vy;
	}
	
	void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval((int)x, (int)y, width, height);
	}
}
