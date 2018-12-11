package me.zombies;

class Weapon {
	String name;
	int maxAmmo;
	private int damage;
	private int ammo;
	
	Bullet bullets = new Bullet();

	Weapon(int weaponNumber){
		if (weaponNumber == 0){
			name = "Pistol";
			damage = 100;
			maxAmmo = 10;
			ammo = 10;
		}
		else if (weaponNumber == 1){
			name = "Rifle";
			damage = 150;
			maxAmmo = 5;
			ammo = 5;
		}
		else if (weaponNumber == 2){
			name = "Shotgun";
			damage = 100;
			maxAmmo = 2;
			ammo = 2;
		}
	}

	void shoot() {
		ammo--;
		if (ammo < 0) ammo = 0;
		else bullets.shoot();
		
	}
	void reload() {
		ammo = maxAmmo;
	}

	int getDamage() {
		return damage;
	}
	int getAmmo() {
		return ammo;
	}
	
}