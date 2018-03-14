package cpsc3720.userserver;

import java.util.ArrayList;

import cpsc3720.ship.ShipType;

/*
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
*/

public class ShipTypeDatabase {
	private ArrayList<ShipType> ships = new ArrayList<ShipType>();
	
	public void add(ShipType ship) {
		ships.add(ship);
	}
	
	public int remove(ShipType ship){
		for(int i = 0; i < ships.size(); i++){
			if(ships.get(i).getId() == ship.getId()){
				ships.remove(i);
				return i+1;
			}
		}
		return -1;
	}

	public ArrayList<ShipType> getAll() {
		return ships;
	}

	public void reset() {
		ships.clear();
	}

	private static ShipTypeDatabase instance = new ShipTypeDatabase();

	public static ShipTypeDatabase instance() {
		return instance;
	}

}

