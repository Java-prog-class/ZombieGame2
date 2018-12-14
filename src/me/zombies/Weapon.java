package me.zombies;

class Weapon {
	String name;
	int maxAmmo;
	private int damage;
	private int ammo;

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

	Bullet shoot(int mx,int my,int weaponNumber) {
		double x=ZombiesMain.panW/2,y=ZombiesMain.panH/2;
		double dX=mx-x,dY=my-y;
		double angle=Math.atan2(dY, dX);

		if (weaponNumber ==0) {
			x = Bullet.spedp * Math.cos(angle);
			y = Bullet.spedp * Math.sin(angle);
		}
		else if (weaponNumber ==1) {
			x = Bullet.spedr * Math.cos(angle);
			y = Bullet.spedr * Math.sin(angle);
		}
		else {
			x = Bullet.speds * Math.cos(angle);
			y = Bullet.speds * Math.sin(angle);
		}
		ammo--;
		if (ammo < 0) ammo = 0;
		
		else { 

		}
		return new Bullet(x,y);
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