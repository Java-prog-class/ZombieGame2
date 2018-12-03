package me.zombies;

class Weapon {
	String name;
	int type;
	int maxAmmo;
	private int damage;
	private int ammo;
	//these must be sequential so that we can flip through them  
	static final int PISTOL = 1;
	static final int RIFLE = 2;
	static final int SHOTGUN = 3;
	
	Weapon(int type){
		this.type = type; // store the weapon number
		
		switch(type) {
		case PISTOL:
			damage = 100;
			ammo = 30;
			name = "pistol";
			break;
		case RIFLE:
			damage = 150;
			ammo = 15;
			name = "rifle";
		case SHOTGUN:
			damage = 200;
			ammo = 5;
			name = "shotgun";
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
