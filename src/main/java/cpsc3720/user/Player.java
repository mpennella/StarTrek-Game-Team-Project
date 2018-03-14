package cpsc3720.user;

import cpsc3720.empire.Empire;
import cpsc3720.game.Square;
import cpsc3720.ship.*;
import cpsc3720.userserver.GameDatabase;
import cpsc3720.util.*;
/* Each object of this type represents one player within a specific game and keeps track of that player's empire and ship*/
public class Player {
	int id;
	String name;
	Empire aff;
	int gameId;
	Loc shipLoc;
	public Player(String name, int setGameId,Empire aff, Loc setShipLoc) {
		super();
		gameId=setGameId;
		this.name = name;
		this.aff = aff;
		shipLoc=setShipLoc;
	}
	
	public Player() {
		id = -1;
		name = "CPU";
		aff = null;
		shipLoc = null;
		gameId = -1;
	}
//This method uses the location of the ship to return the ship associated with the player the method is called on
	public Ship accessShip() {
		Square pos= GameDatabase.instance().getGame(gameId).getGrid().getSquare(shipLoc);
	    assert(pos.getOccType()=='S');
	    return (Ship)pos.getOccupant();
	}
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", aff=" + aff
				+ ", ship@" + shipLoc+"]";
	}
//------Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int set)
	{
		gameId=set;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Empire getAff() {
		return aff;
	}
	public void setAff(Empire aff) {
		this.aff = aff;
	}
	public Loc getShipLoc() {
		return shipLoc;
	}
	public void setShipLoc(Loc setShipLoc) {
		shipLoc=setShipLoc;
	}
	
	
}
