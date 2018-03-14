package cpsc3720.userserver;

import java.util.ArrayList;

import cpsc3720.empire.Empire;
/*
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
OLD CODE NO LONGER USED
*/
public class EmpireDatabase {

	private ArrayList<Empire> empires;
	public EmpireDatabase()
	{
		empires = new ArrayList<Empire>();
	}
	private static int curID=0;
	public int add(Empire emp) {
		//emp.setID(curID);
		//System.out.println("add(E) "+emp.getShipTypeList().size());
		empires.add(emp);
		return curID++;
	}
	public int remove(Empire empire){
		for(int i = 0; i < empires.size(); i++)
			if(empires.get(i).getID()==empire.getID() )
			{
				empires.remove(i);
				return i;
			}
		return -1;
	}

	public ArrayList<Empire> getAll() {
		return empires;
	}

	public void reset() {
		curID=0;
		empires.clear();
	}


}
