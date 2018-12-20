package me.zombies;

/* this contains the weapons:
 * their type, angle, and direction of shooting 
 * 
 */

class Weapon {
	String name;
	int maxAmmo;
	private int damage;
	int ammo;
	int bulletHP;
	int speed;

	//each weapon's properties
	Weapon(int weaponNumber){
		if (weaponNumber == 0){
			name = "Pistol";
			damage = 100;
			maxAmmo = 10;
			ammo = 10;
			bulletHP = 10;
			speed = 10;
		}
		else if (weaponNumber == 1){
			name = "Rifle";
			damage = 150;
			maxAmmo = 5;
			ammo = 5;
			bulletHP = 15;
			speed = 15;
		}
		else if (weaponNumber == 2){
			name = "Shotgun";
			damage = 100;
			maxAmmo = 2;
			ammo = 2;
			bulletHP = 20;
			speed = 5;
		}
	}

	//Calculate angle and direction of shoot
	Bullet shoot(int mx,int my,int weaponNumber,int x,int y) {
		double vx, vy;
		double dX=mx-x,dY=my-y;
		double angle=Math.atan2(dY, dX);
		
		vx = speed * Math.cos(angle);
		vy = speed * Math.sin(angle);
		
//		if (weaponNumber ==0) {
//			vx = Bullet.spedp * Math.cos(angle);
//			vy = Bullet.spedp * Math.sin(angle);
//		}
//		else if (weaponNumber ==1) {
//			vx = Bullet.spedr * Math.cos(angle);
//			vy = Bullet.spedr * Math.sin(angle);
//		}
//		else {
//			vx = Bullet.speds * Math.cos(angle);
//			vy = Bullet.speds * Math.sin(angle);
//		}
		
		ammo--;
		if (ammo < 0) ammo = 0;
		
		else { 

		}
		return new Bullet(vx,vy,x,y, bulletHP);
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
	int getBHP() {
		return bulletHP;
	}

}