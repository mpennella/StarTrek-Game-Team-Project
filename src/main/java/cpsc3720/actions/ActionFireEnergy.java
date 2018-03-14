package cpsc3720.actions;

import cpsc3720.game.Grid;
import cpsc3720.ship.Alert;
import cpsc3720.ship.Ship;
import cpsc3720.util.Loc;

public class ActionFireEnergy {
	int gameId;
	Loc attacker;
	Loc defender;
	public ActionFireEnergy(int gameId, Loc attacker, Loc defender) {
		super();
		this.gameId = gameId;
		this.attacker = attacker;
		this.defender = defender;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public Loc getAttacker() {
		return attacker;
	}
	public void setAttacker(Loc attacker) {
		this.attacker = attacker;
	}
	public Loc getDefender() {
		return defender;
	}
	public void setDefender(Loc defender) {
		this.defender = defender;
	}
	
	//Function to fire an energy weapon at a specified target
	//Throws an exception if it can't find the ship that wishes to fire
	//Returns a string of the action that happened
	public void performAciton(Grid grid) throws FireTorpedoException{
		//checks for the attcking ship
		if(!grid.getSquare(attacker).hasShip())
			throw new FireTorpedoException("Attacker not found.");
		//gets the attacking ship
		Ship attacking = grid.getSquare(attacker).getShip();
		//checks for a defender if there isn't one the attack misses
		if(!grid.getSquare(defender).hasShip())
			grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " + attacking.getPlayerName() + "'s attack did not hit a ship.");
		else {
			//gets the defender
			Ship defending = grid.getSquare(defender).getShip();
			
			//conditionals to check the alert level of the ship and perform the correct calculations depedning
			//on the alert level of the ship
			if(defending.getAlert() == Alert.Green){
				grid.getSquare(defender).setOccupant(null);
				attacking.decEnergyByShield();
				grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " + defending.getPlayerName()+ "'s " + defending.getShipType().getTitle() + " destroyed by " + attacking.getPlayerName()+ "'s " +attacking.getShipType().getTitle());
			}
			else if(defending.getAlert() == Alert.Yellow){
				int damage = defending.getCurShield() - (attacking.getShipType().getEnergy().getYield() / 2);
				defending.setCurShield(damage);
				attacking.decEnergyByShield();
				if(defending.getCurShield() <= 0){
					grid.getSquare(defender).setOccupant(null);
					grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " + defending.getPlayerName()+ "'s " + defending.getShipType().getTitle() + " destroyed by " + attacking.getPlayerName()+ "'s " +attacking.getShipType().getTitle());
				}
				else
					grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " + defending.getPlayerName()+ "'s " + defending.getShipType().getTitle() + " attcked by " + attacking.getPlayerName()+ "'s " + attacking.getShipType().getTitle() +  "; shields now at  " + damage);
			}
			else {
				int damage = defending.getCurShield() - (attacking.getShipType().getEnergy().getYield() / 5);
				defending.setCurShield(damage);
				attacking.decEnergyByShield();
				if(defending.getCurShield() <= 0){
					grid.getSquare(defender).setOccupant(null);
					grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " + defending.getPlayerName()+ "'s " + defending.getShipType().getTitle() + " destroyed by " + attacking.getPlayerName()+ "'s " +attacking.getShipType().getTitle());
				}
				else
					grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " + defending.getPlayerName()+ "'s " + defending.getShipType().getTitle() + " attcked by " + attacking.getPlayerName()+ "'s " + attacking.getShipType().getTitle() +  "; shields now at  " + damage);
			}
		}
	}
}
