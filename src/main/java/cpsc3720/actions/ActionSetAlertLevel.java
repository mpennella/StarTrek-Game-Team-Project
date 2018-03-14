package cpsc3720.actions;

import cpsc3720.game.Grid;
import cpsc3720.ship.Alert;
import cpsc3720.ship.Ship;
import cpsc3720.util.Loc;

public class ActionSetAlertLevel {
	int gameId;
	Loc shipLoc;
	Alert alert;
	public ActionSetAlertLevel(){}
	public ActionSetAlertLevel(int gameId, Loc shipLoc, Alert alert) {
		super();
		this.gameId = gameId;
		this.shipLoc = shipLoc;
		this.alert = alert;
	}
	
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Loc getShipLoc() {
		return shipLoc;
	}
	public void setShipLoc(Loc shipLoc) {
		this.shipLoc = shipLoc;
	}
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	//Function to set the alert level of a ship at a designated location
	//Throws an exception if it can't find the ship
	//Returns a string of what the alert level has been set to
	public void performAction(Grid grid) throws AlertLevelException{
		//checks if the ship is there and then retrives the ship
		if(!grid.getSquare(shipLoc).hasShip())
			throw new AlertLevelException("No ship to change the alert level of.");
		Ship s = grid.getSquare(shipLoc).getShip();
		
		//sets the alert level and then decriments the energy for the action
		s.setAlert(alert);
		s.decEnergyByShield();
		
		//returns the string about what the alert level has been set
		grid.getSector(shipLoc.getSector().getX(), shipLoc.getSector().getY()).addAttack(">>" + grid.incStardate() + ": " +s.getPlayerName() + "'s " + s.getShipType().getTitle() + " alert level set to " + alert);
	}
}
