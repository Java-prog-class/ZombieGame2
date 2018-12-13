package me.zombies;

class Weapon {
	String name;
	int type;
	int maxAmmo;
	private int damage;
	private static int ammo;
	static final int PISTOL = 1;
	static final int RIFLE = 2;
	static final int SHOTGUN = 3;

	Weapon(int type){
		this.type = type; // store the weapon number

		switch(type) {
		case PISTOL:
			damage = 100;
			maxAmmo = 30;
			ammo = 30;
			name = "pistol";
			break;
		case RIFLE:
			damage = 150;
			maxAmmo = 15;
			ammo = 15;
			name = "rifle";
			break;
		case SHOTGUN:
			damage = 200;
			maxAmmo = 5;
			ammo = 5;
			name = "shotgun";
			break;
		}
	}

	static void shoot() {
		
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