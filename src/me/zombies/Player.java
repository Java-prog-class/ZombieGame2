package me.zombies;

import java.awt.Color;

import javax.swing.JPanel;

 class Player{
	int x,y,r;
	static int HP;

	Player(){
		x=ZombiesMain.panW/2;
		y=ZombiesMain.panH/2;
		r=20;
	}
	
	void decreaseHP(int n, Zombie z){
		if (z.type == "light") {
			Player.HP-= n/Math.random()*.100;
		}
		if (z.type == "medium") {
			Player.HP -= n/Math.random()*.50;
		}
		if (z.type == "heavy") {
			Player.HP -= n/Math.random()*.25;
		}
	}
}
