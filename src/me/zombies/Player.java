package me.zombies;

 class Player{
	int x,y,r;
	int vx = 10, vy = 10;
	static int HP = 1000;
	int currentWeapon = 0;
	
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
