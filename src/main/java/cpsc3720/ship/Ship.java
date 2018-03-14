package cpsc3720.ship;

import cpsc3720.user.Player;
import cpsc3720.util.Loc;
/* Each object of this class type represents one player's ship, including holding current resource values and alert levels*/
public class Ship {
	int id=-1;
	ShipType shipType;
	int curEnergy;
	int curMissile;
	String playerName;
	Alert alert;
	int curShield;
	public Ship(ShipType ship, int curEnergy, int curMissle,
			Alert alert, int curShield) {
		super();
		this.shipType = ship;
		this.playerName = "CPU";
		this.curEnergy = curEnergy;
		this.curMissile = curMissle;
		this.alert = alert;
		this.curShield = curShield;
		playerName = "";
	}
	public Ship()
	{
		id=-1;
		shipType=null;
		curEnergy=-1;
		curMissile=-1;
		playerName=".";
		alert=Alert.Green;
		this.playerName = "CPU";
		curShield=-1;
	}
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(Player player) {
		playerName=player.getName();
	}
	public void setPlayerName(String set) {
		playerName=set;
	}
	public Ship(ShipType ship, Loc loc) {
		super();
		this.shipType = ship;
		curEnergy = ship.getMaxEnergy();
		curMissile = ship.getMaxMissiles();
		alert = Alert.Green;
		curShield = ship.getMaxShields();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ShipType getShipType() {
		return shipType;
	}
	public void setShipType(ShipType ship) {
		this.shipType = ship;
	}
	public int getCurEnergy() {
		return curEnergy;
	}
	public void setCurEnergy(int curEnergy) {
		this.curEnergy = curEnergy;
	}
	public int getCurMissile() {
		return curMissile;
	}
	public void setCurMissile(int curMissle) {
		this.curMissile = curMissle;
	}
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert setalert) {
		alert = setalert;
	}
	public int getCurShield() {
		return curShield;
	}
	public void setCurShield(int curShield) {
		this.curShield = curShield;
	}
	//This method uses the ship's max energy level and current alert level to calculate
	//the decrease in energy and will be called each time the ship performs a successful action
	public void decEnergyByShield(){
		if(alert == Alert.Yellow){
    			int amount = (int) (shipType.getMaxEnergy() * .02);
    			curEnergy -= amount;
    		}
    		else if(alert == Alert.Red){
    			int amount = (int) (shipType.getMaxEnergy() * .05);
    			curEnergy -= amount;
    		}
	}
	
}
