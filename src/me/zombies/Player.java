package me.zombies;

import java.awt.Rectangle;

class Player extends Rectangle{
	int x,y,r;
	int speed=10;
	int vx = 10, vy = 10;
	static int HP = 1000;
	int currentWeapon = 0;
	int width,height;
	
	Player(){
		x=ZombiesMain.panW/2;
		y=ZombiesMain.panH/2;
		width=height=20;		
	}
	
	void decreaseHP(int n, Zombie z){

		if (z.type == "light") HP-= n/Math.random()*.100;
		if (z.type == "medium") HP -= n/Math.random()*.50;
		if (z.type == "heavy") HP -= n/Math.random()*.25;
	}
}