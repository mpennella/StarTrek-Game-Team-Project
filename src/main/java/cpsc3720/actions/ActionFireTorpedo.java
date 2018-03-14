package cpsc3720.actions;

import cpsc3720.game.Grid;
import cpsc3720.ship.Alert;
import cpsc3720.ship.Ship;
import cpsc3720.util.Loc;

public class ActionFireTorpedo {
	int gameId;
	Loc attacker;
	Loc defender;
	
	public ActionFireTorpedo(){};
	public ActionFireTorpedo(int gameId, Loc attacker, Loc defender) {
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
	
	//Function to fire a torpedo at the desired target
	//Throws an exception if it can't find the attacking ship
	//Returns a string of the attack
	public void performAction(Grid grid) throws FireTorpedoException{
		//checks for the attacking ship and then retrives it
		if(!grid.getSquare(attacker).hasShip())
			throw new FireTorpedoException("Attacker not found.");
		Ship attacking = grid.getSquare(attacker).getShip();
		
		//checks to see if there are enough missles to fire
		if(attacking.getCurMissile() <= 0)
			grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(attacking.getPlayerName() + " tried to fire a missle but didn't have one to fire.");
		//decrements the missle count
		attacking.setCurMissile(attacking.getCurMissile() - 1);
		
		//checks for a defender and if there isn't one returns that the attack was a miss
		//if there is a defender it retrives the defender
		if(!grid.getSquare(defender).hasShip()){
			grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " +attacking.getPlayerName() + "'s attack did not hit a ship.");
		}
		else{
			Ship defending = grid.getSquare(defender).getShip();
			
			//conditionals to determine the alert level of the ship and then perfrom the calculations
			//based upon the level of the shields then returns a string of the attack
			if(defending.getAlert() == Alert.Green){
				grid.getSquare(defender).setOccupant(null);
				attacking.decEnergyByShield();
				grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " + defending.getPlayerName()+ "'s " + defending.getShipType().getTitle() + " destroyed by " + attacking.getPlayerName()+ "'s " +attacking.getShipType().getTitle());
			}
			else if(defending.getAlert() == Alert.Yellow){
				int damage = defending.getCurShield() - (attacking.getShipType().getMissile().getYield() / 2);
				defending.setCurShield(damage);
				attacking.decEnergyByShield();
				if(defending.getCurShield() < 0){
					grid.getSquare(defender).setOccupant(null);
					grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " + defending.getPlayerName()+ "'s " + defending.getShipType().getTitle() + " destroyed by " + attacking.getPlayerName()+ "'s " +attacking.getShipType().getTitle());
				}
				else
					grid.getSector(attacker.getSector().getX(), attacker.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " + defending.getPlayerName()+ "'s " + defending.getShipType().getTitle() + " attcked by " + attacking.getPlayerName()+ "'s " + attacking.getShipType().getTitle() +  "; shields now at  " + damage);
			}
			
			else {
				int damage = defending.getCurShield() - (attacking.getShipType().getMissile().getYield() / 5);
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
