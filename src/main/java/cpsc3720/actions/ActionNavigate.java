package cpsc3720.actions;

import java.util.Random;

import cpsc3720.game.Grid;
import cpsc3720.game.Square;
import cpsc3720.ship.Ship;
import cpsc3720.user.Player;
import cpsc3720.util.Loc;

public class ActionNavigate {
	int gameId;
	Player start;
	Loc destination;
	public ActionNavigate(){}
	public ActionNavigate(int gameId, Player start, Loc destination) {
		super();
		this.gameId = gameId;
		this.start = start;
		this.destination = destination;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Player getStart() {
		return start;
	}
	public void setStart(Player start) {
		this.start = start;
	}
	public Loc getDestination() {
		return destination;
	}
	public void setDestination(Loc destination) {
		this.destination = destination;
	}
	
	//Function to navigate a desired ship to a desired location
	//Throws an error if it can't find the ship that wishes to move
	//Returns a string of the navigation that is only used for debugging currently
	public String performAction(Grid grid) throws NavigationException{
		//checks for the ship and then retrives it 
		if(!grid.getSquare(start.getShipLoc()).isOccupied())
			throw new NavigationException("Unable to find the ship.");
		grid.incStardate();
		Ship s = (Ship) grid.getSquare(start.getShipLoc()).getOccupant();
		
		//checks to see if the ship wishes to use warp or impulse
		if(start.getShipLoc().getSector().getX() != destination.getSector().getX() || start.getShipLoc().getSector().getY() != destination.getSector().getY()){
			//calculations to get the distance the ships will travel
			int horizontalTravel = start.getShipLoc().getSector().getX() - destination.getSector().getX();
			if (horizontalTravel < 0) horizontalTravel *= -1;
			int verticalTravel = start.getShipLoc().getSector().getY() - destination.getSector().getY();
			if(verticalTravel < 0) verticalTravel *= -1;
			double totalTravel = Math.sqrt((horizontalTravel * horizontalTravel) + (verticalTravel * verticalTravel));
			
			//checks if the total travel is less than the  max speed of the ship
			if(totalTravel < s.getShipType().getMaxSpeed()){
				//gets the total energy
				int energyUsed = (int)totalTravel * 100;
				
				//checks to see if the ship has enough energy
				if(s.getCurEnergy() < energyUsed){
					return s.getPlayerName() + "'s " + s.getShipType().getTitle() + " didn't have enough energy to make the jump to " + destination.getSector();
				}
				
				//finds a random square that is unoccupied for the ship to go into
				Random rnd = new Random();
				int x = rnd.nextInt(8);
				int y = rnd.nextInt(8);
				Loc tempDest = new Loc(destination.getSector().getX(), destination.getSector().getY(), x, y);
				while(grid.getSquare(tempDest).isOccupied()){
					x = rnd.nextInt(8);
					y = rnd.nextInt(8);
					tempDest = new Loc(destination.getSector().getX(), destination.getSector().getY(), x, y);
				}
				
				//decrements the energy of the ship for the navigation
				s.setCurEnergy(s.getCurEnergy() - energyUsed);
				
				//moves the ships and sets the shiploc on the player to the new loc
				grid.getSquare(tempDest).setShip(s);
				grid.getSquare(start.getShipLoc()).setOccupant(null);
				start.setShipLoc(tempDest);
				
				s.decEnergyByShield();
				return s.getPlayerName() + "'s " + s.getShipType().getTitle() + " moved to location " + tempDest + " using " + energyUsed + " energy.";
			}
			else
				return "Distance wished to be traveled greater than max speed of " + s.getPlayerName() + "'s " + s.getShipType().getTitle();
		}
		else{

			//same as before but moves to a specific destination on the sector
			Square sq = grid.getSquare(destination);
			if(sq.isOccupied())
				return "Can't move " + s.getPlayerName() + "'s " + s.getShipType().getTitle() + " to " + destination + " because the square is occupied.";
			int horizontalTravel = start.getShipLoc().getSquare().getX() - destination.getSquare().getX();
			if(horizontalTravel < 0) 
				horizontalTravel *= -1;
			int verticalTravel = start.getShipLoc().getSquare().getY() - destination.getSquare().getY();
			if(verticalTravel < 0) 
				verticalTravel *= -1;
			double totalTravel = Math.sqrt((horizontalTravel * horizontalTravel) + (verticalTravel * verticalTravel));
			int energyUsed = (int)totalTravel * 10;
			if(s.getCurEnergy() < energyUsed)
				return s.getPlayerName() + "'s " + s.getShipType().getTitle() + " didn't have enough energy to make the navigation to " + destination.getSquare();
			s.setCurEnergy(s.getCurEnergy() - energyUsed);
			sq.setShip(grid.getSquare(start.getShipLoc()).getShip());
			grid.getSquare(start.getShipLoc()).setOccupant(null);
			start.setShipLoc(destination);
			s.decEnergyByShield();
			return s.getPlayerName() + "'s " + s.getShipType().getTitle() + " moved to location " + destination + " using " + energyUsed + " energy.";	
		}
	}

}
