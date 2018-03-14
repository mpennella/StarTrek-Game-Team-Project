package cpsc3720.userserver;

import java.util.ArrayList;

import cpsc3720.weapon.Weapon;
/*
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
*/
public class WeaponDatabase {

	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	
	public void add(Weapon weapon) {
		weapons.add(weapon);
	}
	
	public int remove(Weapon weapon){
		for(int i = 0; i < weapons.size(); i++)
			if(weapons.get(i).getId() == weapon.getId()){
				weapons.remove(i);
				return i+1;
			}
		return 0;
	}

	public ArrayList<Weapon> getAll() {
		return weapons;
	}

	public void reset() {
		weapons.clear();
	}

	private static WeaponDatabase instance = new WeaponDatabase();

	public static WeaponDatabase instance() {
		return instance;
	}

}
