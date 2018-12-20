package me.zombies;

/* These are the general bullet objects
 *  that get shot by each weapon.
 *  
 *  The bullets are made by weapon.shoot()
 */

import java.awt.*;

public class Bullet extends Rectangle{
	
	double vx,vy;
	int bulletHP;
	
	Bullet(double vx,double vy,int x,int y, int bulletHP){
		this.x=x;
		this.y=y;
				
		width = height = 10;
		
		this.vx=vx;
		this.vy=vy;
		
		this.bulletHP = bulletHP;
	}
	
	void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval((int)x, (int)y, width, height);
	}
	
	void decreaseBHP(int n) {
		
	}
}
