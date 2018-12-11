package me.zombies;

class Weapon {
	String name;
	static String ammoN;
	int maxAmmo;
	private int damage;
	private static int ammo;

	Weapon(int weaponNumber){
		if (weaponNumber == 1){
			name = "Pistol";
			damage = 100;
			maxAmmo = 10;
			ammo = 10;
			ammoN = "10";
		}
		else if (weaponNumber == 2){
			name = "Rifle";
			damage = 150;
			maxAmmo = 5;
			ammo = 5;
			ammoN = "5";
		}
		else if (weaponNumber == 3){
			name = "Shotgun";
			damage = 100;
			maxAmmo = 2;
			ammo = 2;
			ammoN = "2";
		}
	}

	static void shoot() {
		ammo--;
		if (ammoN.equals("10")) {
			
		}
		if (ammoN.equals("5")) {
			
		}
		if (ammoN.equals("2")) {
			
		}
	}
	void reload(int numBullets) {
		ammo += numBullets;
		if (ammo > maxAmmo) ammo = maxAmmo;
	}

	int getDamage() {
		return damage;
	}
}