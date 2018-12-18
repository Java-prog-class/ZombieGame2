package me.zombies;

class Weapon {
	String name;
	int maxAmmo;
	private int damage;
	int ammo;

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

	Bullet shoot(int mx,int my,int weaponNumber,int x,int y) {
		//double vx=ZombiesMain.panW/2,vy=ZombiesMain.panH/2;
		double vx, vy;
		double dX=mx-x,dY=my-y;
		double angle=Math.atan2(dY, dX);

		if (weaponNumber ==0) {
			vx = Bullet.spedp * Math.cos(angle);
			vy = Bullet.spedp * Math.sin(angle);
		}
		else if (weaponNumber ==1) {
			vx = Bullet.spedr * Math.cos(angle);
			vy = Bullet.spedr * Math.sin(angle);
		}
		else {
			vx = Bullet.speds * Math.cos(angle);
			vy = Bullet.speds * Math.sin(angle);
		}
		ammo--;
		return new Bullet(vx,vy,x,y);
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