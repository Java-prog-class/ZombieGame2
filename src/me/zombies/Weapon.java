package me.zombies;

class Weapon {
	String name;
	int maxAmmo;
	private int damage;
	int ammo;

	//each weapon's properties
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

	//Calculate angle and direction of shoot
	Bullet shoot(int mx,int my,int weaponNumber,int x,int y) {
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
		if (ammo < 0) ammo = 0;
		
		else { 

		}
		return new Bullet(vx,vy,x,y);
	}
	
	//reload lol
	void reload() {
		ammo = maxAmmo;
	}
	//zombo damage
	int getDamage() {
		return damage;
	}
	//tell us the ammo
	int getAmmo() {
		return ammo;
	}

}