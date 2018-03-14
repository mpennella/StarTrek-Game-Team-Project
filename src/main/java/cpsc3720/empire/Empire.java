package cpsc3720.empire;

import java.util.ArrayList;

import cpsc3720.ship.*;
/* Each object of this class represents one Empire within a game, including basic information as well as a list of
associated ShipType objects and a boolean dictating whether the empire's victory condition is exploration or aggression */
public class Empire 
{

	private String id;
	private ArrayList<ShipType> shipTypes;
	private String name="--";
	private boolean exploration;
	public Empire()
	{
		name="_";
		shipTypes=new ArrayList<ShipType>();
		exploration=false;
		id="===";
		//System.out.println("blank constructor ");
	}
	public Empire(String ID, String setname, boolean exp)
	{
		name=setname;
		id = ID;
		exploration = exp;
		shipTypes=new ArrayList<ShipType>();
		//System.out.println("String constructor ");
	}
	public String getID() {
		return id;
	}
	public Empire(String empireName, ArrayList<ShipType> setShipTypes) {
		name = empireName;
		shipTypes = setShipTypes;
		System.out.println("full constructor: "+shipTypes.toString());
	}
	public void setID(String ID) {
		id = ID;
	}
	public void setName(String set)
	{
		name=set;
	}
	
	@Override
	public String toString() {
		return "Empire [id=" + id + ", shipTypes=" + shipTypes + ", name="
				+ name + ", exploration=" + exploration + "]";
	}
	public String getName(){
		return name;
	}
	public ArrayList<ShipType> getShipTypeList()
	{
		return shipTypes;
	}
	public void addShipType(ShipType s)
	{
		shipTypes.add(s);
	}
	public ArrayList<ShipType> getShipTypes() {
		return shipTypes;
	}
	public void setShipTypes(ArrayList<ShipType> shipTypes) {
		this.shipTypes = shipTypes;
	}
	public boolean isExploration() {
		return exploration;
	}
	public void setExploration(boolean exploration) {
		this.exploration = exploration;
	}
	
	
	
}
