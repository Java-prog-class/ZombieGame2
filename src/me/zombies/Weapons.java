package me.zombies;

class Weapons {
	String name;
	int maxAmmo;
	private int damage;
	private int ammo;
	
	Weapons(int type){
		switch(type) {
		case 1:
			damage = 100;
			ammo = 30;
			name = "pistol";
			break;
		case 2:
			damage = 150;
			ammo = 15;
			name = "pistol";
		case 3:
			damage = 200;
			ammo = 5;
			name = "pistol";
		}
	}
	
	void shoot() {
		ammo--;
	}
	void reload(int numBullets) {
		ammo += numBullets;
		if (ammo > maxAmmo) ammo = maxAmmo;
	}
	
	int getDamage() {
		return damage;
	}
}


/*
 	damage = new int[] {
				100,
				150,
				200
		};

		ammo = new int[] {
				30,
				15,
				5
*/
