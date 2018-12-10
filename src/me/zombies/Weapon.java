package me.zombies;

class Weapon {
	String name;
	int maxAmmo;
	private int damage;
	private static int ammo;

	Weapon(int weaponNumber){
		if (weaponNumber == 1){
			name = "Pistol";
			damage = 100;
			maxAmmo = 30;
			ammo = 30;
		}
		else if (weaponNumber == 2){
			name = "Rifle";
			damage = 150;
			maxAmmo = 15;
			ammo = 15;
		}
		else if (weaponNumber == 3){
			name = "Shotgun";
			damage = 100;
			maxAmmo = 5;
			ammo = 5;
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